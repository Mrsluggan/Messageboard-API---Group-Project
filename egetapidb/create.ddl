
    create table Post (
        idPost bigserial not null,
        user_id bigint,
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

    alter table if exists Post 
       add constraint FK58gj8nsg0ksi7s2y8jjtguap7 
       foreign key (user_id) 
       references users;
