package kr.co.sboard.mapper;

import kr.co.sboard.dto.TermsDTO;
import kr.co.sboard.dto.UserDTO;
import kr.co.sboard.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    public TermsDTO selectTerms();
    public void insertUser(UserDTO userDTO);
    public int selectCountUser(@Param("type") String type, @Param("value") String value);
    public void selectUsers();
    public void updateUser();
    public void deleteUser();

    public  UserDTO findByNameAndEmail(UserDTO userDTO);
    public  UserDTO findByUidAndEmail(UserDTO userDTO);
    public  UserDTO findByEmail(String email);

    public  void updateUserPassword(UserDTO userDTO);
}