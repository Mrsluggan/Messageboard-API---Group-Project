
    create table users (
        id bigserial not null,
        apikey uuid,
        username varchar(255),
        primary key (id)
    );
