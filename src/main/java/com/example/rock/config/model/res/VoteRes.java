package com.example.rock.config.model.res;

import com.example.rock.entity.Vote;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoteRes {

    private long id;
    private long voterId;
    private long targetId;
    private long questionId;
    private boolean opened;
    private LocalDateTime createdAt;

    public static VoteRes fromVote(Vote vote) {
        return VoteRes.builder()
                .id(vote.getId())
                .voterId(vote.getVoter().getId())
                .targetId(vote.getTarget().getId())
                .questionId(vote.getQuestion().getId())
                .opened(vote.isOpened()).build();

    }
}
