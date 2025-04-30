package com.example.rock.service;

import com.example.rock.config.model.req.VoteReq;
import com.example.rock.config.model.res.VoteRes;
import com.example.rock.entity.Member;
import com.example.rock.entity.Question;
import com.example.rock.entity.Vote;
import com.example.rock.repository.MemberRepository;
import com.example.rock.repository.QuestionRepository;
import com.example.rock.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final MemberRepository memberRepository;
    private final VoteRepository voteRepository;
    private final QuestionRepository questionRepository;

    public List<VoteRes> getVoteList(Long userId) {
        Member member = memberRepository.getReferenceById(userId);
        List<Vote> voteList = voteRepository.getVotesByVoter(member);

        return voteList.stream().map(VoteRes::fromVote).toList();
    }

    @Transactional
    public VoteRes doVote(long voterId, VoteReq voteReq) {
        Optional<Question> optionalQuestion = questionRepository.findById(voteReq.getQuestionId());
        long targetId = voteReq.getTargetId();

        if (!memberRepository.existsById(targetId) || optionalQuestion.isEmpty())
            throw new RuntimeException("Invalid vote request");

        Member voter = memberRepository.getReferenceById(voterId);
        Member target = memberRepository.getReferenceById(targetId);
        Question question = optionalQuestion.get();

        Vote vote = voteRepository.save(Vote.builder()
                .voter(voter)
                .target(target)
                .question(question)
                .createdAt(LocalDateTime.now())
                .build());

        memberRepository.doVote(voterId, question.getPoints());
        memberRepository.plusVotedSum(targetId);

        return VoteRes.fromVote(vote);
    }

}
