CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START 1;
create table if not exists client
(
  id  bigint not null constraint client_pkey primary key,
  name  varchar(255) UNIQUE,
  dateofbirth  varchar(255),
  cpf  varchar(255),
  address  varchar(255)
);
