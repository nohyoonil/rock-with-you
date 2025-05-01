package com.example.rock.config.model.res;

import com.example.rock.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberInfo {

    private Long id;
    private String name;
    private String pfp; //프로필 사진
    private int points; //보유 포인트
    private int voteSum; //해당 유저의 총 투표수
    private int votedSum; //해당 유저가 받은 총 투표수
    private LocalDateTime createdAt;

    public static MemberInfo fromMember(Member member) {
        return MemberInfo.builder()
                .id(member.getId())
                .name(member.getName())
                .pfp(member.getPfp())
                .points(member.getPoints())
                .voteSum(member.getVoteSum())
                .votedSum(member.getVotedSum())
                .createdAt(member.getCreatedAt())
                .build();
    }
}
