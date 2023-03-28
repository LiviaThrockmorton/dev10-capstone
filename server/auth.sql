use duck_dress_prod;

drop table if exists app_user_authority;
drop table if exists app_authority;
drop table if exists app_user;

create table app_user (
app_user_id int primary key auto_increment,
username varchar(100) not null unique,
password_hash varchar(100),
authorities varchar(100),
email varchar(260),
hidden bit not null default(1)
);

create table app_authority (
	app_authority_id int primary key auto_increment,
    `name` varchar(25) not null
);

create table app_user_authority (
	app_user_id int not null,
    app_authority_id int not null,
    constraint pk_app_user_authority
		primary key (app_user_id, app_authority_id),
	constraint fk_app_user_authority_app_user_id
		foreign key (app_user_id)
		references app_user(app_user_id),
	constraint fk_app_user_authority_app_authority_id
		foreign key (app_authority_id)
		references app_authority(app_authority_id)
);

-- data
insert into app_authority (`name`) values
	('USER'),
    ('ADMIN');
    
insert into app_user (username, password_hash) values
	('user', '$2a$10$.zydOtTivoIcJP1CVcbEbOEaSfgDefR8yIRp3W.oZRFrvOSEOAOm6'), -- password "user"
    ('admin', '$2a$10$8e6e6BDT1RUt0WuHIXqYb.szJZH9RYNRGScE.WlC52EB/92L6CasW'); -- password "admin"
    
insert into app_user_authority(app_user_id, app_authority_id) values
	(1, 1),
    (2, 2);