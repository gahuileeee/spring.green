package kr.co.ch10.service;

import kr.co.ch10.dto.UserDTO;
import kr.co.ch10.entity.User;
import kr.co.ch10.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class UserService{

    private  final UserRepository repository;

    public void insertUser(UserDTO userDTO){
        repository.save(userDTO.toEntity());
    }

    public UserDTO selectUser(String uid){
        return  repository.findById(uid).get().toDTO();
    }

    public List<UserDTO> selectUsers(){
        return repository.findAll().stream().map(user -> user.toDTO()).collect(Collectors.toList());
    }

    public UserDTO updateUser(UserDTO userDTO){
        //수정
        repository.save(userDTO.toEntity());
        //수정한 사용자 조회 및 반환
        return repository.findById(userDTO.getUid()).get().toDTO();
    }

    public ResponseEntity deleteUser(String uid){
        repository.deleteById(uid);
        Optional<User> result =repository.findById(uid);
        if(result.isPresent()){
            //사용자가 존재하면 삭제 후 삭제한 사용자 정보 반환
            return ResponseEntity.ok().body(result.get());
        }else{
            //사용자가 존재하지 않으면 NOT FOUND status + user not fount 메세지 return
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
        }
    }

}
