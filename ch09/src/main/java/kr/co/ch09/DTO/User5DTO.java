package kr.co.ch09.DTO;

import kr.co.ch09.entity.User2;
import kr.co.ch09.entity.User5;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User5DTO {
    private String seq;
    private String name;
    private String gender;
    private int age;
    private String addr;

    public User5 toUser5Entity() {
        if (seq != null) {
            return User5.builder()
                    .seq(seq)
                    .name(name)
                    .gender(gender)
                    .age(age)
                    .addr(addr)
                    .build();
        } else {
            return User5.builder()
                    .name(name)
                    .gender(gender)
                    .age(age)
                    .addr(addr)
                    .build();
        }
    }
}
