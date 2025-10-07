CREATE TABLE devices
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(255),
    price FLOAT
);

CREATE TABLE people
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE devices_people
(
    id        SERIAL PRIMARY KEY,
    device_id INT REFERENCES devices (id),
    people_id INT REFERENCES people (id)
);

insert into devices(name, price) values('Часы Montana', 100.00), ('Тамагочи', 300.00), ('Ну погоди', 700.00), ('Iphone4', 2000), ('Redme10C', 5001.00), ('Планшет', 24990.00), ('Iphone17', 100500.00);
insert into people(name) values ('Вася'), ('Антон'), ('Серёжа');
insert into devices_people(device_id, people_id) values (1, 1), (2, 1), (3, 1), (4, 1), (5, 1), (3, 2), (4, 2), (5, 2), (6, 2), (1, 3), (3, 3), (5, 3); 



select avg(price) from devices d;

select p."name", avg(d.price) from people p 
join devices_people dp on p.id = dp.people_id 
join devices d on d.id = dp.device_id 
group by p.id;

select p."name", avg(d.price) from people p 
join devices_people dp on p.id = dp.people_id 
join devices d on d.id = dp.device_id 
group by p.id 
having avg(d.price) > 5000;