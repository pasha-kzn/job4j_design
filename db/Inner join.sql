create table roles(
id serial primary key,
role_name varchar(127)
);

create table users(
id serial primary key,
username varchar(63),
email varchar(63),
role_id int references roles(id)
);

create table items(
id serial primary key,
name varchar(63),
user_id int references users(id),
category_id int,
state_id int
);

insert into roles (role_name) values ('Директор');
insert into roles (role_name) values ('Менеджер');
insert into roles (role_name) values ('Продавец');
insert into roles (role_name) values ('Кладовщик');

insert into users (username, email, role_id) values ('Вячеслав Арнольдович', 'blueberry@mai.ru', 1);
insert into users (username, email, role_id) values ('Александр', 'redberry@mai.ru', 2);
insert into users (username, email, role_id) values ('Сережа', 'sergio@mai.ru', 3);
insert into users (username, email, role_id) values ('Санек', 'emperor_of_aches@mai.ru', 4);
insert into users (username, email, role_id) values ('Ванек', 'vano_01011999@mai.ru', 4);

insert into items (name, user_id, category_id, state_id) values ('деревянная лопата', 3, 1, 1);
insert into items (name, user_id, category_id, state_id) values ('деревянная лошадка', 2, 2, 2);
insert into items (name, user_id, category_id, state_id) values ('деревянный предмет', 2, 3, 3);

select * from users u
join roles r on u.role_id = r.id 
where r.id != 4

select * from items i
join users u on u.id = i.user_id 
where u.username = 'Александр'

select u.id, r.role_name, i."name" from users u
join roles r on u.role_id = r.id
join items i on u.id = i.user_id 


