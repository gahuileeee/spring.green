package kr.co.ch04.dao;

import kr.co.ch04.dto.User4DTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User4RowMapper implements RowMapper<User4DTO> {
    /*
        -select 결과 처리 메서드
        -select 결과가 4개 이상이면 리스트로 생성
        -select 결과가 4개이면 해당 DTO로 생성
     */
    @Override
    public User4DTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        User4DTO user4DTO = new User4DTO();
        user4DTO.setUid(rs.getString("uid"));
        user4DTO.setName(rs.getString("name"));
        user4DTO.setGender(rs.getString("gender"));
        user4DTO.setAge(rs.getString("age"));
        user4DTO.setHp(rs.getString("hp"));
        user4DTO.setAddr(rs.getString("addr"));
        return user4DTO;
    }
}
