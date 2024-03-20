package kr.co.sboard.entity;

import groovy.beans.Bindable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
@Bindable
@Entity
@Table(name = "config")
@NoArgsConstructor
public class Config {

    @Id
    private  String cate;
    private  String boardName;
    private  String admin;

    @ColumnDefault("0")
    private  int total;

    @CreationTimestamp
    private LocalDateTime createDate;

}
