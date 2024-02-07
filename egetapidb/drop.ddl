
    alter table if exists users_Post 
       drop constraint if exists FKny4b7thmva88g7e8oji2fe5m1;

    alter table if exists users_Post 
       drop constraint if exists FKs95kqgg4wgnctmjnrn0k2rydx;

    drop table if exists Post cascade;

    drop table if exists users cascade;

    drop table if exists users_Post cascade;
