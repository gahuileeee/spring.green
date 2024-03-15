package kr.co.ch07.dto;

import kr.co.ch07.entity.User1;
import lombok.*;

@Builder
@Data
public class User1DTO {
    private String uid;
    private String name;
    private String birth;
    private String hp;
    private String age;

    //Entity 변환 method
    public User1 toEntity(){
        return  User1.builder()
                .uid(uid)
                .name(name)
                .birth(birth)
                .age(age)
                .hp(hp)
                .build();
    }
}
