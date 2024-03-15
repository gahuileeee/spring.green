package kr.co.ch09.controller;

import kr.co.ch09.DTO.User5DTO;
import kr.co.ch09.entity.User5;
import kr.co.ch09.service.User5Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class User5Controller {

    private  final User5Service service;

    @GetMapping("/user5")
    public List<User5DTO> list(){
        return service.selectUser5s();
    }

    @GetMapping("/user5/{seq}")
    public User5DTO user5(@PathVariable("seq")String seq){
        return service.selectUser5(seq);
    }

    @PostMapping("/user5")
    public ResponseEntity<User5DTO> register(User5DTO user5DTO){
        User5 user5 = service.insertUser5(user5DTO);
        return ResponseEntity.ok().body(service.selectUser5(user5.getSeq()));
    }

    @PutMapping("/user5")
    public ResponseEntity<User5DTO> modify(User5DTO user5DTO){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.updateUser5(user5DTO));
    }

    @DeleteMapping("/user5/{uid}")
    public ResponseEntity delete(@PathVariable("uid") String uid){
        return service.deleteUser5(uid);
    }

}
