package kr.co.ch04.dao;

import kr.co.ch04.dto.User2DTO;
import kr.co.ch04.dto.User2DTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User2RowMapper implements RowMapper<User2DTO> {
    /*
        -select 결과 처리 메서드
        -select 결과가 2개 이상이면 리스트로 생성
        -select 결과가 2개이면 해당 DTO로 생성
     */
    @Override
    public User2DTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        User2DTO user2DTO = new User2DTO();
        user2DTO.setUid(rs.getString("uid"));
        user2DTO.setName(rs.getString("name"));
        user2DTO.setBirth(rs.getString("birth"));
        user2DTO.setAddr(rs.getString("addr"));
        return user2DTO;
    }
}
