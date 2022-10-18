package edu.global.ex.dto;


import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class BoardDTO {
    private Long bid;
    private String bwriter;
    private String btitle;
    private String bcontent;
    private long bhit;
    private long bgroup;
    private long bstep;
    private long bindent;
    private LocalDateTime regdate;
    private LocalDateTime moddate;
}
