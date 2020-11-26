package com.example.naflex.jpaTest.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="member")      // JPA가 관리하는 클래스
public class MemerberVO {
    @Id                     // @Id를 사용하여 PK 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동생성성
    private Long mbrNo;

   private String id;
    private String name;

    @Builder
    public MemerberVO(String id, String name){
        this.id = id;
        this.name = name;
    }
}
