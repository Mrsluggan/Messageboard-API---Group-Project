-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

INSERT INTO users (username) VALUES ('Dennis');
INSERT INTO users (username) VALUES ('Love');
INSERT INTO users (username) VALUES ('Eric');
INSERT INTO users (username) VALUES ('Sam');
INSERT INTO users (username) VALUES ('Viktor');

INSERT INTO Post (title, text, userId, likes, dislikes) VALUES ('Mat', 'Hamburgare är gott', 1, 0, 0);
INSERT INTO Post (title, text, userId, likes, dislikes) VALUES ('Mat', 'Pizza är gott', 1, 0, 0);
INSERT INTO Post (title, text, userId, likes, dislikes) VALUES ('Mat', 'Pannkakor är gott', 1, 0, 0);
INSERT INTO Post (title, text, userId, likes, dislikes) VALUES ('Mat', 'Hamburgare är gott', 2, 0, 0);
INSERT INTO Post (title, text, userId, likes, dislikes) VALUES ('Mat', 'Pizza är gott', 2, 0, 0);
INSERT INTO Post (title, text, userId, likes, dislikes) VALUES ('Mat', 'Pannkakor är gott', 2, 0, 0);


INSERT INTO Developer ( email, apikey) VALUES ( 'jan.banan@gmail.com', 'a9991969-8b6a-413c-ae14-82dc423e4425');
