use cars;

insert into customers(first_name, last_name, phone) values ('Peter', 'Griffin', '555-222-3232');
insert into customers(first_name, last_name, phone) values ('Hank', 'Hill', '555-444-1232');
insert into customers(first_name, last_name, phone) values ('Bob', 'Beltcher', '555-123-5555');


insert into cars(make, model, price) values ('AMC', 'Gremlin', 7453.34);
insert into cars(make, model, price) values ('Ford', 'Pinto', 548.68);
insert into cars(make, model, price) values ('Chevy', 'Chevette', 1568.34);

insert into orders( customer_id, car_id, order_date) values (
	(select id from customers where first_name = 'Peter' and last_name = 'Griffin'),
    (select id from cars where make='AMC' and model='Gremlin'),
    '2015-04-23');
    
insert into orders( customer_id, car_id, order_date) values (
	(select id from customers where first_name = 'Hank' and last_name = 'Hill'),
    (select id from cars where make='Ford' and model='Pinto'),
    '1998-08-04');
    
insert into orders( customer_id, car_id, order_date) values (
	(select id from customers where first_name = 'Bob' and last_name = 'Beltcher'),
    (select id from cars where make='Chevy' and model='Chevette'),
    '2018-01-15');