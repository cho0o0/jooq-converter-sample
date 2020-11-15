create table person(
    id varchar(64) not null unique,
    name varchar(32) not null,
    gender enum('MALE', 'FEMALE', 'OTHER'),
    birthday timestamp null default null,
    primary key (id)
)