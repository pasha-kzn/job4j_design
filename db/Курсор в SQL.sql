CREATE TABLE productos
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(50),
    count INTEGER DEFAULT 0,
    price INTEGER
);

INSERT INTO productos (name, count, price)
VALUES ('product_1', 1, 5);
INSERT INTO productos (name, count, price)
VALUES ('product_2', 2, 10);
INSERT INTO productos (name, count, price)
VALUES ('product_3', 3, 15);
INSERT INTO productos (name, count, price)
VALUES ('product_4', 4, 20);
INSERT INTO productos (name, count, price)
VALUES ('product_5', 5, 25);
INSERT INTO productos (name, count, price)
VALUES ('product_6', 6, 30);
INSERT INTO productos (name, count, price)
VALUES ('product_7', 7, 35);
INSERT INTO productos (name, count, price)
VALUES ('product_8', 8, 40);
INSERT INTO productos (name, count, price)
VALUES ('product_9', 9, 45);
INSERT INTO productos (name, count, price)
VALUES ('product_10', 10, 50);
INSERT INTO productos (name, count, price)
VALUES ('product_11', 11, 55);
INSERT INTO productos (name, count, price)
VALUES ('product_12', 12, 60);
INSERT INTO productos (name, count, price)
VALUES ('product_13', 13, 65);
INSERT INTO productos (name, count, price)
VALUES ('product_14', 14, 70);
INSERT INTO productos (name, count, price)
VALUES ('product_15', 15, 75);
INSERT INTO productos (name, count, price)
VALUES ('product_16', 16, 80);
INSERT INTO productos (name, count, price)
VALUES ('product_17', 17, 85);
INSERT INTO productos (name, count, price)
VALUES ('product_18', 18, 90);
INSERT INTO productos (name, count, price)
VALUES ('product_19', 19, 95);
INSERT INTO productos (name, count, price)
VALUES ('product_20', 20, 100);

BEGIN;
DECLARE
cursor_productos CURSOR FOR
SELECT * FROM productos;
FETCH 10 FROM cursor_productos;
FETCH NEXT FROM cursor_productos;
FETCH NEXT FROM cursor_productos;
MOVE FORWARD 2 FROM cursor_productos;
FETCH NEXT FROM cursor_productos;
CLOSE cursor_productos;
COMMIT;

--Домашнее задание
BEGIN;
declare
cursor_homework CURSOR for
SELECT * FROM productos;
FETCH LAST FROM cursor_homework;
MOVE BACKWARD 4 FROM cursor_homework;
FETCH PRIOR  FROM cursor_homework;
MOVE BACKWARD 7 FROM cursor_homework;
FETCH PRIOR  FROM cursor_homework;
MOVE BACKWARD 4 FROM cursor_homework;
FETCH PRIOR  FROM cursor_homework;
FETCH PRIOR  FROM cursor_homework;
CLOSE cursor_homework;
COMMIT;
