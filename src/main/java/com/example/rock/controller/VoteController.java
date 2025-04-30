package com.example.rock.controller;

import com.example.rock.config.annotation.LoginId;
import com.example.rock.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vote")
public class VoteController {

    private final VoteService voteService;

    @GetMapping
    public ResponseEntity<?> getMyVotes(@LoginId Long userId) {
        if (userId == null) {
            return ResponseEntity.badRequest().body("UserId is null");
        }
        return ResponseEntity.ok(userId);
    }
}
