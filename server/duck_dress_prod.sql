drop database if exists duck_dress_prod;
create database duck_dress_prod;
use duck_dress_prod;

-- create tables and relationships
create table duck (
duck_id int primary key auto_increment,
duck_image varchar(1000),
hidden bit not null
);

create table app_user (
app_user_id int primary key auto_increment,
username varchar(100) not null unique,
password_hash varchar(100),
authorities varchar(100),
email varchar(260),
hidden bit not null default(1)
);

create table clothing_item (
item_id int primary key auto_increment,
item_type varchar(50),
clothing_item_image varchar(1000),
hidden bit not null
);

create table outfit (
outfit_id int primary key auto_increment,
duck_id int not null,
app_user_id int not null,
date_created date not null,
clothing_items varchar(1000),
posted bit not null,
hidden bit not null,
	constraint fk_duck_id
		foreign key (duck_id)
        references duck(duck_id),
	constraint fk_app_user_id
		foreign key (app_user_id)
        references app_user(app_user_id)
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
constraint fk_user_id
		foreign key (app_user_id)
        references app_user(app_user_id),
constraint fk_outfit_id
		foreign key (outfit_id)
        references outfit(outfit_id)
);