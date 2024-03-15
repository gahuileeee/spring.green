package kr.co.ch07.entity.board;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString (exclude = "article")
@Builder
@Entity
@Table(name = "board_comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int cno;
    private  String content;
    private LocalDateTime rdate;

    @ManyToOne
    @JoinColumn(name = "writer")
    private User user;

    @ManyToOne
    @JoinColumn(name = "parent")
    private Article article;
}
