package kr.co.ch07.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.co.ch07.dto.User1DTO;
import lombok.*;

@Getter //getter만 설정, setter 필요 없대(값의 수정을 막음)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity //entity 객체 생성
@Table( name = "user1") //매핑 테이블 설정
public class User1 {

    @Id
    private String uid; //pk설정

    @Column(name = "name") //매핑 컬럼 설정, 생략 가능
    private String name;

    @Column(name = "birth")
    private String birth;

    @Column(name = "hp")
    private String hp;

    @Column(name = "age")
    private String age;
    
    // DTO 변환 메서드
    public User1DTO toDTO(){
        return User1DTO.builder()
                .uid(uid)
                .name(name)
                .birth(birth)
                .hp(hp)
                .age(age)
                .build();
    }
}
