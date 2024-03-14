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


create table if not exists books (
    id             bigserial,
    author         varchar(50) not null,
    description    varchar(255) not null,
    user_id        integer not null,
    constraint books_pk primary key (id),
    constraint books_users_fk foreign key (user_id) references users (id)
);
insert into books(author, description, user_id) values ('Адитья Бхаргава', 'Иллюстрированное пособие для программистов и любопытствующих', 2);
