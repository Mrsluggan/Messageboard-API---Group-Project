
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
