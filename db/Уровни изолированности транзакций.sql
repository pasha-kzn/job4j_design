CREATE TABLE products1
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(50),
    producer VARCHAR(50),
    count    INTEGER DEFAULT 0,
    price    INTEGER
);

INSERT INTO products1 (name, producer, count, price)
VALUES ('product_1', 'producer_1', 3, 50);
INSERT INTO products1 (name, producer, count, price)
VALUES ('product_2', 'producer_2', 15, 32);
INSERT INTO products1 (name, producer, count, price)
VALUES ('product_3', 'producer_3', 8, 115);

select * from products1;

--первая транзакция
begin transaction isolation level serializable;

SELECT SUM(count) FROM products1;

UPDATE products1 SET count = 26 WHERE name = 'product_1';

commit;



--первая транзакция
begin transaction isolation level serializable;

SELECT SUM(count) FROM products1;

UPDATE products1 SET count = 26 WHERE name = 'product_2';

commit;