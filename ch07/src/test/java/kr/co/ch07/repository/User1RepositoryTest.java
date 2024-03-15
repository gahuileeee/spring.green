package kr.co.ch07.repository;

import kr.co.ch07.entity.User1;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@SpringBootTest
class User1RepositoryTest {
    /*
    test할 때 autowire이 아닌 all~이걸로 하면
    생성자 주입은 No parameterResolver registered for parameter error 발생
     */

    /*
    private final User1Repository repository;

    @Autowired
    User1RepositoryTest(User1Repository repository) {
        this.repository = repository;
    }


    @DisplayName("user1 등록")
    public void insertUser1(){
        //given (entity 생성)
        User1 user1 = User1.builder()
                .uid("j102")
                .name("김유신동생")
                .birth("1999-06-27")
                .age("21")
                .hp("010-1234-5678")
                .build();
        
        //when : entity 저장
        repository.save(user1);

        //then

    }


    @DisplayName("user1 조회")
    public void selectUser1(){

        //given 조회 아이디
        String uid= "j101";

        //when : 조회하기
        // Optinal은  null값 처리를 편리하게 하도록 만든 자료형임
        Optional<User1> result = repository.findById(uid);
        User1 user1 =result.get();
        log.info(user1.toString());

    }

    @DisplayName("user1 목록")
    public void selectUser1s(){

    }

    @DisplayName("user1 삭제")
    public void deleteUser1(){

    }
    
    @DisplayName("user1 스정")
    public void updateUser1(){

    }

    //사용자 정의 쿼리 메소드
    //find는 select이고 by는 where 절
    @Test
    public void findUser1ByUid(){
        User1 user1 =repository.findUser1ByUid("j101");
        log.warn(user1.toString());
    }
    @Test
    public void findUser1ByName(){
        List<User1> user1s = repository.findUser1ByName("김유신");
        log.warn(user1s.toString());
    };
    @Test
    public void findUser1ByNameNot(){
        log.warn(repository.findUser1ByNameNot("깅유신").toString());
    };
    @Test
    public void findUser1byUidAndName(){
        log.warn(repository.findUser1byUidAndName("j101","김유신").toString());
    };
    @Test
    public void findUser1byUidOrName(){
        log.warn(repository.findUser1byUidOrName("a101","김유신").toString());
    };
    @Test
    public void findUser1ByAgeGreaterThan(){
        log.warn(repository.findUser1ByAgeGreaterThan("30").toString());
    };
    @Test
    public  void findUser1ByAgeGreaterThanEqual(){
        log.warn(repository.findUser1ByAgeGreaterThanEqual("30").toString());
    };
    @Test
    public  void findUser1ByAgeLessThan(){
        log.warn(repository.findUser1ByAgeLessThan("30").toString());
    };
    @Test
    public  void findUser1ByAgeLessThanEqual(){
        log.warn(repository.findUser1ByAgeLessThan("30").toString());
    };
    @Test
    public  void findUser1ByAgeBetween(){
        log.warn(repository.findUser1ByAgeBetween("10","30").toString());
    };
    @Test
    public  void findUser1ByNameLike(){};
    @Test
    public  void findUser1ByNameContains(){};
    @Test
    public  void findUser1ByNameStartsWith(){
        log.warn(repository.findUser1ByNameStartsWith("김").toString());
    };
    @Test
    public  void findUser1ByNameEndsWith(){
        log.warn(repository.findUser1ByNameEndsWith("신").toString());
    };
    @Test
    public  void findUser1ByOrderByName(){
        log.warn(repository.findUser1ByOrderByName().toString());
    };
    @Test
    public  void findUser1ByOrderByAgeAsc(){
        log.warn(repository.findUser1ByOrderByAgeAsc().toString());
    };
    @Test
    public  void findUser1ByOrderByAgeDesc(){
        log.warn(repository.findUser1ByOrderByAgeDesc().toString());
    };
    @Test
    public  void findUser1ByAgeGreaterThanOrderByAgeDesc(){
        log.warn(repository.findUser1ByAgeGreaterThanOrderByAgeDesc("30").toString());
    };

    @Test
    public void countUser1ByUid(){
        int a = repository.countUser1ByUid("j101");
        log.warn("countUser1ByUid() "+a);
    };
    @Test
    public void countUser1ByName(){
        int a = repository.countUser1ByName("김유신");
        log.warn("countUser1ByName() "+a);
    };

    //JPQL 정의


    @Test
    //?1 ?2 ?3 처럼 파라미터가 ? 더하기 순서로 되어 있음
    public void selectUser1ByName(){
        log.warn(repository.selectUser1ByName("김유신").toString());
    };

    @Test
    public void selectUser1UByNameParam(){
        log.warn(repository.selectUser1UByNameParam("김유신").toString());
    };

    @Test
    public void selectUser1ByUid(){
        log.warn(repository.selectUser1ByUid("j101").toString());
    };
 */
}