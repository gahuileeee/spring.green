package kr.co.sboard.dto;

import lombok.*;
import org.springframework.stereotype.Service;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TermsDTO {
    private String terms;
    private String privacy;
    private String sms;
}
