package com.example.rock.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "content")
    private String content;
    @Column(nullable = false, name = "points")
    private int points; //해당 질문에 답변 후 받을 포인트
    @Column(nullable = false, name = "asked_sum")
    private int askedSum; //해당 질문에 대한 총 답변 수
}
