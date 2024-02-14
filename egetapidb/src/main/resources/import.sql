-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

INSERT INTO users (username, userPassword, userEmail, profileImg) VALUES ('Dennis', '123', 'grotta1@gmail.com', 'https://robohash.org/minimamolestiaequia.png?size=50x50&set=set1');
INSERT INTO users (username, userPassword, userEmail, profileImg) VALUES ('Love', '123', 'grotta2@gmail.com', 'https://robohash.org/adipiscioccaecatirerum.png?size=50x50&set=set1');
INSERT INTO users (username, userPassword, userEmail, profileImg) VALUES ('Eric', '123', 'grotta3@gmail.com', 'https://robohash.org/aperiampariatureos.png?size=50x50&set=set1');
INSERT INTO users (username, userPassword, userEmail, profileImg) VALUES ('Sam', '123', 'grotta4@gmail.com', 'https://robohash.org/sintvoluptatemexpedita.png?size=50x50&set=set1');
INSERT INTO users (username, userPassword, userEmail, profileImg) VALUES ('Viktor', '123', 'grotta5@gmail.com', 'https://robohash.org/fugaestdolores.png?size=50x50&set=set1');

INSERT INTO Post (title, text, userId, likes, dislikes,whoLiked,whoDisliked) VALUES ('Mat', 'Hamburgare är gott', 1, 2, 2, ARRAY[1,2]::bigint[], ARRAY[2,1]::bigint[]);
INSERT INTO Post (title, text, userId, likes, dislikes,whoLiked,whoDisliked) VALUES ('Mat', 'Pizza är gott', 1, 0, 0,  ARRAY[]::bigint[], ARRAY[]::bigint[]);
INSERT INTO Post (title, text, userId, likes, dislikes,whoLiked,whoDisliked) VALUES ('Mat', 'Pannkakor är gott', 1, 0, 0,  ARRAY[]::bigint[], ARRAY[]::bigint[]);
INSERT INTO Post (title, text, userId, likes, dislikes,whoLiked,whoDisliked) VALUES ('Mat', 'Hamburgare är gott', 2, 0, 0,  ARRAY[]::bigint[], ARRAY[]::bigint[]);
INSERT INTO Post (title, text, userId, likes, dislikes,whoLiked,whoDisliked) VALUES ('Mat', 'Pizza är gott', 2, 0, 1,  ARRAY[]::bigint[], ARRAY[3]::bigint[]);
INSERT INTO Post (title, text, userId, likes, dislikes,whoLiked,whoDisliked) VALUES ('Mat', 'Pannkakor är gott', 2, 0, 1,  ARRAY[]::bigint[], ARRAY[4]::bigint[]);


INSERT INTO Developer (devEmail, apikey) VALUES ( 'jan.banan@gmail.com', 'a9991969-8b6a-413c-ae14-82dc423e4425');
