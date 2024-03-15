package kr.co.ch07.repository;

import kr.co.ch07.dto.User1DTO;
import kr.co.ch07.entity.User1;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//<Entity, Einty @ID의 자료형>
public interface User1Repository extends JpaRepository<User1, String> {
    /*
    
    //사용자 정의 쿼리 메소드
    //find는 select이고 by는 where 절
    public User1 findUser1ByUid(String uid);
    public List<User1> findUser1ByName(String name);
    public List<User1> findUser1ByNameNot(String name);

    public User1 findUser1byUidAndName(String uid, String name);
    public List<User1> findUser1byUidOrName(String uid, String name);

    public  List<User1> findUser1ByAgeGreaterThan(String age);
    public  List<User1> findUser1ByAgeGreaterThanEqual(String age);
    public  List<User1> findUser1ByAgeLessThan(String age);
    public  List<User1> findUser1ByAgeLessThanEqual(String age);
    public  List<User1> findUser1ByAgeBetween(String low, String high);

    public  List<User1> findUser1ByNameLike(String name);
    public  List<User1> findUser1ByNameContains(String name);
    public  List<User1> findUser1ByNameStartsWith(String name);
    public  List<User1> findUser1ByNameEndsWith(String name);

    public  List<User1> findUser1ByOrderByName();
    public  List<User1> findUser1ByOrderByAgeAsc();
    public  List<User1> findUser1ByOrderByAgeDesc();
    public  List<User1> findUser1ByAgeGreaterThanOrderByAgeDesc(String age);

    public int countUser1ByUid(String uid);
    public int countUser1ByName(String name);

    //JPQL 정의
    //method 이름 마음대로 정함
   // @Query("select u1 from User1 as u1 where u1.age<30")
   // public List<User1> selectUser1UnderAge30();
    
    @Query("select u1 from User1 as u1 where u1.name = ?1")
    //?1 ?2 ?3 처럼 파라미터가 ? 더하기 순서로 되어 있음
    public List<User1> selectUser1ByName(String name);

    @Query("select u1 from User1 as u1 where u1.name= :name")
    public List<User1> selectUser1UByNameParam(@Param("name") String name);

    @Query("select u1.uid, u1.name, u1.age from User1 as u1 where u1.uid = :uid")
    public List<User1> selectUser1ByUid(@Param("uid") String uid);

     */
}
