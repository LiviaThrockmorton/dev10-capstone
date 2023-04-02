
drop database if exists duck_dress_test;
create database duck_dress_test;
use duck_dress_test;

-- create tables and relationships
create table duck (
duck_id int primary key auto_increment,
duck_image varchar(1000),
hidden boolean not null default(false)
);

-- create table app_user (
-- app_user_id int primary key auto_increment,
-- username varchar(100) not null unique,
-- password_hash varchar(100),
-- authorities varchar(100),
-- email varchar(260),
-- hidden boolean not null default(false),
-- );

create table app_user (
	app_user_id int primary key auto_increment,
    username varchar(50) not null unique,
    password_hash varchar(2048) not null,     
-- authorities varchar(100),           
	email varchar(260),
	hidden boolean not null default(false),
	enabled boolean not null default(false)
);


-- create table clothing_item (
-- item_id int primary key auto_increment,
-- shirt_id int,
-- pants_id int,
-- hat_id int,
-- 	constraint fk_shirt_id
-- 		foreign key (shirt_id)
--         references shirts(shirt_id),
-- 	constraint fk_pants_id
-- 		foreign key (pants_id)
--         references pants(pants_id),
-- 	constraint fk_hat_id
-- 		foreign key (hat_id)
--         references hats(hat_id)
-- );
create table clothing_item (
item_id int primary key auto_increment,
item_type varchar(50),
clothing_item_image varchar(1000),
hidden boolean not null default(false)
);


create table outfit (
outfit_id int primary key auto_increment,
duck_id int not null,
app_user_id int not null,
date_created date not null,
shirt_id int not null,
pants_id int not null,
hat_id int not null,
posted boolean not null default(false),
hidden boolean not null default(false),
	constraint fk_duck_id
		foreign key (duck_id)
        references duck(duck_id),
	constraint fk_app_user_id
		foreign key (app_user_id)
        references app_user(app_user_id),
	constraint fk_shirt_id_outfit
		foreign key (shirt_id)
        references clothing_item(item_id),
	constraint fk_pants_id_outfit
		foreign key (pants_id)
        references clothing_item(item_id),
	constraint fk_hat_id_outfit
		foreign key (hat_id)
        references clothing_item(item_id)
);

-- create table user_outfit (
-- app_user_id int not null,
-- outfit_id int not null,
-- 	constraint pk_user_outfit
-- 		primary key(app_user_id, outfit_id),
-- 	constraint fk_user_outfit_app_user_id
-- 		foreign key (app_user_id)
--         references app_user(app_user_id),
-- 	constraint fk_user_outfit_outfit_id
-- 		foreign key (outfit_id)
--         references outfit(outfit_id)
-- );

create table comments (
comment_id int primary key auto_increment,
app_user_id int not null,
outfit_id int not null,
content varchar(500),
date_time date not null,
hidden boolean not null default(false),
constraint fk_comment_app_user_id
		foreign key (app_user_id)
        references app_user(app_user_id),
constraint fk_outfit_id
		foreign key (outfit_id)
        references outfit(outfit_id)
);


-- drop table if exists app_user_authority;
-- drop table if exists app_authority;
-- drop table if exists app_user;

-- create table app_user (
--     app_user_id int primary key auto_increment,
--     username varchar(50) not null unique,
--     password_hash varchar(2048) not null,
--     enabled bit not null default(1)
-- );



create table app_authority (
    app_authority_id int primary key auto_increment,
    `name` varchar(50) not null unique
);

create table app_user_authority (
    app_user_id int not null,
    app_authority_id int not null,
    constraint pk_app_user_authority
        primary key (app_user_id, app_authority_id),
    constraint fk_app_user_authority_user_id
        foreign key (app_user_id)
        references app_user(app_user_id),
    constraint fk_app_user_authority_authority_id
        foreign key (app_authority_id)
        references app_authority(app_authority_id)
);


delimiter //
create procedure set_known_good_state()
begin


-- deletes
delete from app_user_authority;
alter table app_user_authority auto_increment = 1;

delete from app_authority;
alter table app_authority auto_increment = 1;
delete from comments;
alter table comments auto_increment = 1;
delete from outfit;
alter table outfit auto_increment = 1;
delete from clothing_item;
alter table clothing_item auto_increment = 1;

delete from duck;
alter table duck auto_increment = 1;
delete from app_user;
alter table app_user auto_increment = 1;

-- delete from user_outfit;
-- alter table user_outfit auto_increment = 1;

-- inserts

insert into app_authority (`name`) values
    ('USER'),
    ('ADMIN');

-- passwords are set to "P@ssw0rd!"
insert into app_user (username, password_hash, email, hidden, enabled)
    values
    ('jsmith', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 'john@smith.com', 0, 1),
    ('sjones', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 'sally@jones.com', 0, 1);

insert into app_user_authority
    values
    (1, 2),
    (2, 1);


insert into duck (duck_id, duck_image, hidden) values
(1, '/svgs/yellow-duck.svg', 0),
(2, '/svgs/brown-duck.svg', 0),
(3, '[INSERT URL HERE]', 1),
(4, '[INSERT URL HERE]', 1),
(5, '[INSERT URL HERE]', 1);

insert into clothing_item (item_id, item_type, clothing_item_image, hidden) values
(1, 'shirt', '[INSERT URL HERE]', 0),
(2, 'shirt', '[INSERT URL HERE]', 0),
(3, 'pants', '[INSERT URL HERE]', 1),
(4, 'pants', '[INSERT URL HERE]', 0),
(5, 'hat', '[INSERT URL HERE]', 0),
(6, 'hat', '[INSERT URL HERE]', 0);

-- insert into clothing_item (shirt_id, pants_id, hat_id) values 
-- (1, null, null),
-- (2, null, null),
-- (null, 1, null),
-- (null, 2, null),
-- (null, null, 1),
-- (null, null, 2);

insert into outfit (outfit_id, app_user_id, shirt_id, pants_id, hat_id, date_created, duck_id, posted, hidden) values
(1, 1, 1, 3, 5, '2023-03-28', 1, true, false),
(2, 2, 2, 3, 5,  '2023-03-21', 2, true, true),
(3, 2, 1, 3, 5, '2023-02-28', 3, false, false),
(4, 2, 2, 3, 5, '2023-01-02', 4, true, true),
(5, 1, 1, 3, 5, '2023-11-28', 5, false, false);


insert into comments (comment_id, app_user_id, content, outfit_id, date_time, hidden) values
(1, 1, 'I really liked my duck! So cute.', 1, '2023-3-28', 0),
(2, 2, 'Are we actually going to pretend this is cute?', 2, '2023-03-21', 1),
(3, 1, 'Here is mine.', 3, '2023-02-28', 0),
(4, 2, 'I think I should show MY ducks now...', 4, '2023-01-02', 0),
(5, 1, 'Very proud of this one yall!!', 5, '2022-11-28', 0),
(6, 1, 'Rude comment that should be hidden!', 5, '2022-11-29', 1);



end //
delimiter ;


set sql_safe_updates = 0;
call set_known_good_state();
set sql_safe_updates = 1;
