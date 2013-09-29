
create table pictures (
    id integer identity,
    title varchar(255),
    author varchar(255),
    caption varchar(255),
    year_painted int,
    url varchar(255),
    primary key (id)
);

create table comments (
    id integer identity,
    picture_id integer,
    user varchar(255),
    website_url varchar(255),
    comment varchar(255),
    primary key (id)
);

insert into pictures (title, author, url, year_painted) values ('La strada entra nella casa', 'Umberto Boccioni', 'boccioni.lastrada.jpg', 1911);
insert into pictures (title, author, url, year_painted) values ('La torre rossa', 'Giorgio De Chirico', 'g.de_chirico_la_torre_rossa.jpg', 1912);
insert into pictures (title, author, url, year_painted) values ('La joie de vivre', 'Pablo Picasso', 'joie.jpg', 1946);
insert into pictures (title, author, url, year_painted) values ('Le Nord-Sud', 'Gino Severini', 'le-nord-sud.jpg', 1912);
    
    
    