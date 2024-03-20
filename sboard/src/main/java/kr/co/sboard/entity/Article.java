package kr.co.sboard.entity;

import jakarta.persistence.*;
import kr.co.sboard.dto.ArticleDTO;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name = "article")
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int no;

    public int parent;
    public int comment;
    public String cate;
    public String title;
    public String content;
    public int file;
    public int hit;
    public String writer;

    /*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer")
    public User user;
    */

    public String regip;

    @CreationTimestamp
    public LocalDateTime rdate;

    @OneToMany(mappedBy = "ano")
    private List<File> filesList;

}
