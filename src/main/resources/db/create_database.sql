DROP DATABASE IF EXISTS auctions;
DROP USER IF EXISTS auctions_user;

USE mysql;
CREATE DATABASE auctions DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
CREATE USER 'auctions_user'@'%' IDENTIFIED BY 'pass';
GRANT ALL ON auctions.* TO 'auctions_user'@'%';
FLUSH PRIVILEGES;
