select * from roles

insert into roles (role_name) values ('Директор')
insert into roles (role_name) values ('Менеджер')
insert into roles (role_name) values ('Продавец')
insert into roles (role_name) values ('Кладовщик')

select * from users

insert into users (username, email, role_id) values ('Вячеслав Арнольдович', 'blueberry@mai.ru', 1)
insert into users (username, email, role_id) values ('Александр', 'redberry@mai.ru', 2)
insert into users (username, email, role_id) values ('Сережа', 'sergio@mai.ru', 3)
insert into users (username, email, role_id) values ('Санек', 'emperor_of_aches@mai.ru', 5)
insert into users (username, email, role_id) values ('Ванек', 'vano_01011999@mai.ru', 5)

select * from rules r 

insert into rules (name, description) values('Можно опаздывать на работу', 'Бла Бла Бла 1234561')
insert into rules (name, description) values('Можно носить оранжевые штаны', 'Бла Бла Бла 1234562')
insert into rules (name, description) values('Нужно носить кофе начальнику', 'Бла Бла Бла 1234563')
insert into rules (name, description) values('Выполнять свои обязанности', 'Бла Бла Бла 1234564')

select * from roles_rules

insert into roles_rules (role_id, rule_id) values (1, 1)
insert into roles_rules (role_id, rule_id) values (1, 2)
insert into roles_rules (role_id, rule_id) values (2, 1)
insert into roles_rules (role_id, rule_id) values (2, 2)
insert into roles_rules (role_id, rule_id) values (3, 2)
insert into roles_rules (role_id, rule_id) values (3, 3)
insert into roles_rules (role_id, rule_id) values (5, 4)

select * from categories 

insert into categories (name) values ('Товары для дома')
insert into categories (name) values ('Товары для детей')
insert into categories (name) values ('Товары для взрослых')

select * from states

insert into states (name) values ('Активен')
insert into states (name) values ('Снят с продажи')
insert into states (name) values ('Временно отсутствует')

select * from items

insert into items (name, user_id, category_id, state_id) values ('деревянная лопата', 3, 1, 1)
insert into items (name, user_id, category_id, state_id) values ('деревянная лошадка', 2, 2, 2)
insert into items (name, user_id, category_id, state_id) values ('деревянный предмет', 2, 3, 3)

select * from comments

insert into comments (content, item_id) values('Упаковано хорошо, пока не открывал', 1)
insert into comments (content, item_id) values('Есть пить не просит, все отлично', 1)
insert into comments (content, item_id) values('Упаковано хорошо, пока не открывал', 2)
insert into comments (content, item_id) values('Есть пить не просит, все отлично', 2)
insert into comments (content, item_id) values('Упаковано хорошо, пока не открывал', 3)
insert into comments (content, item_id) values('Огнище огненное, закываю уже в который раз', 3)

select * from attachs

insert into attachs (attach_url, item_id) values('url1', 1)
insert into attachs (attach_url, item_id) values('url2', 1)
insert into attachs (attach_url, item_id) values('url3', 2)
insert into attachs (attach_url, item_id) values('url4', 2)
insert into attachs (attach_url, item_id) values('url5', 3)
insert into attachs (attach_url, item_id) values('url6', 3)

