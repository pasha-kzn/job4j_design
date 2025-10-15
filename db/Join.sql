create TABLE departments
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(255)
);    

create TABLE employees
(
    id    SERIAL PRIMARY KEY,
    fio  VARCHAR(255),
    department_id int references departments(id)
);

create TABLE teens
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(255),
    gender VARCHAR(7)
);

insert into departments(name) values('Казань'), ('Самара'), ('Екатеринбург');
insert into employees(fio, department_id) values('Иванов Иван Иваныч', 1),
	('Сергей Петрович', 1),
	('Афансий Никитич', 1),
	('Зульфия Петровна', 1),
	('Марина Сергеевна', 1),
	('Альфия Абдрахманована', 1),
	(null, 1),
	(null, 2),
	('Ольга Александровна', 2),
	('Наталья Петровна', 2),
	('Филип Бедросович', 2),
	('Владим Вадимыч', 2);
insert into teens(name, gender) values('Ваня', 'male'),
	('Сережа', 'male'),
	('Афансий', 'male'),
	('Филип', 'male'),
	('Владим', 'male'),
	('Альфия', 'female'),
	('Ольга', 'female'),
	('Наталья', 'female'),
	('Вера', 'female'),
	('Полина', 'female');

-- Выполнить запросы с left, right, full, cross соединениями
select * from departments d
join employees e on d.id = e.department_id;

select * from departments d
left join employees e on d.id = e.department_id;

select * from departments d
right join employees e on d.id = e.department_id;

select * from departments d
full join employees e on d.id = e.department_id;

select * from departments d
cross join employees e;

--Используя left join найти департаменты, у которых нет работников
select d.id from departments d
left join employees e on d.id = e.department_id
where e.id is null;

--Используя left и right join написать запросы, которые давали бы одинаковый результат (порядок вывода колонок в эти запросах также должен быть идентичный)
select * from departments d
left join employees e on d.id = e.department_id 
where d.id = 1;

select * from departments d
right join employees e on d.id = e.department_id 
where d.id = 1;

--Создать таблицу teens с атрибутами name, gender и заполнить ее. Используя cross join составить все возможные разнополые пары. Исключите дублирование пар вида Вася-Маша и Маша-Вася
select * from teens t1
cross join teens t2
where t1.gender != t2.gender
and t1.name < t2.name;
