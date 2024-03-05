package kr.co.ch04.dao;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import kr.co.ch04.dto.User1DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //컴포넌트 등록
public class User1DAO {

    @Autowired //bean에서 주입받기
    private JdbcTemplate jdbcTemplate;

    public  void insertUser1(User1DTO user1DTO){
        String sql= "INSERT INTO `user1` values (?,?,?,?,?)";

        Object[] params = {user1DTO.getUid(),
                           user1DTO.getName(),
                           user1DTO.getBirth(),
                           user1DTO.getHp(),
                           user1DTO.getAge()
                        };

        jdbcTemplate.update(sql,params); //update로 insert, delete 등 할 수 있음
    }

    public  User1DTO selectUser1(String uid){
        String sql = "SELECT * FROM `user1` WHERE `uid`=?";
        return jdbcTemplate.queryForObject(sql, new User1RowMapper(), uid);
    }

    public List<User1DTO> selectUser1s(){
        String sql="SELECT * FROM `user1`";
        List<User1DTO> uers= jdbcTemplate.query(sql, new User1RowMapper());
        return  uers;
    }
    
    public  void updateUser1(User1DTO user1DTO){
        String sql="UPDATE  `user1` SET  `name`=?, `birth`=?, `hp`=?, `age`=? WHERE `uid`=?";
        System.out.println(user1DTO);
        Object [] params = {user1DTO.getName(), user1DTO.getBirth(), user1DTO.getHp(), user1DTO.getAge(),
        user1DTO.getUid()};
        jdbcTemplate.update(sql, params);
    }

    public  void deleteUser1(String uid){
        String sql = "DELETE FROM `user1` WHERE `uid`=?";
        jdbcTemplate.update(sql,uid);
    }
}
