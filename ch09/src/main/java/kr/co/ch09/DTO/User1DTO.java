package kr.co.ch09.DTO;

import kr.co.ch09.entity.User1;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User1DTO {
    private String uid;
    private String name;
    private String birth;
    private String hp;
    private int age;

    public User1 toUser1Entity(){
        return  User1.builder()
                        .uid(uid)
                        .name(name)
                        .birth(birth)
                        .hp(hp)
                        .age(age)
                        .build();
    }
}
