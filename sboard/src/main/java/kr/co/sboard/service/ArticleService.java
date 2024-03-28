package kr.co.sboard.service;

import com.querydsl.core.Tuple;
import jakarta.transaction.Transactional;
import kr.co.sboard.dto.ArticleDTO;
import kr.co.sboard.dto.FileDTO;
import kr.co.sboard.dto.PageRequestDTO;
import kr.co.sboard.dto.PageResponseDTO;
import kr.co.sboard.entity.Article;
import kr.co.sboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class ArticleService {
    // RootConfig Bean 생성/등록
    private final ModelMapper modelMapper;


    private final ArticleRepository articleRepository;
    private  final  FileService fileService;

    public void insertArticle(ArticleDTO articleDTO){
        articleDTO.setFile(articleDTO.getFiles().size());

        for(MultipartFile mf : articleDTO.getFiles()){
            if(mf.getOriginalFilename() ==null || mf.getOriginalFilename() == ""){
                articleDTO.setFile(0);
            }
        }
        Article article = modelMapper.map(articleDTO, Article.class);
        log.info(article.toString());
        Article savedArticle= articleRepository.save(article);
        int ano = savedArticle.getNo();
        articleDTO.setNo(ano);

       fileService.fileUpload(articleDTO);
    }

    public void modifyArticle(ArticleDTO articleDTO){
        Article oArticle = articleRepository.findById(articleDTO.getNo()).get();
        ArticleDTO oArticleDTO = modelMapper.map(oArticle, ArticleDTO.class);


        oArticleDTO.setContent(articleDTO.getContent());
        oArticleDTO.setTitle(articleDTO.getTitle());
        oArticleDTO.setFiles(articleDTO.getFiles());

        int count = fileService.fileUpload(oArticleDTO);

        oArticleDTO.setFile(oArticleDTO.getFile()+count);
        Article article = modelMapper.map(oArticleDTO, Article.class);
        articleRepository.save(article);

    }

    public ArticleDTO selectArticle(int no){
        return modelMapper.map(articleRepository.findById(no), ArticleDTO.class);
    }
    
    //일반 글 목록 조회
 /*
    public PageResponseDTO findByParentAndCate(PageRequestDTO pageRequestDTO){
        Pageable pageable = pageRequestDTO.getPageable("no");
        Page<Article> pageArticle = articleRepository.findByParentAndCate(0, pageRequestDTO.getCate(), pageable);
        List<ArticleDTO> dtoList = pageArticle.getContent().stream()
                .map(entity -> modelMapper.map(entity, ArticleDTO.class))
                .toList();
        int total = (int) pageArticle.getTotalElements();
        return PageResponseDTO.builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total(total)
                .build();
    }

  */
   public PageResponseDTO findByParentAndCate(PageRequestDTO pageRequestDTO){
       Pageable pageable = pageRequestDTO.getPageable("no");
       Page<Tuple> pageArticle = articleRepository.selectArticles(pageRequestDTO , pageable);
       log.info(pageArticle.getContent().toString()+"!!");
       List<ArticleDTO> dtoList = pageArticle.getContent().stream()
               .map(tuple -> {
                   Article article = tuple.get(0 ,Article.class);
                   String nick = tuple.get(1, String.class);
                   article.setNick(nick);
                   return modelMapper.map(article, ArticleDTO.class);
               })
               .toList();
       log.info(dtoList+" dto! !!");
       int total = (int) pageArticle.getTotalElements();
       return PageResponseDTO.builder()
               .pageRequestDTO(pageRequestDTO)
               .dtoList(dtoList)
               .total(total)
               .build();
   }

    public PageResponseDTO searchArticles(PageRequestDTO pageRequestDTO){
        Pageable pageable = pageRequestDTO.getPageable("no");
        Page<Article> pageArticle = articleRepository.searchArticles(pageRequestDTO, pageable);

        List<ArticleDTO> dtoList = pageArticle.getContent().stream()
                .map(entity -> modelMapper.map(entity, ArticleDTO.class))
                .toList();

        int total = (int) pageArticle.getTotalElements();
        return PageResponseDTO.builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total(total)
                .build();
    }


    public ArticleDTO updateArtice (ArticleDTO articleDTO){
        Article article = modelMapper.map(articleDTO, Article.class);
        Article article1 = articleRepository.save(article);
        return modelMapper.map(article1, ArticleDTO.class);
    }

    @Transactional
    public  void  deleteArticle (int no){
       articleRepository.deleteById(no);
       articleRepository.deleteArticlesByParent(no);
    }


    //comment
    public ResponseEntity inserComment(ArticleDTO articleDTO){
        Article savedArticle =articleRepository.save(modelMapper.map(articleDTO,Article.class));

        return ResponseEntity.ok().body(savedArticle);
    }

    public List<ArticleDTO> selectComment(int parent){
        List<Tuple> lists =articleRepository.selectComments(parent);

        return lists.stream()
                .map(tuple -> {
                    Article article = tuple.get(0 ,Article.class);
                    String nick = tuple.get(1, String.class);
                    article.setNick(nick);
                    return modelMapper.map(article, ArticleDTO.class);
                })
                .toList();
    }

    public ResponseEntity deleteComment(int no){
        articleRepository.deleteById(no);

        Map<String, Object> map = new HashMap<>();
        map.put("sucesss", "1");

        return ResponseEntity.ok().body(map);
    }

    public ArticleDTO selectCommentByNo(int no){
        return modelMapper.map(articleRepository.findById(no), ArticleDTO.class) ;
    }

    public ResponseEntity updateComment(ArticleDTO commentDTO){
        Article article= articleRepository.save(modelMapper.map(commentDTO, Article.class));
        Map<String, Object> map = new HashMap<>();
        map.put("article", article);
        return  ResponseEntity.ok().body(map);
    }
}