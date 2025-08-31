CREATE EXTENSION IF NOT EXISTS "pgcrypto";

DROP TABLE IF EXISTS public.users;

CREATE TABLE public.users
(
    user_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR,
    last_name VARCHAR,
    birth_date DATE,
    address VARCHAR,
    phone_number VARCHAR,
    email VARCHAR,
    salary INTEGER
);