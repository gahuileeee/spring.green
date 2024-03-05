package kr.co.ch04.dao;

import kr.co.ch04.dto.User3DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //컴포넌트 등록
public class User3DAO {

    @Autowired //bean에서 주입받기
    private JdbcTemplate jdbcTemplate;

    public  void insertUser3(User3DTO user3DTO){
        String sql= "INSERT INTO `user3` values (?,?,?,?,?)";

        Object[] params = {user3DTO.getUid(),
                           user3DTO.getName(),
                           user3DTO.getBirth(),
                            user3DTO.getHp(),
                            user3DTO.getAddr()
                        };

        jdbcTemplate.update(sql,params); //update로 insert, delete 등 할 수 있음
    }

    public  User3DTO selectUser3(String uid){
        String sql = "SELECT * FROM `user3` WHERE `uid`=?";
        return jdbcTemplate.queryForObject(sql, new User3RowMapper(), uid);
    }

    public List<User3DTO> selectUser3s(){
        String sql="SELECT * FROM `user3`";
        List<User3DTO> users= jdbcTemplate.query(sql, new User3RowMapper());
        return  users;
    }

    public  void updateUser3(User3DTO user3DTO){
        try {

            String sql="UPDATE `user3` set `name`=?, `birth`=?, `hp`=?, `addr`=?  WHERE `uid`=? ";
            Object [] params = {user3DTO.getName(), user3DTO.getBirth(), user3DTO.getHp(), user3DTO.getAddr(),
                    user3DTO.getUid() };
            jdbcTemplate.update(sql, params);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public  void deleteUser3(String uid){
        String sql = "DELETE FROM `user3` WHERE `uid`=?";
        jdbcTemplate.update(sql,uid);
    }
}
