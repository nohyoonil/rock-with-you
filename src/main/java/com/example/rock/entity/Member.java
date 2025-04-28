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
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "name")
    private String name;
    @Column(nullable = true, name = "pfp")
    private String pfp; //프로필 사진
    @Column(nullable = false, name = "points")
    private int points; //보유 포인트
    @Column(nullable = false, name = "vote_sum")
    private int voteSum; //해당 유저의 총 투표수
    @Column(nullable = false, name = "voted_sum")
    private int votedSum; //해당 유저가 받은 총 투표수
    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;
}
