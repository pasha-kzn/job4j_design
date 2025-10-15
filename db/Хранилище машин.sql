CREATE TABLE car_bodies 
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(255)
);

CREATE TABLE car_engines 
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(255)
);

CREATE TABLE car_transmissions
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(255)
);

CREATE TABLE cars 
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(255),
    body_id int references car_bodies(id),
    engine_id  int references car_engines(id),
    transmission_id int references car_transmissions(id)    
);

insert into car_bodies (name) values ('Седан'), ('Хэтчбэк'), ('Пикап'), ('Купе'), ('Кроссовер'), ('Кабриолет');
insert into car_engines (name) values ('Двигатель 50 лс'), ('Бензин 150 лс'), ('Бензин 250 лс'), ('Дизель 250 лс'), ('Электро 150 лс'), ('Электро 250 лс');
insert into car_transmissions (name) values('Автомат'), ('Механика'), ('Робот'), ('Вариатор');
insert into cars (name, body_id, engine_id, transmission_id) values
	('Ваз-2101', 1, 1, 2),
	('Honda Civic', 1, 1, 1),
	('Toyota Raf4', 5, 3, 4),
	('Tesla', 1, 6, null);

--Вывести список всех машин и все привязанные к ним детали.
select c.id, c."name" as car_name, b."name" as body_name, e."name" as engine_name, t."name" as transmission_name from cars c
left join car_bodies b on c.body_id = b.id 
left join car_engines e on c.engine_id  = e.id 
left join car_transmissions t on c.transmission_id = t.id;

--Вывести кузова, которые не используются НИ в одной машине
select cb."name" from cars c 
right join car_bodies cb on c.body_id = cb.id 
where c.id is null;

--Вывести двигатели, которые не используются НИ в одной машине
select ce."name" from cars c 
right join car_engines ce on c.engine_id = ce.id 
where c.id is null;

--Вывести коробки передач, которые не используются НИ в одной машине
select ct."name" from cars c 
right join car_transmissions ct on c.transmission_id = ct.id 
where c.id is null;