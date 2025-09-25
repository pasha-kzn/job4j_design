/*CREATE TABLE position(
    id serial primary key,
    name varchar(255)
);

create table access_cards(
id serial primary key,
card_number int
)

create table users(
id serial primary key,
namr varchar(255),
position_id int references position(id),
access_card_id int references access_cards(id) unique
)

create table roles(
id serial primary key,
name varchar(255)
)

create table users_role(
id serial primary key,
user_id int references users(id),
role_id int references roles(id)
)

insert into roles (name) values ('Продавец')

insert into roles (name) values ('Кассир')

insert into roles (name) values ('Директор')

select * from roles r */

create table profiles(
id serial primary key,
full_name varchar(127),
birthdate date,
phone_number varchar(15)
)

create table users(
id serial primary key,
username varchar(63),
email varchar(63),
profile_id int references profiles(id) unique
)

create table posts(
id serial primary key,
user_id int references users(id),
title varchar(63),
content text
)

create table tags(
id serial primary key,
name varchar(63)
)


create table post_tags(
post_id int references posts(id),
tag_id int references tags(id)
)

select * from profiles

insert into profiles(full_name, birthdate, phone_number) values('Иванов Иван', '01.04.1980', '+71234567890')
insert into profiles(full_name, birthdate, phone_number) values('Димон Медведев', '01.04.1970', '+71234567891')

select * from users

insert into users(username, email, profile_id) values ('Ванек1980', 'iivan100500@yandex.ru', 1)
insert into users(username, email, profile_id) values ('@krasau4eg', 'toli4@yandex.ru', 2)

select * from posts

insert into posts(user_id, title, content) values (1, 'Как вырастить большой урожай репы', 'Бла Бла Бла 100500')
insert into posts(user_id, title, content) values (1, 'Как нагнать из репы самогон', 'Бла Бла Бла 100501')
insert into posts(user_id, title, content) values (1, 'Как найти время на курс j4j, если его нет', 'Бла Бла Бла 100502')
insert into posts(user_id, title, content) values (3, 'Как вырастить большой урожай винограда', 'Бла Бла Бла 100503')
insert into posts(user_id, title, content) values (3, 'Как получить настоящее вино', 'Бла Бла Бла 100504')
insert into posts(user_id, title, content) values (3, 'Как догнать и перегнать Америку', 'Бла Бла Бла 100505')

select * from tags

insert into tags(name) values ('#садоводство')
insert into tags(name) values ('#томные вечера')
insert into tags(name) values ('#моя боль')
insert into tags(name) values ('#суки страшные')

select * from post_tags

insert into post_tags(post_id, tag_id) values (1, 1)
insert into post_tags(post_id, tag_id) values (2, 1)
insert into post_tags(post_id, tag_id) values (1, 3)
insert into post_tags(post_id, tag_id) values (3, 3)
insert into post_tags(post_id, tag_id) values (3, 1)
insert into post_tags(post_id, tag_id) values (5, 1)
insert into post_tags(post_id, tag_id) values (6, 2)
insert into post_tags(post_id, tag_id) values (7, 3)
insert into post_tags(post_id, tag_id) values (7, 4)
