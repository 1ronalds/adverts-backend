use advertdb;

DROP TABLE IF EXISTS advert;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS application;

CREATE TABLE user ( UserID bigint(20) NOT NULL AUTO_INCREMENT,
username varchar(45) NOT NULL UNIQUE,
email varchar(50) NOT NULL UNIQUE,
password varchar(20) NOT NULL,
phone varchar(12) NOT NULL,
is_admin boolean NOT NULL,
PRIMARY KEY (UserID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE advert (
AdvertID bigint(20) NOT NULL AUTO_INCREMENT,
title varchar(50) NOT NULL,
description varchar(500) NOT NULL,
price DECIMAL(4,2) NOT NULL,
img_location varchar(100) NOT NULL,
user_id bigint(20) NOT NULL,
PRIMARY KEY (AdvertID),
FOREIGN KEY (user_id) REFERENCES user(UserID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE application (
advert_id bigint(20) NOT NULL,
user_id bigint(20) NOT NULL,
PRIMARY KEY (advert_id, user_id),
FOREIGN KEY (advert_id) REFERENCES advert(AdvertID),
FOREIGN KEY (user_id) REFERENCES user(UserID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;