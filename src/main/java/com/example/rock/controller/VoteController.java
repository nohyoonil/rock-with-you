package com.example.rock.controller;

import com.example.rock.config.annotation.LoginId;
import com.example.rock.config.model.req.VoteReq;
import com.example.rock.config.model.res.VoteRes;
import com.example.rock.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vote")
public class VoteController {

    private final VoteService voteService;

    @GetMapping
    public ResponseEntity<List<VoteRes>> getMyVotes(@LoginId Long userId) {
        List<VoteRes> voteList = voteService.getVoteList(userId);
        return ResponseEntity.ok(voteList);
    }

    @PostMapping
    public ResponseEntity<VoteRes> createVote(@LoginId Long userId, @RequestBody VoteReq voteReq) {
        VoteRes voteInfo = voteService.doVote(userId, voteReq);
        return ResponseEntity.ok(voteInfo);
    }
}
