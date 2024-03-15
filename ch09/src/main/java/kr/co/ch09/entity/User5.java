package kr.co.ch09.entity;

import jakarta.persistence.*;
import kr.co.ch09.DTO.User2DTO;
import kr.co.ch09.DTO.User5DTO;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Entity
@Builder
@Table(name = "user5")
public class User5 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String seq;
    private String name;
    private String gender;
    private int age;
    private String addr;

    public User5DTO toUser5DTO(){
        if(seq != null){
            return User5DTO.builder()
                    .seq(seq)
                    .name(name)
                    .gender(gender)
                    .age(age)
                    .addr(addr)
                    .build();
        }else{
            return User5DTO.builder()
                    .name(name)
                    .gender(gender)
                    .age(age)
                    .addr(addr)
                    .build();
        }

    }

}
