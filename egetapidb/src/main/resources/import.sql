-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

INSERT INTO users (username, apikey) VALUES ('Dennis', '4b3029f1-98d5-4cf4-9d05-9b3a8a857d2b');
INSERT INTO users (username, apikey) VALUES ('Love', '8d2d68f2-5b51-4a9f-8f64-320a8bc47b1c');
INSERT INTO users (username, apikey) VALUES ('Eric', 'ac5a1fc4-3f39-44f8-bb6d-18491d9a4b7e');
INSERT INTO users (username, apikey) VALUES ('Sam', 'ac5a1fc4-3f39-44f8-bb6d-18491d9a4b7c');
INSERT INTO users (username, apikey) VALUES ('Viktor', 'ac5a1fc4-3f39-44f8-bb6d-18491d9a4b7f');

INSERT INTO Post ( title, text, userId) VALUES ( 'Mat', 'Hamburgare är gott', 1);
INSERT INTO Post ( title, text, userId) VALUES ( 'Mat', 'Pizza är gott', 1);
INSERT INTO Post ( title, text, userId) VALUES ( 'Mat', 'Pannkakor är gott', 1);
INSERT INTO Post ( title, text, userId) VALUES ( 'Mat', 'Hamburgare är gott', 2);
INSERT INTO Post ( title, text, userId) VALUES ( 'Mat', 'Pizza är gott', 2);
INSERT INTO Post ( title, text, userId) VALUES ( 'Mat', 'Pannkakor är gott', 2);