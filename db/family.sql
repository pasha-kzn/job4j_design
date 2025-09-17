create table members(
id serial primary key,
name varchar(63),
sex varchar(7),
role varchar(7),
age integer
)



insert into members(name, sex, role, age) values('Pavel', 'male', 'father', 45)

insert into members(name, sex, role, age) values('Vera', 'female', 'mather', 44)

insert into members(name, sex, role, age) values('Ilia', 'male', 'son', 15)

insert into members(name, sex, role, age) values('Polina', 'female', 'daughte', 13)



delete from members m where m.id in (2, 3, 6)


select * from members