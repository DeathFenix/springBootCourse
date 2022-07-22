create table tb_cidade(
    id_cidade bigint not null primary key,
    nome varchar(50) not null,
    qtd_habitantes bigint
);

insert into tb_cidade
    (id_cidade, nome , qtd_habitantes)
values
    (1, 'São Paulo', 12396372),
    (2, 'Rio de Janeiro', 450062355),
    (3, 'Fortaleza', 9812955),
    (4, 'salvador', 6357984),
    (5, 'Belo Horizonte', 23319874),
    (6, 'Porto Alegre', 23123165),
    (7, 'Porto Velho', 8979165),
    (8, 'Palmas', 56482556),
    (9, 'Recife', 65465),
    (10, 'Natal', 987416565),
    (11, 'Brasilia', 1000000),
    (12, 'Vitória', null),
    (13, 'Curitiba', null);