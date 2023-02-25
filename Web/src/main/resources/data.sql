insert into zanbanzero.user(username, password, roles) values ('user', '$2a$10$rb7fhQHdtxNf7RgTJVlERePH99.BqB6/i5LE61leYMh0vGOQoGbIC', 'ROLE_USER');
insert into zanbanzero.user(username, password, roles) values ('user1', '$2a$10$rb7fhQHdtxNf7RgTJVlERePH99.BqB6/i5LE61leYMh0vGOQoGbIC', 'ROLE_USER');

insert into zanbanzero.manager(password, roles, username) values ('$2a$10$rb7fhQHdtxNf7RgTJVlERePH99.BqB6/i5LE61leYMh0vGOQoGbIC', 'ROLE_MANAGER', 'manager');
insert into zanbanzero.manager(password, roles, username) values ('$2a$10$rb7fhQHdtxNf7RgTJVlERePH99.BqB6/i5LE61leYMh0vGOQoGbIC', 'ROLE_MANAGER', 'manager2');

insert into zanbanzero.menu(cost, info, name) values (2000, '두유, 복숭아', '치킨너겟');
insert into zanbanzero.menu(cost, info, name) values (45000, '감자', '배고픈 무지');
insert into zanbanzero.menu(cost, info, name) values (12345, '삼겹살 알레르기', '육회');