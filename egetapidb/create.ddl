
    create table Developer (
        devId bigserial not null,
        apiKey uuid,
        email varchar(255) unique,
        primary key (devId)
    );

    create table Post (
        dislikes integer not null,
        likes integer not null,
        postId bigserial not null,
        userId bigint,
        text varchar(255),
        title varchar(255),
        whoDisliked bigint array,
        whoLiked bigint array,
        primary key (postId)
    );

    create table users (
        id bigserial not null,
        ImgUrl varchar(255),
        email varchar(255) unique,
        userPassword varchar(255),
        username varchar(255) unique,
        primary key (id)
    );
INSERT INTO users (username, userPassword, email) VALUES ('Dennis', '123', 'grotta1@gmail.com');
INSERT INTO users (username, userPassword, email) VALUES ('Love', '123', 'grotta2@gmail.com');
INSERT INTO users (username, userPassword, email) VALUES ('Eric', '123', 'grotta3@gmail.com');
INSERT INTO users (username, userPassword, email) VALUES ('Sam', '123', 'grotta4@gmail.com');
INSERT INTO users (username, userPassword, email) VALUES ('Viktor', '123', 'grotta5@gmail.com');
INSERT INTO Post (title, text, userId, likes, dislikes,whoLiked,whoDisliked) VALUES ('Mat', 'Hamburgare är gott', 1, 2, 2, ARRAY[1,2]::bigint[], ARRAY[2,1]::bigint[]);
INSERT INTO Post (title, text, userId, likes, dislikes,whoLiked,whoDisliked) VALUES ('Mat', 'Pizza är gott', 1, 0, 0,  ARRAY[]::bigint[], ARRAY[]::bigint[]);
INSERT INTO Post (title, text, userId, likes, dislikes,whoLiked,whoDisliked) VALUES ('Mat', 'Pannkakor är gott', 1, 0, 0,  ARRAY[]::bigint[], ARRAY[]::bigint[]);
INSERT INTO Post (title, text, userId, likes, dislikes,whoLiked,whoDisliked) VALUES ('Mat', 'Hamburgare är gott', 2, 0, 0,  ARRAY[]::bigint[], ARRAY[]::bigint[]);
INSERT INTO Post (title, text, userId, likes, dislikes,whoLiked,whoDisliked) VALUES ('Mat', 'Pizza är gott', 2, 0, 1,  ARRAY[]::bigint[], ARRAY[3]::bigint[]);
INSERT INTO Post (title, text, userId, likes, dislikes,whoLiked,whoDisliked) VALUES ('Mat', 'Pannkakor är gott', 2, 0, 1,  ARRAY[]::bigint[], ARRAY[4]::bigint[]);
INSERT INTO Developer ( email, apikey) VALUES ( 'jan.banan@gmail.com', 'a9991969-8b6a-413c-ae14-82dc423e4425');
