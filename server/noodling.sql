drop database if exists noodling;
create database noodling;
use noodling;




create table shirts (
shirt_id int primary key auto_increment,
item_type varchar(50),
clothing_item_image varchar(1000),
hidden bit not null
);

create table pants (
pants_id int primary key auto_increment,
item_type varchar(50),
clothing_item_image varchar(1000),
hidden bit not null
);

create table hats (
hat_id int primary key auto_increment,
item_type varchar(50),
clothing_item_image varchar(1000),
hidden bit not null
);

create table clothing_items (
item_id int primary key auto_increment,
shirt_id int,
pants_id int,
hat_id int,
	constraint fk_shirt_id
		foreign key (shirt_id)
        references shirts(shirt_id),
	constraint fk_pants_id
		foreign key (pants_id)
        references pants(pants_id),
	constraint fk_hat_id
		foreign key (hat_id)
        references hats(hat_id)
);

-- create table outfit (
-- outfit_id int primary key auto_increment,
-- duck_id int not null,
-- -- app_user_id int not null,
-- date_created date not null,
-- item_id int not null,
-- posted bit not null,
-- hidden bit not null,
-- 	constraint fk_duck_id
-- 		foreign key (duck_id)
--         references duck(duck_id),
-- 	constraint fk_item_id
-- 		foreign key (item_id)
--         references clothing_items(item_id)
-- );


insert into hats (hat_id, item_type, clothing_item_image, hidden) values
(1, 'Hat', '[INSERT URL HERE]', 0),
(2, 'Hat', '[INSERT URL HERE]', 0);

insert into shirts (shirt_id, item_type, clothing_item_image, hidden) values
(3, 'Shirt', '[INSERT URL HERE]', 0),
(4, 'Shirt', '[INSERT URL HERE]', 0);

insert into pants (pants_id, item_type, clothing_item_image, hidden) values
(5, 'Pants', '[INSERT URL HERE]', 0),
(6, 'Pants', '[INSERT URL HERE]', 0);