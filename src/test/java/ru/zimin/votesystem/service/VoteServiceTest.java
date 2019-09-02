package ru.zimin.votesystem.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.zimin.votesystem.model.Vote;
import ru.zimin.votesystem.to.VoteTo;
import ru.zimin.votesystem.util.exceptions.TooLateForVoteException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.zimin.votesystem.data.TestData.*;

public class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    VoteService voteService;

    @Test
    void getAllForDate() throws Exception {
        List<VoteTo> all = voteService.getAllForDate(LocalDate.of(2019, 7, 1));
        assertMatch(all, VOTE_TOS_FOR_20190701);
    }

    @Test
    void get() throws Exception {
        Vote vote = voteService.get(VOTE3_ID);
        assertMatch(vote, VOTE3);
    }

    @Test
    void voteFirstTimeBefore11() throws Exception {
        LocalDate date = LocalDate.parse("2019-07-01");
        LocalTime time = LocalTime.of(9, 0, 0);
        Vote vote = voteService.vote(date, ADMIN_ID, RES1_ID, time);
        assertEquals(vote, voteService.getByUserIdAndVotingDate(ADMIN_ID, date).orElse(null));
    }

    @Test
    void voteSecondTimeBefore11() throws Exception {
        LocalDate date = LocalDate.parse("2019-07-01");
        LocalTime time1 = LocalTime.of(9, 0, 0);;
        Vote vote1 = voteService.vote(date, ADMIN_ID, RES1_ID, time1);

        LocalTime time2 = LocalTime.of(10, 0, 0);
        Vote vote2 = voteService.vote(date, ADMIN_ID, RES2_ID, time2);

        Vote expected = voteService.getByUserIdAndVotingDate(ADMIN_ID, date).orElse(null);

        System.out.println(vote1);
        System.out.println(vote2);

        assertEquals(vote2, expected);
    }

    @Test
    void voteFirstTimeAfter11() throws Exception {
        LocalDate date = LocalDate.parse("2019-07-02");
        LocalTime time = LocalTime.of(11, 0, 5);
        Vote vote = voteService.vote(date, ADMIN_ID, RES3_ID, time);
        assertEquals(vote, voteService.getByUserIdAndVotingDate(ADMIN_ID, date).orElse(null));
    }

    @Test
    void voteSecondTimeAfter11() throws Exception {
        LocalDate date = LocalDate.parse("2019-07-02");
        LocalTime time1 = LocalTime.of(11, 0, 5);
        Vote expected = voteService.vote(date, ADMIN_ID, RES3_ID, time1);

        LocalTime time2 = LocalTime.of(12, 0, 0);
        assertThrows(TooLateForVoteException.class, () ->
                voteService.vote(date, ADMIN_ID, RES2_ID, time2));

        assertEquals(expected, voteService.getByUserIdAndVotingDate(ADMIN_ID, date).orElse(null));
    }

    @Test
    void getAllForDateForUser() throws Exception {
        List<VoteTo> all = voteService.getAllForDateForUser(LocalDate.of(2019, 7, 2), USER3_ID);
        assertMatch(all, VOTE_TOS_FOR_20190702_FOR_USER3);
    }
}
