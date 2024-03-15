package kr.co.ch09.service;

import kr.co.ch09.DTO.User5DTO;
import kr.co.ch09.entity.User5;
import kr.co.ch09.repository.User5Repository;
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
public class User5Service {

    private  final User5Repository repository;

    public User5 insertUser5(User5DTO userDTO){
        return repository.save(userDTO.toUser5Entity());
    }

    public User5DTO selectUser5(String uid){
        return  repository.findById(uid).get().toUser5DTO();
    }

    public List<User5DTO> selectUser5s(){
        return repository.findAll().stream().map(user -> user.toUser5DTO()).collect(Collectors.toList());
    }

    public User5DTO updateUser5(User5DTO userDTO){
        //수정
        repository.save(userDTO.toUser5Entity());
        //수정한 사용자 조회 및 반환
        return repository.findById(userDTO.getSeq()).get().toUser5DTO();
    }

    public ResponseEntity deleteUser5(String seq){
        repository.deleteById(seq);
        Optional<User5> result =repository.findById(seq);
        if(result.isPresent()){
            //사용자가 존재하면 삭제 후 삭제한 사용자 정보 반환
            return ResponseEntity.ok().body(result.get());
        }else{
            //사용자가 존재하지 않으면 NOT FOUND status + user not fount 메세지 return
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
        }
    }

}
