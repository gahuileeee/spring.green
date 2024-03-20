package kr.co.sboard.service;

import com.sun.net.httpserver.HttpHandler;
import jakarta.transaction.Transactional;
import kr.co.sboard.dto.ArticleDTO;
import kr.co.sboard.dto.FileDTO;
import kr.co.sboard.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {
    private  final FileRepository fileRepository;
    private  final  ModelMapper modelMapper;

    @Value("${file.upload.path}")
    private  String fileUploadPath;

    public  void fileUpload(ArticleDTO articleDTO)  {
        String path = new File(fileUploadPath).getAbsolutePath();  //실제 업로드 할 시스템상의 경로 구하기
        int ano = articleDTO.getNo();

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

                }catch (IOException e){
                    log.error("fileUpload : "+e.getMessage());
                }
            }
        }

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

            return new ResponseEntity<>(resource, headers, HttpStatus.OK);
        }catch (IOException e){
            log.error("fileDownlaod .. : "+e.getMessage());
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
    }

}
