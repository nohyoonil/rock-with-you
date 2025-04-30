package com.example.rock.repository;

import com.example.rock.entity.Member;
import com.example.rock.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    List<Vote> getVotesByVoter(Member voter);
}
