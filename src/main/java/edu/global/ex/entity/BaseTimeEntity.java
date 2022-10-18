package edu.global.ex.entity;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@EntityListeners(value = AuditingEntityListener.class)
@MappedSuperclass
@Getter
@ToString
public abstract class BaseTimeEntity {

    @CreatedDate
    @Column(name="regdate",updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "moddate")
    private LocalDateTime modDate;
}

