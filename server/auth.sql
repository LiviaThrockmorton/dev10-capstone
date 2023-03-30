use duck_dress_test;

-- Change this later ^^^ 
-- use duck_dress_prod
-- also remember to change in the application.properties! 

drop table if exists app_user_role;
drop table if exists app_role;
-- drop table if exists app_user;

-- create table app_user (
--     app_user_id int primary key auto_increment,
--     username varchar(50) not null unique,
--     password_hash varchar(2048) not null,
--     enabled bit not null default(1)
-- );



create table app_role (
    app_role_id int primary key auto_increment,
    `name` varchar(50) not null unique
);

create table app_user_role (
    app_user_id int not null,
    app_role_id int not null,
    constraint pk_app_user_authority
        primary key (app_user_id, app_authority_id),
    constraint fk_app_user_authority_user_id
        foreign key (app_user_id)
        references app_user(app_user_id),
    constraint fk_app_user_authority_authority_id
        foreign key (app_authority_id)
        references app_authority(app_authority_id)
);

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