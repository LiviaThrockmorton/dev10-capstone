drop database if exists duck_dress_test;
create database duck_dress_test;
use duck_dress_test;

-- create tables and relationships
create table duck (
duck_id int primary key auto_increment,
hidden boolean,
duck_image varchar(1000)
);

create table app_user (
user_id int primary key auto_increment,
username varchar(100),
`password` varchar(100),
authorities varchar(100),
hidden boolean,
email varchar(260)
);

create table clothing_item (
item_id int primary key auto_increment,
hat_id int not null,
shirt_id int not null,
pants_id int not null,
item_type varchar(50),
clothing_item_image varchar(1000),
hidden boolean
);

create table outfit (
outfit_id int primary key auto_increment,
date_created date not null,
clothing_items varchar(1000),
posted int boolean,
hidden boolean,
	constraint fk_shirt_id
		foreign key (shirt_id)
        references shirt(shirt_id),
	constraint fk_pants_id
		foreign key (pants_id)
        references pants(pants_id),
	constraint fk_hat_id
		foreign key (hat_id)
        references hat(hat_id),
	constraint fk_duck_id
		foreign key (dick_id)
        references duck(duck_id),
	constraint fk_user_id
		foreign key (user_id)
        references app_user(user_id)
);


create table user_outfit (
user_id int not null,
outfit_id int not null,
	constraint pk_user_outfit
		primary key(user_id, outfit_id),
	constraint fk_user_outfit_user_id
		foreign key (user_id)
        references app_user(user_id),
	constraint fk_user_outfit_outfit_id
		foreign key (outfit_id)
        references outfit(outfit_id)
);

create table comment (
comment_id int primary key auto_increment,
content varchar(500),
date_time date not null,
hidden boolean,
constraint fk_user_id
		foreign key (user_id)
        references app_user(user_id),
constraint fk_outfit_id
		foreign key (outfit_id)
        references outfit(outfit_id)
);