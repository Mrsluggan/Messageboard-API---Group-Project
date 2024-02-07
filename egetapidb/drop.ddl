
    alter table if exists Post 
       drop constraint if exists FK58gj8nsg0ksi7s2y8jjtguap7;

    drop table if exists Post cascade;

    drop table if exists users cascade;
