package kr.co.ch06.mapper;

import kr.co.ch06.dto.User1DTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class User1MapperTest {

    @Autowired
    private User1Mapper mapper;

    //@Test //이거 해줘야지 해당 것이 test됨
    @DisplayName("user1 등록") //이름지어주기
    void insertUser1() {
        log.info("insertUser1...");
        // 테스트 코드 패턴 : given = when = then

        // given : 테스트를 실행할 준비 단계
        User1DTO user1DTO = User1DTO.builder()
                .uid("j101")
                .name("홍길동")
                .birth("1998-03-11")
                .hp("010-1234-1111")
                .age(22)
                .build();

        //when
        mapper.insertUser1(user1DTO);

        //then 테스트 결과를 검증하는 단계
        User1DTO resultUser1 = mapper.selectUser1(user1DTO.getUid());
        Assertions.assertEquals(user1DTO.getUid(), resultUser1.getUid());
        //등록 데이터와 조회 데이터가 같은지 확인
    }


    @DisplayName("user1 조회")
    void selectUser1() {
        log.debug("selectUser1...");
        //given
        String uid = "j102";

        //when
        User1DTO user1DTO= mapper.selectUser1(uid);

        //then (특별한 기능이 있기 보다는 가독성이 좋음)
        // false가 되면 바로 빨간불이 뜸
        assertEquals(uid, user1DTO.getUid());
    }


    @DisplayName("user1 목록")
    void selectUser1s() {
        log.debug("selectUser1s...");

        //given
        List<User1DTO> users =null;
        //when
        users = mapper.selectUser1s();

        //then
        assertFalse(users.isEmpty());
    }


    @DisplayName("user1 수정")
    void updateUser1() {
        log.debug("updateUser1...");
        //given
        User1DTO user1DTO = User1DTO.builder()
                .uid("j101")
                .name("홍길복")
                .birth("1998-03-11")
                .hp("010-1234-1222")
                .age(44)
                .build();
        //when
        mapper.updateUser1(user1DTO);

        //then
        assertEquals(user1DTO.getName(), mapper.selectUser1(user1DTO.getUid()).getName());
    }

    @Test
    @DisplayName("user1 삭제")
    void deleteUser1() {
        log.debug("deleteUser1...");
        //given
        String uid ="j101";
        //when
        mapper.deleteUser1(uid);
        //then
        User1DTO result = mapper.selectUser1(uid);
        assertNull(result);
    }
}