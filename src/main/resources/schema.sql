create table if not exists users (
    id   bigserial,
    name varchar(50) not null,
    age  integer not null,
    constraint users_pk primary key (id)
);
insert into users(name, age) values ('Mike', 18),
                                    ('Serg', 23),
                                    ('Nik', 27),
                                    ('Tim', 21);

create table if not exists condition (
    id integer not null,
    status varchar(10) not null,
    constraint condition_pk primary key (id)
);
insert into condition(id, status) values (1, 'NEW'),
                                         (2, 'GOOD'),
                                         (3, 'OLD');

create table if not exists information (
    id           bigserial,
    publisher    varchar(50) not null,
    year         integer not null,
    pages        integer not null,
    condition_id integer not null,
    constraint information_pk primary key (id),
    constraint information_condition_fk foreign key (condition_id) references condition (id)
);
insert into information(publisher, year, pages, condition_id) values ('ПИТЕР', 2017, 288, 1);

create table if not exists books (
    id             bigserial,
    author         varchar(50) not null,
    description    varchar(255) not null,
    information_id integer not null,
    constraint books_pk primary key (id),
    constraint books_information_fk foreign key (information_id) references information (id)
);
insert into books(author, description, information_id) values ('Адитья Бхаргава', 'Иллюстрированное пособие для программистов и любопытствующих', 1);
