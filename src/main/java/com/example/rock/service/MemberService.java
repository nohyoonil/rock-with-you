package com.example.rock.service;

import com.example.rock.config.model.res.MemberInfo;
import com.example.rock.entity.Member;
import com.example.rock.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberInfo getMemberInfo(Long userId) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return MemberInfo.fromMember(member);
    }
}
