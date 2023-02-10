insert into zanbanzero.user(nickname, password, roles, username) values ('user', '$2a$10$rb7fhQHdtxNf7RgTJVlERePH99.BqB6/i5LE61leYMh0vGOQoGbIC', 'ROLE_USER', 'user');

insert into zanbanzero.manager(nickname, password, roles, username) values ('manager', '$2a$10$rb7fhQHdtxNf7RgTJVlERePH99.BqB6/i5LE61leYMh0vGOQoGbIC', 'ROLE_MANAGER', 'manager');
insert into zanbanzero.manager(nickname, password, roles, username) values ('manager2', '$2a$10$rb7fhQHdtxNf7RgTJVlERePH99.BqB6/i5LE61leYMh0vGOQoGbIC', 'ROLE_MANAGER', 'manager2');

insert into zanbanzero.store(location, name, id) values (1, 'test store 1', 1);
insert into zanbanzero.store(location, name, id) values (2, 'test store 2', 2);

insert into zanbanzero.menu(cost, info, name, store_id) values (2000, '두유, 복숭아', '치킨너겟', 1);
insert into zanbanzero.menu(cost, info, name, store_id) values (45000, '감자', '배고픈 무지', 2);
insert into zanbanzero.menu(cost, info, name, store_id) values (12345, '삼겹살 알레르기', '육회',1);