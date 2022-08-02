CREATE TABLE IF NOT EXISTS users(
id SERIAL primary key not null,
name varchar,
address varchar,
birth_date varchar,
balance bigint,
created_at timestamp,
updated_at timestamp);