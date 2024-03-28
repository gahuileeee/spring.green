package kr.co.sboard.repository.impl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.sboard.dto.ArticleDTO;
import kr.co.sboard.dto.PageRequestDTO;
import kr.co.sboard.entity.Article;
import kr.co.sboard.entity.QArticle;
import kr.co.sboard.entity.QUser;
import kr.co.sboard.repository.custom.ArticleRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ArticleRepositoryImpl implements ArticleRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private QArticle qArticle = QArticle.article;
    private QUser qUser= QUser.user;
    private final ModelMapper modelMapper;

    @Override
    public Page<Article> searchArticles(PageRequestDTO pageRequestDTO, Pageable pageable) {
        String cate =pageRequestDTO.getCate();
        String type =pageRequestDTO.getType();
        String keyword =pageRequestDTO.getKeyword();

        BooleanExpression expression = null;
        //검색 종류에 따른 where 절 표현식 생성
        if(type.equals("title")){
            expression = qArticle.cate.eq(cate).and(qArticle.title.contains(keyword));
            log.info("expression : " +expression);
        }else if(type.equals("content")){
            expression = qArticle.cate.eq(cate).and(qArticle.content.contains(keyword));
            log.info("expression : " +expression);
        }else if(type.equals("title_content")){
            expression =qArticle.cate.eq(cate).and(qArticle.title.contains(keyword).or(qArticle.cate.contains(keyword)));
            log.info("expression : " +expression);
        }else if(type.equals("writer")) {

            expression = qArticle.cate.eq(cate).and(qArticle.parent.eq(0)).and(qUser.nick.contains(keyword));

        }

        //부가적인 Query 실행 정보를 처리하기 위해 fetchResults()로 실행
        QueryResults<Tuple> results = jpaQueryFactory
                .select(qArticle, qUser.nick)
                .from(qArticle)
                .join(qUser)
                .on(qArticle.writer.eq(qUser.uid))
                .where(expression)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(qArticle.no.desc())
                .fetchResults();
        
        List<Tuple> tupleContent =results.getResults();

        //tuple -> Article entity로 변환 (여기서 안 하고, service 단에서 처리 해도 괜찮을 것 같습니다.)
        List<Article> content = tupleContent.stream()
                .map(tuple -> {
                    Article article = tuple.get(0 ,Article.class);
                    String nick = tuple.get(1, String.class);
                    article.setNick(nick);
                    return modelMapper.map(article, Article.class);
                })
                .toList();

        long total = results.getTotal();

        //페이징 처리를 위해 page 객체 리턴
      return  new PageImpl<>(content, pageable, total);
    }
    
    //join해서 전체 조회
    @Override
    public Page<Tuple> selectArticles(PageRequestDTO pageRequestDTO, Pageable pageable) {
        String cate =pageRequestDTO.getCate();


        //부가적인 Query 실행 정보를 처리하기 위해 fetchResults()로 실행
        QueryResults<Tuple> results = jpaQueryFactory
                .select(qArticle, qUser.nick)
                .from(qArticle)
                .join(qUser)
                .on(qArticle.writer.eq(qUser.uid))
                .where(qArticle.cate.eq(cate).and(qArticle.parent.eq(0)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(qArticle.no.desc())
                .fetchResults();

        List<Tuple> content =results.getResults();
        long total = results.getTotal();

        //페이징 처리를 위해 page 객체 리턴
        return  new PageImpl<>(content, pageable, total);
    }

    //댓글
    @Override
    public List<Tuple> selectComments(int no) {

        //부가적인 Query 실행 정보를 처리하기 위해 fetchResults()로 실행
        QueryResults<Tuple> results = jpaQueryFactory
                .select(qArticle, qUser.nick)
                .from(qArticle)
                .join(qUser)
                .on(qArticle.writer.eq(qUser.uid))
                .where(qArticle.parent.eq(no))
                .orderBy(qArticle.no.desc())
                .fetchResults();

        return results.getResults();
    }
}
