package kr.co.sboard.dto;

import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfigDTO {

    private  String cate;
    private  String boardName;
    private  String admin;
    private  int total;
    private LocalDateTime createDate;
}
