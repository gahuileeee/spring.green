package kr.co.ch04.dao;

import kr.co.ch04.dao.User5RowMapper;
import kr.co.ch04.dto.User5DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //컴포넌트 등록
public class User5DAO {

    @Autowired //bean에서 주입받기
    private JdbcTemplate jdbcTemplate;
    public  void insertUser5(User5DTO user5DTO){
        String sql= "INSERT INTO `user5` values (?,?,?,?,?)";

        Object[] params = {user5DTO.getSeq(),
                           user5DTO.getName(),
                           user5DTO.getGender(),
                           user5DTO.getAge(),
                            user5DTO.getAddr()
                        };

        jdbcTemplate.update(sql,params); //update로 insert, delete 등 할 수 있음
    }
    public  User5DTO selectUser5(String uid){
        String sql = "SELECT * FROM `user5` WHERE `seq`=?";
        return jdbcTemplate.queryForObject(sql, new User5RowMapper(), uid);

    }

    public List<User5DTO> selectUser5s(){
        String sql="SELECT * FROM `user5`";
        List<User5DTO> users= jdbcTemplate.query(sql, new User5RowMapper());
        return  users;
    }

    public  void updateUser5(User5DTO user5DTO){
        try {

            String sql="UPDATE `user5` set `name`=?, `gender`=?, `age`=?,  `addr`=?  WHERE `seq`=? ";
            Object [] params = {user5DTO.getName(), user5DTO.getGender(),
                    user5DTO.getAge(),
                     user5DTO.getAddr(),
                    user5DTO.getSeq() };
            jdbcTemplate.update(sql, params);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public  void deleteUser5(String uid){
        String sql = "DELETE FROM `user5` WHERE `seq`=?";
        jdbcTemplate.update(sql,uid);
    }
}
