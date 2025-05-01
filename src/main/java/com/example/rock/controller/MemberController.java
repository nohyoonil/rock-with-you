package com.example.rock.controller;

import com.example.rock.config.model.res.MemberInfo;
import com.example.rock.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberInfo> getMemberInfo(@PathVariable long memberId) {
        return ResponseEntity.ok(memberService.getMemberInfo(memberId));
    }
}
