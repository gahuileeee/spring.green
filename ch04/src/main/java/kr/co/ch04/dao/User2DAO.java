package kr.co.ch04.dao;

import kr.co.ch04.dto.User2DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //컴포넌트 등록
public class User2DAO {

    @Autowired //bean에서 주입받기
    private JdbcTemplate jdbcTemplate;

    public  void insertUser2(User2DTO user2DTO){
        String sql= "INSERT INTO `user2` values (?,?,?,?)";

        Object[] params = {user2DTO.getUid(),
                           user2DTO.getName(),
                           user2DTO.getBirth(),
                            user2DTO.getAddr()
                        };

        jdbcTemplate.update(sql,params); //update로 insert, delete 등 할 수 있음
    }

    public  User2DTO selectUser2(String uid){
        String sql = "SELECT * FROM `user2` WHERE `uid`=?";
        return jdbcTemplate.queryForObject(sql, new User2RowMapper(), uid);
    }

    public List<User2DTO> selectUser2s(){
        String sql="SELECT * FROM `user2`";
        List<User2DTO> uers= jdbcTemplate.query(sql, new User2RowMapper());
        return  uers;
    }

    public  void updateUser2(User2DTO user2DTO){
        try {

            String sql="UPDATE `user2` set `name`=?, `birth`=?, `addr`=?  WHERE `uid`=? ";
            Object [] params = {user2DTO.getName(), user2DTO.getBirth(), user2DTO.getAddr(),
                    user2DTO.getUid() };
            System.out.println(user2DTO);
            System.out.println(params.toString());
            jdbcTemplate.update(sql, params);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public  void deleteUser2(String uid){
        String sql = "DELETE FROM `user2` WHERE `uid`=?";
        jdbcTemplate.update(sql,uid);
    }
}
