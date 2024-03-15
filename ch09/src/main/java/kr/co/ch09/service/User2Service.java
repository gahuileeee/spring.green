package kr.co.ch09.service;

import kr.co.ch09.DTO.User2DTO;
import kr.co.ch09.entity.User2;
import kr.co.ch09.repository.User2repository;
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
public class User2Service {

    private  final User2repository repository;

    public void insertUser2(User2DTO userDTO){
        repository.save(userDTO.toUser2Entity());
    }

    public User2DTO selectUser2(String uid){
        return  repository.findById(uid).get().toUser2DTO();
    }

    public List<User2DTO> selectUser2s(){
        return repository.findAll().stream().map(user -> user.toUser2DTO()).collect(Collectors.toList());
    }

    public User2DTO updateUser2(User2DTO userDTO){
        //수정
        repository.save(userDTO.toUser2Entity());
        //수정한 사용자 조회 및 반환
        return repository.findById(userDTO.getUid()).get().toUser2DTO();
    }

    public ResponseEntity deleteUser2(String uid){
        repository.deleteById(uid);
        Optional<User2> result =repository.findById(uid);
        if(result.isPresent()){
            //사용자가 존재하면 삭제 후 삭제한 사용자 정보 반환
            return ResponseEntity.ok().body(result.get());
        }else{
            //사용자가 존재하지 않으면 NOT FOUND status + user not fount 메세지 return
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
        }
    }

}
