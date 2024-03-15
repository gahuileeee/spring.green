package kr.co.ch07.entity.board;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "board_article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int no;

    private String title;
    private  String content;

    @ManyToOne (fetch = FetchType.LAZY)
    // User1 의 writer는 여러 곳에서 참조하니 1대 다 관계
    @JoinColumn(name = "writer")
    //Lazy는 article이 생성될 때 user도 생성된다
    //User가 생성될 때 article은 생성되지 않음
    // fetch mode는 생략해도 됨
    private User user;

    @OneToOne (mappedBy = "article")
    //File에도 OneToOne 설정
    //mapperBy = 주인은 article이다
    //One으로 시작되는 친구는 mapperBy 써 줘야함
    private File file;
    
    @OneToMany(mappedBy = "article") //foreign key 설정
    //하나의 글에 여러개를 작성할 수 있으니까
    //file도 그렇긴 한데 여러 예시 보여주시려고 one to one으로 하심
    private  List<Comment> comment;

    @CreationTimestamp
    private LocalDateTime rdate;

}
