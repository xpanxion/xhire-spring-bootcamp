create database cars;

use cars;

create table customers (
	id int not null auto_increment,
    first_name varchar(50),
    last_name varchar(50),
    phone varchar(20),
    PRIMARY KEY (id)
);

create table cars(
	id int not null auto_increment,
    make varchar(50),
    model varchar(50),
    price double,
    primary key (id)
);

create table orders (
	id int not null auto_increment,
    customer_id int not null,
    car_id int not null, 
    order_date date not null,
    primary key(id),
    foreign key(customer_id) references customers(id),
    foreign key (car_id) references cars(id)
);