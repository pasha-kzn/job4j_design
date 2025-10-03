create table roles(
id serial primary key,
role_name varchar(127)
)

create table users(
id serial primary key,
username varchar(63),
email varchar(63),
role_id int references roles(id)
)

create table rules(
id serial primary key,
name varchar(63),
description text
)

create table roles_rules(
id serial primary key,
role_id int references roles(id),
rule_id int references rules(id)
)

create table categories(
id serial primary key,
name varchar(127)
)

create table states(
id serial primary key,
name varchar(63)
)

create table items(
id serial primary key,
name varchar(63),
user_id int references users(id),
category_id int references categories(id),
state_id int references states(id)
)

create table comments(
id serial primary key,
content text,
item_id int references items(id)
)

create  table attachs(
id serial primary key,
attach_url varchar(127),
item_id int references items(id)
)