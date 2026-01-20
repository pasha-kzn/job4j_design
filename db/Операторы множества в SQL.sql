CREATE TABLE movies
(
    id       SERIAL PRIMARY KEY,
    name     TEXT,
    director TEXT
);

CREATE TABLE books
(
    id     SERIAL PRIMARY KEY,
    title  TEXT,
    author TEXT
);

INSERT INTO movies (name, director)
VALUES ('Марсианин', 'Ридли Скотт'),
       ('Матрица', 'Братья Вачовски'),
       ('Властелин колец', 'Питер Джексон'),
       ('Гарри Поттер и узник Азкабана', 'Альфонсо Куарон'),
       ('Железный человек', 'Джон Фавро');

INSERT INTO books (title, author)
VALUES ('Гарри Поттер и узник Азкабана', 'Джоан Роулинг'),
       ('Властелин колец', 'Джон Толкин'),
       ('1984', 'Джордж Оруэлл'),
       ('Марсианин', 'Энди Уир'),
       ('Божественная комедия', 'Данте Алигьери');
      
select * from movies;
select * from books;       
      
-- выведите названия всех фильмов, которые сняты по книге;
select m.name from movies m
intersect
select b.title from books b;

-- выведите все названия книг, у которых нет экранизации;
select m.name from movies m
except
select b.title from books b;

-- выведите все уникальные названия произведений из таблиц movies и books (т.е фильмы, которые сняты не по книге, и книги без экранизации)
select m.name from movies m
union
select b.title from books b;