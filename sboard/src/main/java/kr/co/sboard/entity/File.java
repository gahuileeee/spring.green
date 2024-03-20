package kr.co.sboard.entity;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import kr.co.sboard.dto.FileDTO;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "file")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int fno;
    private  int ano;
    private  String oName;
    private  String sName;
    private  int download;

    @CreationTimestamp
    private LocalDateTime rdate;

    public FileDTO toDTO(){
        return  FileDTO.builder()
                .fno(fno)
                .ano(ano)
                .oName(oName)
                .sName(sName)
                .download(download)
                .rdate(rdate)
                .build();
    }
}
