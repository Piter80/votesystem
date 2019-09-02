package ru.zimin.votesystem.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.zimin.votesystem.model.Vote;
import ru.zimin.votesystem.service.VoteService;
import ru.zimin.votesystem.to.VoteTo;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteService voteService;

    public static final String REST_URL = "/admin/votes";

    @GetMapping()
    public List<VoteTo> getAllForToday() {
        log.info("get all votes for today");
        return voteService.getAllForDate(null);
    }

    @GetMapping("/for")
    public List<VoteTo> getAllForDate(@RequestParam LocalDate day) {
        log.info("get all votes for date");
        return voteService.getAllForDate(day);
    }

    @GetMapping("/{id}")
    public Vote get(@PathVariable int id) {
        log.info("get vote with id={}", id);
        return voteService.get(id);
    }
}
