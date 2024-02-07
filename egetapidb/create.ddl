
    create table Post (
        idPost bigserial not null,
        text varchar(255),
        title varchar(255),
        primary key (idPost)
    );

    create table users (
        id bigserial not null,
        apikey uuid,
        username varchar(255),
        primary key (id)
    );

    create table users_Post (
        User_id bigint not null,
        posts_idPost bigint not null unique
    );

    alter table if exists users_Post 
       add constraint FKny4b7thmva88g7e8oji2fe5m1 
       foreign key (posts_idPost) 
       references Post;

    alter table if exists users_Post 
       add constraint FKs95kqgg4wgnctmjnrn0k2rydx 
       foreign key (User_id) 
       references users;
