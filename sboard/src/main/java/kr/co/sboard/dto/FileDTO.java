package kr.co.sboard.dto;

import kr.co.sboard.entity.File;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FileDTO {
    private  int fno;
    private  int ano;
    private  String oName;
    private  String sName;
    private  int download;
    private LocalDateTime rdate;

    public File toEntity(){
        return  File.builder()
                .fno(fno)
                .ano(ano)
                .oName(oName)
                .sName(sName)
                .download(download)
                .rdate(rdate)
                .build();
    }
}
