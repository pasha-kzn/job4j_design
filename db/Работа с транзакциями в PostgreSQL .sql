CREATE TABLE products1
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(50),
    producer VARCHAR(50),
    count    INTEGER DEFAULT 0,
    price    INTEGER
);

delete from products1;

INSERT INTO products1 (name, producer, count, price)
VALUES ('product_1', 'producer_1', 3, 50);
INSERT INTO products1 (name, producer, count, price)
VALUES ('product_2', 'producer_2', 15, 32);
INSERT INTO products1 (name, producer, count, price)
VALUES ('product_3', 'producer_3', 8, 115);

select * from products1;

BEGIN TRANSACTION;
INSERT INTO products1 (name, producer, count, price) VALUES ('product_4', 'producer_4', 11, 64);
COMMIT TRANSACTION;
SELECT * FROM products1;

BEGIN TRANSACTION;
DELETE FROM products1;
DROP TABLE products1;
ROLLBACK TRANSACTION;
SELECT * FROM products1;

BEGIN TRANSACTION;
INSERT INTO products1 (name, producer, count, price) VALUES ('product_5', 'producer_5', 17, 45);
SAVEPOINT first_savepoint;
DELETE FROM products1 WHERE price = 115;
UPDATE products1 SET price = 75 WHERE name = 'product_1';
SELECT * FROM products1;
ROLLBACK TO first_savepoint;
SELECT * FROM products1;
COMMIT TRANSACTION;


