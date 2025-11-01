CREATE
OR REPLACE PROCEDURE insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
LANGUAGE 'plpgsql'
AS $$
    BEGIN
        INSERT INTO productss (name, producer, count, price)
        VALUES (i_name, prod, i_count, i_price);
    END
$$;

CALL insert_data('product_2', 'producer_2', 15, 32);

CREATE
OR REPLACE PROCEDURE update_data(u_count integer, tax float, u_id integer)
LANGUAGE 'plpgsql'
AS $$
    BEGIN
        IF u_count > 0 THEN
            UPDATE productss
            SET count = count - u_count
            WHERE id = u_id;
        END IF;
        IF
            tax > 0 THEN
            UPDATE productss
            SET price = price + price * tax;
        END IF;
    END;
$$;

CALL update_data(10, 0, 19);
CALL insert_data('product_1', 'producer_1', 3, 50);
CALL insert_data('product_3', 'producer_3', 8, 115);
CALL update_data(0, 0.2, 0);

DELETE FROM productss;
ALTER SEQUENCE productss_id_seq RESTART WITH 1;  -- генерация id-шников с единицы, видимо

CREATE
OR REPLACE FUNCTION f_insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
RETURNS void
LANGUAGE 'plpgsql'
AS
$$
    BEGIN
        INSERT INTO productss (name, producer, count, price)
        VALUES (i_name, prod, i_count, i_price);
    END;
$$;

SELECT f_insert_data('product_1', 'producer_1', 25, 50);

CREATE
OR REPLACE FUNCTION f_update_data(u_count integer, tax float, u_id integer)
RETURNS integer
LANGUAGE 'plpgsql'
AS
$$
    DECLARE
        result integer;
    BEGIN
        IF u_count > 0 THEN
            UPDATE productss
            SET count = count - u_count
            WHERE id = u_id;
            SELECT INTO result count
            FROM productss
            WHERE id = u_id;
        END IF;
        IF tax > 0 THEN
            UPDATE productss
            SET price = price + price * tax;
            SELECT INTO result SUM(price)
            FROM productss;
        END IF;
        RETURN result;
    END;
$$;

SELECT f_update_data(10, 0, 1);
SELECT f_insert_data('product_2', 'producer_2', 15, 32);
SELECT f_insert_data('product_3', 'producer_3', 8, 115);
SELECT f_update_data(0, 0.2, 0);

----------------------------------------------
--ДЗ 3. Хранимые процедуры и функции

CREATE
OR REPLACE PROCEDURE delete_data(d_id integer)
LANGUAGE 'plpgsql'
AS $$
    BEGIN
        delete from productss 
        where id = d_id;
    END
$$;

CREATE
OR REPLACE function delete_data()
RETURNS integer
LANGUAGE 'plpgsql'
AS $$
    DECLARE
        result integer;
    BEGIN
        select count(*) into result
        from productss
        where count = 0;
	    delete from productss
        where count = 0;
       return result;
    END;
$$;

SELECT f_insert_data('product_31', 'producer_31', 8, 115);
CALL delete_data(4);
select delete_data();

select * from productss	;




/*
ALTER PROCEDURE update_data(u_count integer, tax float, u_id integer) RENAME TO update;
DROP PROCEDURE update_data(u_count integer, tax float, u_id integer);
select * from productss;
ALTER TABLE productss ENABLE trigger tax_trigger_after;
ALTER TABLE productss DISABLE trigger tax_trigger_after;
ALTER TABLE productss ENABLE trigger tax_trigger_before;
ALTER TABLE productss DISABLE trigger tax_trigger_before;
DROP function IF EXISTS tax();
DROP trigger tax_trigger_before ON productss;
 */
*/