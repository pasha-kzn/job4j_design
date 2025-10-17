-- Создание таблиц
CREATE TABLE customers (
    id SERIAL  PRIMARY KEY,
    name VARCHAR(50),
    city VARCHAR(50)
);

CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    customer_id INT references customers(id),
    order_date DATE
);

CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    product_name VARCHAR(50),
    price DECIMAL(10, 2)
);

CREATE TABLE order_items (
    id SERIAL PRIMARY KEY,
    order_id INT references orders(id),
    product_id INT references products(id),
    quantity INT
);

-- Инициализация данных
INSERT INTO customers(name, city) VALUES
('Иван Иванов', 'Москва'),
('Ольга Петрова', 'Казань'),
('Дмитрий Смирнов', 'Санкт-Петербург');

INSERT INTO orders(customer_id, order_date) VALUES
(1, '2025-10-01'),
(1, '2025-10-03'),
(2, '2025-10-10');

INSERT INTO products(product_name, price) VALUES
('Книга', 500.00),
('Ноутбук', 70000.00),
('Ручка', 50.00);

INSERT INTO order_items(order_id, product_id, quantity) VALUES
(1, 1, 2),
(1, 3, 5),
(2, 2, 1),
(3, 1, 1);

select * from customers;
select * from orders;
select * from products;
select * from order_items;

-- Запрос с множественными JOIN
select
    c.name, c.city, 
    o.order_date, 
    p.product_name, p.price, 
    oi.quantity
FROM customers c
JOIN orders o ON c.id = o.customer_id
JOIN order_items oi ON o.id = oi.order_id
JOIN products p ON oi.product_id = p.id;

-- Создание VIEW, который возвращает те же данные
CREATE VIEW customer_orders_view AS
select
    c.name, c.city, 
    o.order_date, 
    p.product_name, p.price, 
    oi.quantity
FROM customers c
JOIN orders o ON c.id = o.customer_id
JOIN order_items oi ON o.id = oi.order_id
JOIN products p ON oi.product_id = p.id;

select * from customer_orders_view
