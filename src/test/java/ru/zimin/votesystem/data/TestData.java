package ru.zimin.votesystem.data;

import org.springframework.test.web.servlet.ResultMatcher;
import ru.zimin.votesystem.model.*;
import ru.zimin.votesystem.to.VoteTo;
import ru.zimin.votesystem.util.ToUtil;
import ru.zimin.votesystem.web.json.JsonUtil;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.zimin.votesystem.data.TestUtil.readListFromJsonMvcResult;


public class TestData {
    public static final int START_USERS_SEQ = 10;
    public static final int ADMIN_ID = START_USERS_SEQ;
    public static final int USER1_ID = START_USERS_SEQ + 1;
    public static final int USER2_ID = START_USERS_SEQ + 2;
    public static final int USER3_ID = START_USERS_SEQ + 3;


    public static final User ADMIN = new User(ADMIN_ID, "admin1", "admin1@gmail.com", "admPass", Role.ROLE_ADMIN, Role.ROLE_USER);
    public static final User USER1 = new User(USER1_ID, "user1", "user1@yandex.ru", "userPass", Role.ROLE_USER);
    public static final User USER2 = new User(USER2_ID, "user2", "user2@yandex.ru", "user2Pass", Role.ROLE_USER);
    public static final User USER3 = new User(USER3_ID, "user3", "user3@yandex.ru", "user3Pass", Role.ROLE_USER);


    public static final int START_RESTAURANTS_SEQ = 100;
    public static final int RES1_ID = START_RESTAURANTS_SEQ + 1;
    public static final int RES2_ID = START_RESTAURANTS_SEQ + 2;
    public static final int RES3_ID = START_RESTAURANTS_SEQ + 3;


    public static final Restaurant RES1 = new Restaurant(RES1_ID, "Мак Кряк");
    public static final Restaurant RES2 = new Restaurant(RES2_ID, "бургер Квин");
    public static final Restaurant RES3 = new Restaurant(RES3_ID, "Eat and Die");


    public static final List<Restaurant> RESTAURANTS = List.of(RES1, RES2, RES3);
    public static final List<Restaurant> RESTAURANTS_FOR_DAY_20190701 = List.of(RES1, RES2, RES3);
    public static final List<Restaurant> RESTAURANTS_FOR_DAY_20190701_WITHOUT_RES1 = List.of(RES2, RES3);

    public static final int START_DISHES_SEQ = 1000;
    public static final int DISH1_ID = START_DISHES_SEQ + 1;
    public static final int DISH2_ID = START_DISHES_SEQ + 2;
    public static final int DISH3_ID = START_DISHES_SEQ + 3;
    public static final int DISH4_ID = START_DISHES_SEQ + 4;
    public static final int DISH5_ID = START_DISHES_SEQ + 5;
    public static final int DISH6_ID = START_DISHES_SEQ + 6;
    public static final int DISH7_ID = START_DISHES_SEQ + 7;
    public static final int DISH8_ID = START_DISHES_SEQ + 8;
    public static final int DISH9_ID = START_DISHES_SEQ + 9;


    public static final Dish DISH1 = new Dish(DISH1_ID, "Кряк в собственном соку", RES1);
    public static final Dish DISH2 = new Dish(DISH2_ID, "СухоКряки", RES1);
    public static final Dish DISH3 = new Dish(DISH3_ID, "Покрякушки", RES1);
    public static final Dish DISH4 = new Dish(DISH4_ID, "Паж королевы в собственном соку", RES2);
    public static final Dish DISH5 = new Dish(DISH5_ID, "Пики гвардейцев", RES2);
    public static final Dish DISH6 = new Dish(DISH6_ID, "Эшафот прямо в рот", RES2);
    public static final Dish DISH7 = new Dish(DISH7_ID, "Just eat", RES3);
    public static final Dish DISH8 = new Dish(DISH8_ID, "Eat and maybe die", RES3);
    public static final Dish DISH9 = new Dish(DISH9_ID, "Eat and die", RES3);


    public static final List<Dish> RES3_DISHES = List.of(DISH7, DISH8, DISH9);

    public static final List<Dish> DISHES = List.of(DISH1, DISH2, DISH3, DISH4, DISH5, DISH6, DISH7,
            DISH8, DISH9);

    public static final int START_MENU_SEQ = 10000;
    public static final int MENU1_ID = START_MENU_SEQ + 1;
    public static final int MENU2_ID = START_MENU_SEQ + 2;
    public static final int MENU3_ID = START_MENU_SEQ + 3;
    public static final int MENU4_ID = START_MENU_SEQ + 4;
    public static final int MENU5_ID = START_MENU_SEQ + 5;
    public static final int MENU6_ID = START_MENU_SEQ + 6;
    public static final int MENU7_ID = START_MENU_SEQ + 7;
    public static final int MENU8_ID = START_MENU_SEQ + 8;
    public static final int MENU9_ID = START_MENU_SEQ + 9;
    public static final int MENU10_ID = START_MENU_SEQ + 10;
    public static final int MENU11_ID = START_MENU_SEQ + 11;
    public static final int MENU12_ID = START_MENU_SEQ + 12;


    public static final Menu MENU1 = new Menu(MENU1_ID, LocalDate.of(2019, 7, 1), RES1, DISH1, 101);
    public static final Menu MENU2 = new Menu(MENU2_ID, LocalDate.of(2019, 7, 1), RES1, DISH2, 211);
    public static final Menu MENU3 = new Menu(MENU3_ID, LocalDate.of(2019, 7, 1), RES1, DISH3, 251);
    public static final Menu MENU4 = new Menu(MENU4_ID, LocalDate.of(2019, 7, 1), RES2, DISH4, 321);
    public static final Menu MENU5 = new Menu(MENU5_ID, LocalDate.of(2019, 7, 1), RES3, DISH7, 351);
    public static final Menu MENU6 = new Menu(MENU6_ID, LocalDate.of(2019, 7, 1), RES3, DISH8, 321);

    public static final Menu MENU7 = new Menu(MENU7_ID, LocalDate.of(2019, 7, 2), RES1, DISH1, 102);
    public static final Menu MENU8 = new Menu(MENU8_ID, LocalDate.of(2019, 7, 2), RES1, DISH2, 212);
    public static final Menu MENU9 = new Menu(MENU9_ID, LocalDate.of(2019, 7, 2), RES2, DISH5, 232);
    public static final Menu MENU10 = new Menu(MENU10_ID, LocalDate.of(2019, 7, 2), RES2, DISH4, 322);
    public static final Menu MENU11 = new Menu(MENU11_ID, LocalDate.of(2019, 7, 2), RES3, DISH9, 302);
    public static final Menu MENU12 = new Menu(MENU12_ID, LocalDate.of(2019, 7, 2), RES3, DISH8, 322);

    public static final List<Menu> RES1_MENUS = List.of(MENU1, MENU2, MENU3);

    public static final List<Menu> MENUS_FOR_20190701 = List.of(MENU1, MENU2, MENU3, MENU4,
            MENU5, MENU6);

    public static final List<Menu> MENUS_FOR_20190702 = List.of(MENU7, MENU8, MENU9, MENU10,
            MENU11, MENU12);

    public static final List<Menu> MENUS = List.of(MENU1, MENU2, MENU3, MENU4, MENU5, MENU6,
            MENU7, MENU8, MENU9, MENU10, MENU11, MENU12);

    public static final int START_VOTE_SEQ = 50000;
    public static final int VOTE1_ID = START_VOTE_SEQ;
    public static final int VOTE2_ID = START_VOTE_SEQ + 1;
    public static final int VOTE3_ID = START_VOTE_SEQ + 2;
    public static final int VOTE4_ID = START_VOTE_SEQ + 3;
    public static final int VOTE5_ID = START_VOTE_SEQ + 4;
    public static final int VOTE6_ID = START_VOTE_SEQ + 5;


    public static final Vote VOTE1 = new Vote(VOTE1_ID, LocalDate.of(2019, 7, 1), USER1, RES1);
    public static final Vote VOTE2 = new Vote(VOTE2_ID, LocalDate.of(2019, 7, 1), USER2, RES1);
    public static final Vote VOTE3 = new Vote(VOTE3_ID, LocalDate.of(2019, 7, 1), USER3, RES3);

    public static final Vote VOTE4 = new Vote(VOTE4_ID, LocalDate.of(2019, 7, 2), USER1, RES3);
    public static final Vote VOTE5 = new Vote(VOTE5_ID, LocalDate.of(2019, 7, 2), USER2, RES2);
    public static final Vote VOTE6 = new Vote(VOTE6_ID, LocalDate.of(2019, 7, 2), USER3, RES1);


    public static final List<VoteTo> VOTE_TOS_FOR_20190701 = ToUtil.votesAsToList(List.of(VOTE1, VOTE2, VOTE3));
    public static final List<VoteTo> VOTE_TOS_FOR_20190702_FOR_USER3 = ToUtil.votesAsToList(List.of(VOTE6));
    public static final List<VoteTo> VOTE_TOS_FOR_20190701_FOR_RES1 = ToUtil.votesAsToList(List.of(VOTE1, VOTE2));

    public static <T> void assertMatch(T actual, T expected) {
        assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
    }

    public static <T> void assertMatch(Iterable<T> actual, T... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static <T> void assertMatch(T actual, T expected, String... ignore) {
        assertThat(actual).usingComparatorForFields((x, y) -> 0, ignore).isEqualToComparingFieldByFieldRecursively(expected);
    }

    public static <T> ResultMatcher contentJson(Class clazz, T... expected) {
        return contentJson(clazz, List.of(expected));
    }

    public static <T> ResultMatcher contentJson(Class clazz, Iterable<T> expected) {
        return result -> assertMatch(readListFromJsonMvcResult(result, clazz), expected);
    }

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }
}
