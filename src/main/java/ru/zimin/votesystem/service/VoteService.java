package ru.zimin.votesystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zimin.votesystem.model.Restaurant;
import ru.zimin.votesystem.model.User;
import ru.zimin.votesystem.model.Vote;
import ru.zimin.votesystem.repository.RestaurantRepository;
import ru.zimin.votesystem.repository.UserRepository;
import ru.zimin.votesystem.repository.VoteRepository;
import ru.zimin.votesystem.to.VoteTo;
import ru.zimin.votesystem.util.ToUtil;
import ru.zimin.votesystem.util.exceptions.TooLateForVoteException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class VoteService {
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private UserRepository userRepository;

    public List<VoteTo> getAllForDate(LocalDate date) {
        if (date == null) {
            date = LocalDate.now();
        }
        return ToUtil.votesAsToList(voteRepository.getAllForDate(date));
    }

    public Vote get(int id) {
        return voteRepository.get(id);
    }

    public Optional<Vote> getByUserIdAndVotingDate(int userId, LocalDate votingDay) {
        return voteRepository.findByUserIdAndVotingDate(userId, votingDay);
    }

    public Vote vote(LocalDate date, int userId, int restaurantId, LocalTime time) {
        if (time == null) time = LocalTime.now();
        if (date == null) date = LocalDate.now();

        Optional<Vote> optionalVote = voteRepository.findByUserIdAndVotingDate(userId, date);
        User user = userRepository.getOne(userId);
        Restaurant restaurant = restaurantRepository.getOne(restaurantId);

        if (optionalVote.isEmpty()) {
            return voteRepository.save(new Vote(null, date, user, restaurant));
        } else {
            Vote vote = null;
            if (time.isBefore(LocalTime.of(11, 0, 0))) {
                vote = voteRepository.save(new Vote(optionalVote.get().getId(), date, user, restaurant));
            } else {
                throw new TooLateForVoteException(String.format("User with userId = %d already has voted before 11.00 a.m.", userId));
            }
            return vote;
        }
    }

    public List<VoteTo> getAllForDateForUser(LocalDate date, int userId) {
        if (date == null) {
            date = LocalDate.now();
        }
        return ToUtil.votesAsToList(voteRepository.getAllForDateForUser(date, userId));
    }

    public List<VoteTo> getAllForDateForRestaurant(LocalDate date, int restaurantId) {
        if (date == null) {
            date = LocalDate.now();
        }
        return ToUtil.votesAsToList(voteRepository.getAllForDateForRestaurant(date, restaurantId));
    }
}
