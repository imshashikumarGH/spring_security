delete from authorities_customers;
delete from customer;
delete from person;
delete from address;
delete from authorities;
INSERT INTO address (id, city, country) VALUES (101, 'Delhi', 'India');
INSERT INTO address (id, city, country) VALUES (102, 'Vellore', 'India');

INSERT INTO authorities (name) VALUES ('admin');
INSERT INTO authorities (name) VALUES ('user');



INSERT INTO person (p_id, name, designation, address_id) VALUES ('shashi@gmail.com', 'shashi', 'SE', 101);
INSERT INTO person (p_id, name, designation, address_id) VALUES ('ravi@gmail.com', 'ravi', 'caption', 102);

--11111
INSERT INTO customer (email, password) VALUES ('shashi', '$2a$10$DJKHL5oVyHU.5uKYkpNNt.apKeDLIk1hji4w1/CtjqXoiqHYKQbpK');

INSERT INTO public.authorities_customers(authorities_name, customers_email) VALUES ('admin', 'shashi');
INSERT INTO public.authorities_customers(authorities_name, customers_email) VALUES ('user', 'shashi');
