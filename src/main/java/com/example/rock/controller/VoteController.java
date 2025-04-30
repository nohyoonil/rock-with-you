package com.example.rock.controller;

import com.example.rock.config.annotation.LoginId;
import com.example.rock.config.model.res.VoteRes;
import com.example.rock.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vote")
public class VoteController {

    private final VoteService voteService;

    @GetMapping
    public ResponseEntity<List<VoteRes>> getMyVotes(@LoginId Long userId) {
        List<VoteRes> voteList = voteService.getVoteList(userId);
        return ResponseEntity.ok(voteList);
    }
}
