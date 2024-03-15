package kr.co.ch07.service;

import kr.co.ch07.dto.User1DTO;
import kr.co.ch07.entity.User1;
import kr.co.ch07.repository.User1Repository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
@Service
public class User1Service {
    /*
    //생성자 주입
    private  final  User1Repository repository;

    public void insertUser1(User1DTO user1DTO){
        //DTO를 entity로 변환
        User1 user1 = user1DTO.toEntity();
        repository.save(user1);
    }

    public User1DTO  selectUser1(String uid){
        Optional<User1> result= repository.findById(uid);
        return result.get().toDTO();
    }

    public List<User1DTO> selectUser1s(){
        List<User1> user1s = repository.findAll();
        List<User1DTO> user1DTOS =user1s.stream()
                .map(entity -> User1DTO.builder()
                        .uid(entity.getUid())
                        .name(entity.getName())
                        .birth(entity.getBirth())
                        .hp(entity.getHp())
                        .age(entity.getAge())
                        .build())
                .collect(Collectors.toList());

        List<User1DTO> user1DTOS = new ArrayList<>();

        for( User1 user1 : user1s){
            user1DTOS.add(user1.toDTO());
        }


        return user1DTOS;
    }

    public void deleteUser1(String uid){
        repository.deleteById(uid);
    }

    public void updateUser1(User1DTO user1DTO){
        repository.save(user1DTO.toEntity());
    }
    */
}
