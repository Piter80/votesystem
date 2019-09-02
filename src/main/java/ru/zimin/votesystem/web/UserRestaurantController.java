package ru.zimin.votesystem.web;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.zimin.votesystem.AuthorizedUser;
import ru.zimin.votesystem.model.Menu;
import ru.zimin.votesystem.model.Restaurant;
import ru.zimin.votesystem.model.Vote;
import ru.zimin.votesystem.to.VoteTo;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = UserRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestaurantController extends AbstractRestaurantController {
    public static final String REST_URL = "/restaurants";

    @GetMapping("/{id}")
    public Restaurant getForDay(@PathVariable int id) {
        return super.getForDay(id, null);
    }

    @Override
    @GetMapping
    public List<Restaurant> getAllForToday() {
        return super.getAllForToday();
    }

    @PostMapping("/{id}/vote")
    public ResponseEntity<Vote> vote(@PathVariable int id, @AuthenticationPrincipal AuthorizedUser user) {
        Vote vote = super.vote(null, user.getId(), id, null);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}/vote")
                .buildAndExpand(vote.getRestaurant().getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(vote);
    }

    @GetMapping("/{id}/menus")
    public List<Menu> getAllMenusForDay(@PathVariable int id) {
        return super.getAllMenusForDayByRestaurantId(id, null);
    }

    @GetMapping("/menus")
    public List<Menu> getAllMenusForDay() {
        return super.getAllMenusForDay(null);
    }

    @GetMapping("/votes")
    public List<VoteTo> getAllVotesForDay(@AuthenticationPrincipal AuthorizedUser user) {
        return super.getAllForDateForUser(null, user.getId());
    }
}
