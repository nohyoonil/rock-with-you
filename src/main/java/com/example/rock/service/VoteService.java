package com.example.rock.service;

import com.example.rock.config.model.res.VoteRes;
import com.example.rock.entity.Member;
import com.example.rock.entity.Vote;
import com.example.rock.repository.MemberRepository;
import com.example.rock.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final MemberRepository memberRepository;
    private final VoteRepository voteRepository;

    public List<VoteRes> getVoteList(Long userId) {
        Member member = memberRepository.getReferenceById(userId);
        List<Vote> voteList = voteRepository.getVotesByVoter(member);

        return voteList.stream().map(VoteRes::fromVote).toList();
    }
}
