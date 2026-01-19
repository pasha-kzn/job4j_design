create TABLE companies
(
    id   INT PRIMARY KEY,
    city TEXT
);

create TABLE goods
(
    id         INT PRIMARY KEY,
    name       TEXT,
    company_id INT REFERENCES companies (id),
    price      INT
);

create TABLE sales_managers
(
    id          INT PRIMARY KEY,
    last_name   TEXT,
    first_name  TEXT,
    company_id  INT REFERENCES companies (id),
    manager_fee INT
);

create TABLE managers
(
    id         INT PRIMARY KEY,
    company_id INT REFERENCES companies (id)
);

insert into companies
values (1, 'Москва'),
       (2, 'Нью-Йорк'),
       (3, 'Мюнхен');

insert into goods
values (1, 'Небольшая квартира', 3, 5000),
       (2, 'Квартира в центре', 1, 4500),
       (3, 'Квартира у метро', 1, 3200),
       (4, 'Лофт', 2, 6700),
       (5, 'Загородный дом', 2, 9800);

insert into sales_managers
values (1, 'Доу', 'Джон', 2, 2250),
       (2, 'Грубер', 'Ганс', 3, 3120),
       (3, 'Смит', 'Сара', 2, 1640),
       (4, 'Иванов', 'Иван', 1, 4500),
       (5, 'Купер', 'Грета', 3, 2130);

insert into managers
values (1, 2),
       (2, 3),
       (4, 1);
       
select * from sales_managers
where manager_fee > (select avg(manager_fee) from sales_managers);

select name as real_estate, price, (select avg(price) from goods) as avg_price from goods;

select avg(manager_fee)
from sales_managers where sales_managers.id not in (select managers.id from managers);

select company_id, avg(price) as average_price
from goods
group by company_id
having avg(price) > (select max(price) from goods) / 2;

--------------------------------------------------------
--Домашнее задание

create TABLE customers1
(
    id         SERIAL PRIMARY KEY,
    first_name TEXT,
    last_name  TEXT,
    age        INT,
    country    TEXT
);

create TABLE orders1
(
    id          SERIAL PRIMARY KEY,
    amount      INT,
    customer_id INT REFERENCES customers (id)
);

select * from customers1;
select * from orders1;

insert into customers1 (first_name, last_name, age, country) values ('Иван', 'Иванов', 37, 'Russia');
insert into customers1 (first_name, last_name, age, country) values ('Valter', 'Schmidt', 27, 'Deutchland');
insert into customers1 (first_name, last_name, age, country) values ('Petr', 'Pavel', 57, 'Chech');
insert into customers1 (first_name, last_name, age, country) values ('Manuel', 'Makron', 47, 'France');
insert into customers1 (first_name, last_name, age, country) values ('Boris', 'Jonson', 67, 'Great Britan');

insert into orders1 (amount, customer_id) values (1000, 1);
insert into orders1 (amount, customer_id) values (2000, 2);
insert into orders1 (amount, customer_id) values (3000, 3);

select * from customers1 c where c.id not in (select distinct o.id from orders1 o);