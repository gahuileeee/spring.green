package kr.co.sboard.service;

import com.sun.net.httpserver.HttpHandler;
import jakarta.transaction.Transactional;
import kr.co.sboard.dto.ArticleDTO;
import kr.co.sboard.dto.FileDTO;
import kr.co.sboard.entity.Article;
import kr.co.sboard.repository.ArticleRepository;
import kr.co.sboard.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.ssl.SslProperties;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {
    private  final FileRepository fileRepository;
    private  final  ModelMapper modelMapper;
    private  final ArticleRepository articleRepository;

    @Value("${file.upload.path}")
    private  String fileUploadPath;

    public  int fileUpload(ArticleDTO articleDTO)  {
        String path = new File(fileUploadPath).getAbsolutePath();  //실제 업로드 할 시스템상의 경로 구하기
        log.info("fileupload" + articleDTO.getFiles());
        int ano = articleDTO.getNo();
        int count = 0;
            for(MultipartFile mf : articleDTO.getFiles()){
                if(mf.getOriginalFilename() !=null && mf.getOriginalFilename() != ""){
                String oName = mf.getOriginalFilename();
                String ext = oName.substring(oName.lastIndexOf(".")); //확장자
                String sName = UUID.randomUUID().toString()+ ext;

                log.info("oName : "+oName);
                try{
                    //upload directory에 upload가 됨
                    mf.transferTo(new File(path, sName));

                    FileDTO fileDTO = FileDTO.builder()
                            .ano(ano)
                            .oName(oName)
                            .sName(sName)
                            .build();
                    fileRepository.save(fileDTO.toEntity());
                    count++;
                }catch (IOException e){
                    log.error("fileUpload : "+e.getMessage());
                }
            }
        }
            return count;

    }
    @Transactional
    public   ResponseEntity fileDownload(int fno) {
        try {
            FileDTO fileDTO  = modelMapper.map(fileRepository.findById(fno), FileDTO.class) ;

            Path path = Paths.get(fileUploadPath + fileDTO.getSName());

            String contentType = Files.probeContentType(path);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentDisposition(ContentDisposition.builder("attachment")
                    .filename(fileDTO.getOName(), StandardCharsets.UTF_8)
                    .build());

            headers.add(HttpHeaders.CONTENT_TYPE, contentType);

            Resource resource = new InputStreamResource(Files.newInputStream(path));

            //파일 다운로드 count update
            fileDTO.setDownload(fileDTO.getDownload()+1);
            fileRepository.save(modelMapper.map(fileDTO, kr.co.sboard.entity.File.class));

            Map<String, Object> resultMap = new HashMap<>();
            return new ResponseEntity<>(resource, headers, HttpStatus.OK);
        }catch (IOException e){
            log.error("fileDownlaod .. : "+e.getMessage());
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }

    }

    @Transactional
    public ResponseEntity deleteFile(List<Integer> list, int no){
        //파일 수 변경
        Article article= articleRepository.findById(no).get();
        ArticleDTO articleDTO = modelMapper.map(article, ArticleDTO.class);
        articleDTO.setFile(articleDTO.getFile() - list.size());
        Article nArticle =modelMapper.map(articleDTO, Article.class);
        log.info("!!"+nArticle.getFile());
        articleRepository.save(nArticle);

        //파일 삭제
        String path = new File(fileUploadPath).getAbsolutePath();

        for(int fno : list){
            //DB에 삭제
            kr.co.sboard.entity.File file  = fileRepository.findById(fno).get();
            String sName = file.getSName();
            fileRepository.deleteById(fno);

            //실제로 삭제
            File deleteFile = new File(fileUploadPath+File.separator+ sName);
            if(deleteFile.exists()){
                deleteFile.delete();
            }
        }

        Map<String, Object> map2 = new HashMap<>();
        map2.put("delte", "success");

        return ResponseEntity.ok().body(map2);
    }

    public  void deleteFiles(int ano){
        String path = new File(fileUploadPath).getAbsolutePath();
        List<kr.co.sboard.entity.File> files = fileRepository.findFilesByAno(ano);
        for(kr.co.sboard.entity.File file : files){
            String sName = file.getSName();
            int fno = file.getFno();
            fileRepository.deleteById(fno);

            File deleteFile = new File(fileUploadPath+File.separator+sName);
            if(deleteFile.exists()){
                deleteFile.delete();
            }
        }
    }

    public ResponseEntity<?> fileDownloadCount(int fno)  {

        // 파일 조회
        kr.co.sboard.entity.File file = fileRepository.findById(fno).get();

        // 다운로드 카운트 Json 생성
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("count", file.getDownload());

        return ResponseEntity.ok().body(resultMap);
    }

}
