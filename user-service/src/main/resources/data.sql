delete from customer;
delete from person;
delete from address;
INSERT INTO address (id, city, country) VALUES (101, 'Delhi', 'India');
INSERT INTO address (id, city, country) VALUES (102, 'Vellore', 'India');

INSERT INTO person (p_id, name, designation, address_id) VALUES ('shashi@gmail.com', 'shashi', 'SE', 101);
INSERT INTO person (p_id, name, designation, address_id) VALUES ('ravi@gmail.com', 'ravi', 'caption', 102);

--11111
INSERT INTO customer (email, password, role) VALUES ('shashi@gmail.com', '$2a$10$DJKHL5oVyHU.5uKYkpNNt.apKeDLIk1hji4w1/CtjqXoiqHYKQbpK', 'admin');
