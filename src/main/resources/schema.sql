create table if not exists users (
                                     id   bigserial,
                                     name varchar(50) not null,
                                     age  integer not null,
                                     constraint users_pk primary key (id)
);
insert into users(name, age) values ('Mike', 18),
                                    ('Nik', 27),
                                    ('Tim', 21);


create table if not exists books (
                                     id             bigserial,
                                     author         varchar(50) not null,
                                     title          varchar(255) not null,
                                     user_id        bigint not null,
                                     constraint books_pk primary key (id),
                                     constraint books_users_fk foreign key (user_id) references users (id) on delete cascade
);
insert into books(author, title, user_id) values ('Адитья Бхаргава', 'Грокаем алгоритмы', 1),
                                                 ('Роберт Мартин', 'Чистый код', 1),
                                                 ('Брюс Эккель', 'Философия Java', 2),
                                                 ('Гирберт Шилдт', 'Java 8 Полное руководство', 2),
                                                 ('Роберт Лигуори', 'Java 8 Карманный справочник', 3),
                                                 ('Джошуа Блох', 'Java эффективное программирование', 3);
