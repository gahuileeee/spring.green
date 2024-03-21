package kr.co.sboard.service;

import kr.co.sboard.dto.ArticleDTO;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Article article = modelMapper.map(articleDTO, Article.class);
        log.info(article.toString());
        Article savedArticle= articleRepository.save(article);
        int ano = savedArticle.getNo();
        articleDTO.setNo(ano);

       fileService.fileUpload(articleDTO);
    }

    public ArticleDTO selectArticle(int no){
        return modelMapper.map(articleRepository.findById(no), ArticleDTO.class);
    }

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

    //comment
    public ResponseEntity inserComment(ArticleDTO articleDTO){
        Article savedArticle =articleRepository.save(modelMapper.map(articleDTO,Article.class));

        return ResponseEntity.ok().body(savedArticle);
    }

    public List<ArticleDTO> selectComment(int parent){
        return articleRepository.findArticlesByParent(parent).stream()
                .map(comment -> modelMapper.map(comment, ArticleDTO.class)).toList();
    }

    public ResponseEntity deleteComment(int no){
        articleRepository.deleteById(no);

        Map<String, Object> map = new HashMap<>();
        map.put("sucesss", "1");

        return ResponseEntity.ok().body(map);
    }
}