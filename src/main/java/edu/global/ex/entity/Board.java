package edu.global.ex.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "mvc_board")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Board extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bid;

    @Column(length = 20, nullable = false)
    private String bwriter;

    @Column(length = 100, nullable = false)
    private String btitle;

    @Column(length = 1200, nullable = false)
    private String bcontent;

    private long bhit;

    private long bgroup;
    private long bstep;
    private long bindent;

    /**
     * insert 되기전 (persist 되기전) 실행된다.
     * */
/*    @PrePersist
    public void prePersist() {
        this.bhit = this.bgroup = this.bindent = this.bstep = 0L;
    }*/
}
