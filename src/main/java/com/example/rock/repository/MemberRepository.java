package com.example.rock.repository;

import com.example.rock.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Modifying
    @Query("UPDATE Member m SET m.points = m.points + :points, m.voteSum = m.voteSum + 1 WHERE m.id = :voterId")
    void doVote(long voterId, long points);

    @Modifying
    @Query("UPDATE Member m set m.votedSum = m.votedSum + 1 where m.id = :voterId")
    void plusVotedSum(long voterId);
}
