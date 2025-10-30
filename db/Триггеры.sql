create TABLE productss
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(50),
    producer VARCHAR(50),
    count    INTEGER DEFAULT 0,
    price    INTEGER
);

create
or replace function tax_after()
    RETURNS trigger AS
$$
    begin
		update productss
		set price = price * 1.22
        where id = (select id from inserted);
		return null;
    end;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger_after
    after insert
    on productss
    referencing new TABLE AS
                    inserted
    for each STATEMENT
    EXECUTE procedure tax_after();

create
or replace function tax_before()
    RETURNS trigger AS
$$
    begin
        new.price = new.price * 1.22;
		return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger_before
	before insert
	on productss
	for each row
	execute procedure tax_before();
	
create TABLE history_of_price
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(50),
    price INTEGER,
    date  TIMESTAMP
);

create
or replace function savePrice()
    RETURNS trigger AS
$$
    begin
		insert into history_of_price
		(name, price, date)
		values(new.name, new.price, now());
        return new;
    end;
$$
LANGUAGE 'plpgsql';

create trigger save_price
		after insert
		on productss
		for each row
		execute procedure savePrice();
	
INSERT INTO productss (name, producer, count, price)
VALUES ('product_15', 'producer_15', 10, 100);

select * from productss p;
select * from history_of_price hop;

ALTER TABLE productss ENABLE trigger tax_trigger_after;
ALTER TABLE productss DISABLE trigger tax_trigger_after;
ALTER TABLE productss ENABLE trigger tax_trigger_before;
ALTER TABLE productss DISABLE trigger tax_trigger_before;
DROP function IF EXISTS tax();
DROP trigger tax_trigger_before ON productss;
	

/*
CREATE TABLE productss
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(50),
    producer VARCHAR(50),
    count    INTEGER DEFAULT 0,
    price    INTEGER
);

CREATE
OR REPLACE FUNCTION discount()
    RETURNS trigger AS
$$
    BEGIN
        UPDATE productss
        SET price = price - price * 0.2
        WHERE count <= 5
        AND id = new.id;
        RETURN NEW;
    END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER discount_trigger
    AFTER INSERT
    ON productss
    FOR EACH ROW
    EXECUTE PROCEDURE discount();
   
CREATE
OR REPLACE FUNCTION tax()
    RETURNS trigger AS
$$
    BEGIN
        UPDATE products
        SET price = price - price * 0.2
        WHERE id = (SELECT id FROM inserted)
        AND count <= 5;
        RETURN new;
    END;
$$
LANGUAGE 'plpgsql';
   
CREATE TRIGGER tax_trigger
    AFTER INSERT
    ON productss
    REFERENCING NEW TABLE AS
                    inserted
    FOR EACH STATEMENT
    EXECUTE PROCEDURE tax();
    
INSERT INTO productss (name, producer, count, price)
VALUES ('product_3', 'producer_3', 8, 115);

INSERT INTO productss (name, producer, count, price)
VALUES ('product_1', 'producer_1', 3, 50);

ALTER TABLE productss DISABLE TRIGGER discount_trigger;

select * from productss p;

DROP TRIGGER discount_trigger ON productss;
DROP TRIGGER tax_trigger ON productss;
 */
