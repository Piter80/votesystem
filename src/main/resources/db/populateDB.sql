DELETE FROM user_roles;
DELETE FROM votes;
DELETE FROM menus;
DELETE FROM users;
DELETE FROM dishes;
DELETE FROM restaurants;
ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;

INSERT INTO users (id, name, email, password)
VALUES (10, 'admin1', 'admin1@gmail.com', '{noop}admPass'),
       (11, 'user1', 'user1@yandex.ru', '{noop}userPass'),
       (12, 'user2', 'user2@yandex.ru', '{noop}user2Pass'),
       (13, 'user3', 'user3@yandex.ru', '{noop}user3Pass');

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_ADMIN', 10),
       ('ROLE_USER', 10),
       ('ROLE_USER', 11),
       ('ROLE_USER', 12),
       ('ROLE_USER', 13);

INSERT INTO restaurants (id, name)
VALUES (101, 'Мак Кряк'),
       (102, 'бургер Квин'),
       (103, 'Eat and Die');


INSERT INTO dishes(id, name, restaurant_id)
VALUES (1001, 'Кряк в собственном соку', 101),
       (1002, 'СухоКряки', 101),
       (1003, 'Покрякушки', 101),
       (1004, 'Паж королевы в собственном соку', 102),
       (1005, 'Пики гвардейцев', 102),
       (1006, 'Эшафот прямо в рот', 102),
       (1007, 'Just eat', 103),
       (1008, 'Eat and maybe die', 103),
       (1009, 'Eat and die', 103);

INSERT INTO menus(id, menudate, restaurant_id, dish_id, price)
VALUES (10001, DATE '2019-7-1', 101, 1001, 101), -- 01.07.2019
       (10002, DATE '2019-7-1', 101, 1002, 211),
       (10003, DATE '2019-7-1', 101, 1003, 251),
       (10004, DATE '2019-7-1', 102, 1004, 321),
       (10005, DATE '2019-7-1', 103, 1007, 351),
       (10006, DATE '2019-7-1', 103, 1008, 321),

       (10007, DATE '2019-7-2', 101, 1001, 102), -- 02.07.2019
       (10008, DATE '2019-7-2', 101, 1002, 212),
       (10009, DATE '2019-7-2', 102, 1005, 232),
       (10010, DATE '2019-7-2', 102, 1004, 322),
       (10011, DATE '2019-7-2', 103, 1009, 302),
       (10012, DATE '2019-7-2', 103, 1008, 322);

INSERT INTO votes(id, vote_day, user_id, restaurant_id) VALUES
       (50000, DATE '2019-7-1', 11, 101),  -- 01.07.2019
       (50001, DATE '2019-7-1', 12, 101),
       (50002, DATE '2019-7-1', 13, 103),

       (50003, DATE '2019-7-2', 11, 103), -- 02.07.2019
       (50004, DATE '2019-7-2', 12, 102),
       (50005, DATE '2019-7-2', 13, 101);



