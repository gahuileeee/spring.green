package kr.co.ch04.dao;

import kr.co.ch04.dto.User4DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //컴포넌트 등록
public class User4DAO {

    @Autowired //bean에서 주입받기
    private JdbcTemplate jdbcTemplate;

    public  void insertUser4(User4DTO user4DTO){
        String sql= "INSERT INTO `user4` values (?,?,?,?,?,?)";

        Object[] params = {user4DTO.getUid(),
                           user4DTO.getName(),
                           user4DTO.getGender(),
                           user4DTO.getAge(),
                            user4DTO.getHp(),
                            user4DTO.getAddr()
                        };

        jdbcTemplate.update(sql,params); //update로 insert, delete 등 할 수 있음
    }

    public  User4DTO selectUser4(String uid){
        String sql = "SELECT * FROM `user4` WHERE `uid`=?";
        return jdbcTemplate.queryForObject(sql, new User4RowMapper(), uid);
    }

    public List<User4DTO> selectUser4s(){
        String sql="SELECT * FROM `user4`";
        List<User4DTO> users= jdbcTemplate.query(sql, new User4RowMapper());
        return  users;
    }

    public  void updateUser4(User4DTO user4DTO){
        try {

            String sql="UPDATE `user4` set `name`=?, `gender`=?, `age`=?, `hp`=?, `addr`=?  WHERE `uid`=? ";
            Object [] params = {user4DTO.getName(), user4DTO.getGender(),
                    user4DTO.getAge(),
                    user4DTO.getHp(), user4DTO.getAddr(),
                    user4DTO.getUid() };
            jdbcTemplate.update(sql, params);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public  void deleteUser4(String uid){
        String sql = "DELETE FROM `user4` WHERE `uid`=?";
        jdbcTemplate.update(sql,uid);
    }
}
