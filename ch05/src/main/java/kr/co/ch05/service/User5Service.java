package kr.co.ch05.service;

import kr.co.ch05.dto.User5DTO;
import kr.co.ch05.mapper.User5Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class User5Service {
    private  final User5Mapper mapper;

    @Autowired
    public User5Service(User5Mapper mapper) {
        this.mapper = mapper;
    }

    public void  insertUser5(User5DTO user5DTO){
        mapper.insertUser5(user5DTO);
    };

    public User5DTO  selectUser5(String uid){
        return mapper.selectUser5(uid);
    };

    public List<User5DTO> selectUser5s(){
        return  mapper.selectUser5s();
    };

    public void  deleteUser5(String uid){
        mapper.deleteUser5(uid);
    };

    public void  updateUser5(User5DTO user5DTO){
        mapper.updateUser5(user5DTO);
    };
}
