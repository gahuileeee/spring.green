package kr.co.sboard.repository.custom;

import com.querydsl.core.Tuple;
import kr.co.sboard.dto.PageRequestDTO;
import kr.co.sboard.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ArticleRepositoryCustom {

    public Page<Article> searchArticles(PageRequestDTO pageRequestDTO, Pageable pageable);

    // PageRequestDTO에
    //  private  String type;
    //  private  String keyword; 추가

    public Page<Tuple> selectArticles(PageRequestDTO pageRequestDTO, Pageable pageable);

    //댓글조회
    public List<Tuple> selectComments(int no);
}
