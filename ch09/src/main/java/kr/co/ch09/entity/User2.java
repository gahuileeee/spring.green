package kr.co.ch09.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.co.ch09.DTO.User1DTO;
import kr.co.ch09.DTO.User2DTO;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Entity
@Builder
@Table(name = "user2")
public class User2 {

    @Id
    private String uid;
    private String name;
    private String birth;
    private String addr;

    public User2DTO toUser2DTO(){
        return User2DTO.builder()
                        .uid(uid)
                        .name(name)
                        .birth(birth)
                        .addr(addr)
                        .build();
    }

}
