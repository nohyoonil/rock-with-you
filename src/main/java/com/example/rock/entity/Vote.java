package com.example.rock.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vote {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "voter_id" , nullable = false)
    private Member voter; //투표자
    @ManyToOne
    @JoinColumn(name = "target_id", nullable = false)
    private Member target; //피투표자
    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;
    @Column(name = "opened")
    private boolean opened; //피투표자의 투표 정보 열람 여부
    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt; //투표 생성일자(투표일)
}
