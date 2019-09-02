# Restaurant Voting REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) without frontend.
  This votesystem system is designed to decide where to have lunch.
  > 2 types of users: admin and regular users <br>
  > Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price) <br>
  > Menu changes each day (admins do the updates) <br>
  > Users can vote on which restaurant they want to have lunch at <br>
  > Only one vote counted per user <br>
  > Each restaurant provides new menu each day <br>
  > If user votes again the same day:
  > - If it is before 11:00 we asume that he changed his mind <br>
  > - If it is after 11:00 then it is too late, vote can't be changed <br>

##Admin controller, manage users: /admin/users
###### get all: 
curl -s http://localhost:8080/votesystem/admin/users --user admin1@gmail.com:admPass
###### get by id: 
curl -s http://localhost:8080/votesystem/admin/users/16 --user admin1@gmail.com:admPass
###### delete by id: 
curl -s -X DELETE http://localhost:8080/votesystem/admin/users/13 --user admin1@gmail.com:admPass
###### create new: 
curl -s -X POST -d '{"name":"addedUser", "email":"add@ya.ru", "password":"newPass"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votesystem/admin/users --user admin1@gmail.com:admPass
###### update: 
curl -s -X PUT -d '{"name":"UpdatedName", "email":"user1@yandex.ru", "password":"updatedPass"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votesystem/admin/users/100005 --user admin1@gmail.com:admPass
###### get by email: 
curl -s http://localhost:8080/votesystem/admin/users/by?email=user1@yandex.ru --user admin1@gmail.com:admPass

### error examples
###### not found: 
curl -s http://localhost:8080/votesystem/admin/users/1 --user admin1@gmail.com:admPass
###### create with duplicate email: 
curl -s -X POST -d '{"name":"New", "email":"simple@mail.ru", "password":"newPass"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votesystem/admin/users --user admin1@gmail.com:admPass
###### update with validation error: 
curl -s -X PUT -d '{"name":"UpdatedName", "password":"updatedPass"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votesystem/admin/users/100005 --user admin1@gmail.com:admPass
###### get all with authentication error: 
curl -s http://localhost:8080/votesystem/admin/users/
###### get all with forbidden error: 
curl -s http://localhost:8080/votesystem/admin/users/ --user user1@yandex.ru:userPass
###### update with unsafe http error: 
curl -s -X PUT -d '{"name":"<script>alert(123)</script>", "email":"new@gmail.com", "password":"updatedPass"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votesystem/admin/users/100005 --user admin1@gmail.com:admPass

## User controller /profile
###### get AuthorizedUser: 
curl -s http://localhost:8080/votesystem/profile --user user1@yandex.ru:userPass
###### register: 
curl -s -X POST -d '{"name":"New", "email":"new@gmail.com", "password":"newPass"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votesystem/profile/register
###### update AuthorizedUser: 
curl -s -X PUT -d '{"name":"UpdatedName", "email":"user2@yandex.ru", "password":"updatedPass"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votesystem/profile --user user1@yandex.ru:userPass
###### delete AuthorizedUser: 
curl -s -X DELETE http://localhost:8080/votesystem/profile --user user1@yandex.ru:userPass


## Admin Restaurant Controller: /admin/restaurants
###### get all (without links): 
curl -s http://localhost:8080/votesystem/admin/restaurants --user admin1@gmail.com:admPass
###### get by id with all Menus: 
curl -s http://localhost:8080/votesystem/admin/restaurants/103 --user admin1@gmail.com:admPass
###### delete by id: 
curl -s -X DELETE http://localhost:8080/votesystem/admin/restaurants/102 --user admin1@gmail.com:admPass
###### create: 
curl -s -X POST -d '{"name": "Pupkinists"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votesystem/admin/restaurants --user admin1@gmail.com:admPass
###### update: 
curl -s -X PUT -d '{"id": "102", "name": "KoKoS"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votesystem/admin/restaurants --user admin1@gmail.com:admPass
###### get all by day with Menus: 
curl -s http://localhost:8080/votesystem/admin/restaurants/for?day=2019-07-01 --user admin1@gmail.com:admPass
###### get Restaurant by id and day with Menu: 
curl -s http://localhost:8080/votesystem/admin/restaurants/102/for?day=2019-07-01 --user admin1@gmail.com:admPass

###Dishes
###### get all (without links): 
curl -s http://localhost:8080/votesystem/admin/restaurants/dishes --user admin1@gmail.com:admPass
###### get all for Restaurant: 
curl -s http://localhost:8080/votesystem/admin/restaurants/102/dishes --user admin1@gmail.com:admPass
###### get for Restaurant by id: 
curl -s http://localhost:8080/votesystem/admin/restaurants/102/dishes/1004 --user admin1@gmail.com:admPass
###### delete for Restaurant by id: 
curl -s -X DELETE http://localhost:8080/votesystem/admin/restaurants/102/dishes/1004 --user admin1@gmail.com:admPass
###### create for Restaurant: 
curl -s -X POST -d '{"name": "Skushi"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votesystem/admin/restaurants/103/dishes --user admin1@gmail.com:admPass
###### update for Restaurant: 
curl -s -X PUT -d '{"id": "1007", "name": "Hrum"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votesystem/admin/restaurants/103/dishes --user admin1@gmail.com:admPass

### Menus
###### get all (without links): 
curl -s http://localhost:8080/votesystem/admin/restaurants/menus --user admin1@gmail.com:admPass
###### get all for Restaurant: 
curl -s http://localhost:8080/votesystem/admin/restaurants/102/menus --user admin1@gmail.com:admPass
###### get all for Restaurant for day: 
curl -s http://localhost:8080/votesystem/admin/restaurants/103/menus/for?day=2019-07-02 --user admin1@gmail.com:admPass
###### get all for day: 
curl -s http://localhost:8080/votesystem/admin/restaurants/menus/for?day=2019-07-02 --user admin1@gmail.com:admPass
###### get for Restaurant by id: 
curl -s http://localhost:8080/votesystem/admin/restaurants/102/menus/10004 --user admin1@gmail.com:admPass
###### delete for Restaurant by id: 
curl -s -X DELETE http://localhost:8080/votesystem/admin/restaurants/102/menus/10004 --user admin1@gmail.com:admPass
###### delete all for Restaurant for day: 
curl -s -X DELETE http://localhost:8080/votesystem/admin/restaurants/101/menus/for?day=2019-07-01 --user admin1@gmail.com:admPass
###### create for Restaurant: 
curl -s -X POST -d '{"price": "555"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votesystem/admin/restaurants/101/menus/1001 --user admin1@gmail.com:admPass
###### update for Restaurant: 
curl -s -X PUT -d '{"id": "10001", "menuDate": "2019-08-10", "price": "333"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votesystem/admin/restaurants/101/menus/1001 --user admin1@gmail.com:admPass

### votes
###### get all Votes for current day for Restaurant (creating DayMenu for today and votesystem before seeing votes) 
curl -s -X POST -d '{"price": "500"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votesystem/admin/restaurants/101/menus/1001 --user admin1@gmail.com:admPass 
curl -s -X POST http://localhost:8080/votesystem/restaurants/101/vote --user user1@yandex.ru:userPass 
curl -s http://localhost:8080/votesystem/admin/restaurants/101/votes --user admin1@gmail.com:admPass

###### get all Votes for day for Restaurant
curl -s http://localhost:8080/votesystem/admin/restaurants/101/votes/for?day=2019-07-01 --user admin1@gmail.com:admPass

### error examples
###### not found Restaurant error
curl -s http://localhost:8080/votesystem/admin/restaurants/50 --user admin1@gmail.com:admPass

###### create Restaurant with duplicate name error
curl -s -X POST -d '{"name": "Eat and Die"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votesystem/admin/restaurants --user admin1@gmail.com:admPass

###### update Restaurant with duplicate name error
curl -s -X PUT -d '{"id": "103", "name": "Eat and Die"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votesystem/admin/restaurants --user admin1@gmail.com:admPass

###### create Restaurant with validation error
curl -s -X POST -d '{"name": "0"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votesystem/admin/restaurants --user admin1@gmail.com:admPass

###### get all Restaurants with authentication error
curl -s http://localhost:8080/votesystem/admin/restaurants

###### get all Restaurant with forbidden error
curl -s http://localhost:8080/votesystem/admin/restaurants --user user1@yandex.ru:userPass

###### create Restaurant with unsafe http error
curl -s -X POST -d '{"name":"<script>alert(123)</script>"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votesystem/admin/restaurants --user admin1@gmail.com:admPass

###### get Dish for not this Restaurant by id
curl -s http://localhost:8080/votesystem/admin/restaurants/101/dishes/1006 --user admin1@gmail.com:admPass

###### create not new Dish
curl -s -X POST -d '{"id": "1000091", "name": "Just eat"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votesystem/admin/restaurants/103/dishes --user admin1@gmail.com:admPass

###### delete not found Menu for Restaurant by id
curl -s -X DELETE http://localhost:8080/votesystem/admin/restaurants/103/menus/10001 --user admin1@gmail.com:admPass

<hr>

##Votes: /admin/votes
###### get all for current day
curl -s http://localhost:8080/votesystem/admin/votes --user admin1@gmail.com:admPass
###### get all for day
curl -s http://localhost:8080/votesystem/admin/votes/for?day=2019-07-02 --user admin1@gmail.com:admPass
###### get Vote by id
curl -s http://localhost:8080/votesystem/admin/votes/50005 --user admin1@gmail.com:admPass

######for Restaurant
curl -s -X POST http://localhost:8080/votesystem/restaurants/103/vote --user user1@yandex.ru:userPass

###### get all Restaurants for current day with Menus
curl -s http://localhost:8080/votesystem/restaurants/ --user user1@yandex.ru:userPass

###### get Restaurant by id for current day with Menu
curl -s http://localhost:8080/votesystem/restaurants/102 --user user1@yandex.ru:userPass

###### get all Menus for Restaurant for current day
curl -s http://localhost:8080/votesystem/restaurants/103/menus --user user1@yandex.ru:userPass

###### get all Menus for current day
curl -s http://localhost:8080/votesystem/restaurants/menus --user user1@yandex.ru:userPass

###### get all Votes for current day for current User
curl -s http://localhost:8080/votesystem/restaurants/votes --user admin1@gmail.com:admPass