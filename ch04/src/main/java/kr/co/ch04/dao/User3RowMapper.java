package kr.co.ch04.dao;

import kr.co.ch04.dto.User3DTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User3RowMapper implements RowMapper<User3DTO> {
    /*
        -select 결과 처리 메서드
        -select 결과가 3개 이상이면 리스트로 생성
        -select 결과가 3개이면 해당 DTO로 생성
     */
    @Override
    public User3DTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        User3DTO user3DTO = new User3DTO();
        user3DTO.setUid(rs.getString("uid"));
        user3DTO.setName(rs.getString("name"));
        user3DTO.setBirth(rs.getString("birth"));
        user3DTO.setHp(rs.getString("hp"));
        user3DTO.setAddr(rs.getString("addr"));
        return user3DTO;
    }
}
