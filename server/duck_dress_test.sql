drop database if exists duck_dress_test;
create database duck_dress_test;
use duck_dress_test;

-- create tables and relationships
create table duck (
duck_id int primary key auto_increment,
duck_image varchar(1000),
hidden bit not null default(1)
);

-- create table app_user (
-- app_user_id int primary key auto_increment,
-- username varchar(100) not null unique,
-- password_hash varchar(100),
-- authorities varchar(100),
-- email varchar(260),
-- hidden bit not null default(1)
-- );

create table app_user (
	app_user_id int primary key auto_increment,
    username varchar(50) not null unique,
    password_hash varchar(2048) not null,     
-- authorities varchar(100),           
	email varchar(260),
	hidden bit not null default(1),
	enabled bit not null default(1)
);

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

create table clothing_item (
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

create table outfit (
outfit_id int primary key auto_increment,
duck_id int not null,
-- app_user_id int not null,
date_created date not null,
shirt_id int not null,
pants_id int not null,
hat_id int not null,
posted bit not null,
hidden bit not null,
	constraint fk_duck_id
		foreign key (duck_id)
        references duck(duck_id),
-- 	constraint fk_app_user_id
-- 		foreign key (app_user_id)
--         references app_user(app_user_id),
	constraint fk_shirt_id_outfit
		foreign key (shirt_id)
        references clothing_item(shirt_id),
	constraint fk_pants_id_outfit
		foreign key (pants_id)
        references clothing_item(pants_id),
	constraint fk_hat_id_outfit
		foreign key (hat_id)
        references clothing_item(hat_id)
);

create table user_outfit (
app_user_id int not null,
outfit_id int not null,
	constraint pk_user_outfit
		primary key(app_user_id, outfit_id),
	constraint fk_user_outfit_app_user_id
		foreign key (app_user_id)
        references app_user(app_user_id),
	constraint fk_user_outfit_outfit_id
		foreign key (outfit_id)
        references outfit(outfit_id)
);

create table comments (
comment_id int primary key auto_increment,
app_user_id int not null,
outfit_id int not null,
content varchar(500),
date_time date not null,
hidden bit not null,
constraint fk_comment_app_user_id
		foreign key (app_user_id)
        references app_user(app_user_id),
constraint fk_outfit_id
		foreign key (outfit_id)
        references outfit(outfit_id)
);

delimiter //
create procedure set_known_good_state()
begin

-- deletes
delete from duck;
alter table duck auto_increment = 1;
delete from clothing_item;
alter table clothing_item auto_increment = 1;
delete from comments;
alter table comments auto_increment = 1;
delete from outfit;
alter table outfit auto_increment = 1;

-- inserts


insert into comments (comment_id, app_user_id, content, outfit_id, date_time, hidden) values
(1, 1, 'I really liked my duck! So cute.', 1, '2023-3-28', 0),
(2, 2, 'Are we actually going to pretend this is cute?', 2, '2023-03-21', 1),
(3, 3, 'Here is mine.', 3, '02-28-2023', 0),
(4, 4, 'I think I should show MY ducks now...', 4, '2023-01-02', 0),
(5, 5, 'Very proud of this one yall!!', 5, '2022-11-28', 0);

insert into duck (duck_id, duck_image, hidden) values
(1, '[INSERT URL HERE]', 0),
(2, '[INSERT URL HERE]', 1),
(3, '[INSERT URL HERE]', 0),
(4, '[INSERT URL HERE]', 1),
(5, '[INSERT URL HERE]', 0);

insert into shirts (shirt_id, item_type, clothing_item_image, hidden) values
(3, 'Shirt', '[INSERT URL HERE]', 0),
(4, 'Shirt', '[INSERT URL HERE]', 0);

insert into pants (pants_id, item_type, clothing_item_image, hidden) values
(5, 'Pants', '[INSERT URL HERE]', 0),
(6, 'Pants', '[INSERT URL HERE]', 0);

insert into hats (hat_id, item_type, clothing_item_image, hidden) values
(1, 'Hat', '[INSERT URL HERE]', 0),
(2, 'Hat', '[INSERT URL HERE]', 0);

insert into clothing_item (shirt_id, pants_id, hat_id) values 
(3, 0, 0),
(3, 0, 0),
(0, 5, 0),
(0, 6, 0),
(0, 0, 1),
(0, 0, 2);

insert into outfit (outfit_id, shirt_id, pants_id, hat_id, date_created, duck_id, posted, hidden) values
(1, 1, 1, 1, '2023-03-28', 1, true, false),
(2, 2, 3, 5, '2023-03-21', 2, true, true),
(3, 3, 5, 4, '2023-02-28', 3, false, false),
(4, 5, 2, 3, '2023-01-02', 4, true, true),
(5, 4, 4, 2, '2023-11-28', 5, false, false);

insert into user_outfit (app_user_id, outfit_id) values
(1, 4),
(2, 3),
(3, 5),
(4, 2),
(5, 1);

end //
delimiter ;


-- Error Code: 1452. Cannot add or update a child row: a foreign key constraint fails (`duck_dress_test`.`outfit`, CONSTRAINT `fk_duck_id` FOREIGN KEY (`duck_id`) REFERENCES `duck` (`duck_id`))
