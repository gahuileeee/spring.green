package kr.co.ch09.DTO;

import jakarta.validation.constraints.*;
import kr.co.ch09.entity.User1;
import kr.co.ch09.entity.User2;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

@Data
@Builder
public class User2DTO {
    @NotBlank // null, "", " " 모두 허용 안함
    private String uid;

    @NotEmpty // null, "" 허용 안함
    private String name;

    @NotNull // null 허용 안함
    private String birth;

    @Email //email 형식 검사
    private String email;

    @Size(min=1, max = 100) //최소값, 최대값 설정
    private  int age;
    private String addr;

    public User2 toUser2Entity(){
        return  User2.builder()
                        .uid(uid)
                        .name(name)
                        .birth(birth)
                        .addr(addr)
                        .build();
    }
}
