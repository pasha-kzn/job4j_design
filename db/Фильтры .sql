CREATE TABLE type
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(255)
);
    

CREATE TABLE product
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(255),
    type_id int references type(id),
    expired_date date,    
    price FLOAT
);

insert into type(name) values('сыр'), ('мороженное'), ('молоко');
insert into product(name, type_id, expired_date, price) values('Сырок плавленный', 1, '01.11.2025', 25.99),
	('Сырок тугоплавкий', 1, '30.10.2025', 255.99),
	('Моцарелла', 1, '29.10.2025', 152.99),
	('Маасдам', 1, '28.10.2025', 825.99),
	('Тильзистер', 1, '27.10.2025', 325.99),
	('Голландский', 1, '26.10.2025', 425.99),
	('Сырок Viola', 1, '25.10.2025', 525.99),
	('Сырок славянский', 1, '24.10.2025', 625.99),
	('Сыр косичка', 1, '23.10.2025', 725.99),
	('Камамбер', 1, '22.10.2025', 825.99),
	('Сыр пластмассовый', 1, '21.10.2025', 125.99),
	('Мороженное эскимо', 2, '20.10.2025', 219.99),
	('Мороженное пломбир', 2, '19.10.2025', 119.99),
	('Мороженное сливочное', 2, '18.10.2025', 79.99),
	('Мороженное молочное', 2, '17.10.2025', 49.99),
	('Мороженное эскимо', 2, '01.10.1987', 0.07),
	('Молоко отборное', 3, '17.10.2025', 129.90),
	('Молоко топленое', 3, '16.10.2025', 139.90),
	('Молоко 3,2%', 3, '15.10.2025', 99.90),
	('Молоко отборное 2,5%', 3, '14.10.2025', 89.90);

select * from "type" t 

--Написать запрос получение всех продуктов с типом "СЫР"
select p."name" from product p 
join "type" t on p.type_id = t.id 
where t."name" = 'сыр';

--Написать запрос получения всех продуктов, у кого в имени есть слово "мороженое"
select p."name" from product p 
join "type" t on p.type_id = t.id 
where t."name" like '%мороженное%';

--Написать запрос, который выводит все продукты, срок годности которых уже истек
select * from product p where p.expired_date < now() 

--Написать запрос, который выводит самый дорогой продукт
select * from product p where p.price = (select max(price) from product)

--Написать запрос, который выводит для каждого типа количество продуктов к нему принадлежащих
select t."name", count(p.*) from "type" t 
join product p on t.id = p.type_id 
group by t.id 

--Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select * from product p 
where p.type_id in (select t.id from "type" t where t.name in ('сыр', 'молоко'))


--Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук
select p.type_id from product p 
group by p.type_id 
having count(p."name") < 10 


--Вывести все продукты и их тип
select t."name" as type_name, p.* from product p 
join "type" t on p.type_id = t.id 
order by t.id 



