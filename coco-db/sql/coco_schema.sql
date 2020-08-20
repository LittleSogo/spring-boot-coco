drop database if exists coco;
drop user 'coco'@'127.0.0.1';
-- 支持emoji：需要mysql数据库参数： character_set_server=utf8mb4
create database coco default character set utf8mb4 collate utf8mb4_unicode_ci;
use coco;
create user 'coco'@'127.0.0.1' identified by 'coco123456';
grant all privileges on coco.* to 'coco'@'127.0.0.1';
flush privileges;