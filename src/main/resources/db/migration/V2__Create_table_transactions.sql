CREATE TABLE IF NOT EXISTS transactions(
id SERIAL primary key not null,
user_id int,
transaction_date varchar,
transaction_amount bigint,
created_at timestamp,
updated_at timestamp);