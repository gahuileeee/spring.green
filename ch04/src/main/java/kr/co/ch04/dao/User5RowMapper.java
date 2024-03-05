package kr.co.ch04.dao;

import kr.co.ch04.dto.User5DTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User5RowMapper implements RowMapper<User5DTO> {

    /*
        -select 결과 처리 메서드
        -select 결과가 5개 이상이면 리스트로 생성
        -select 결과가 5개이면 해당 DTO로 생성
     */
    @Override
    public User5DTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        User5DTO user5DTO = new User5DTO();
        user5DTO.setSeq(rs.getString("seq"));
        user5DTO.setName(rs.getString("name"));
        user5DTO.setGender(rs.getString("gender"));
        user5DTO.setAge(rs.getString("age"));
        user5DTO.setAddr(rs.getString("addr"));
        return user5DTO;
    }
}
