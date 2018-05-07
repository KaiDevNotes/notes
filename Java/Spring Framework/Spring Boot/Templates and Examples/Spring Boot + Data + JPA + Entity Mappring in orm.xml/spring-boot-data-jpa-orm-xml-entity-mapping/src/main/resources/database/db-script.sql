

-- DDL


create database orm_xml_mapping_sample;
use orm_xml_mapping_sample;


CREATE TABLE IF NOT EXISTS `location` 
(
	`id` varchar(512) NOT NULL,
	`country` varchar(512) NOT NULL,
	`city` varchar(512) NOT NULL,
	`iso2code` varchar(512) NOT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `location_unique` UNIQUE (`id`)
) 
ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;


CREATE TABLE IF NOT EXISTS `server` (
	`id` varchar(512) NOT NULL,
	`name` varchar(512) NOT NULL,
	`ip` varchar(512) DEFAULT NULL,
	`port` varchar(512) DEFAULT NULL,
	`location_id` varchar(512) DEFAULT NULL,
	PRIMARY KEY (`id`),
	KEY `location_id` (`location_id`),
	CONSTRAINT `server_unique` UNIQUE (`id`)
) 
ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;


CREATE TABLE IF NOT EXISTS `vpn_channel` (
	`id` varchar(512) NOT NULL,
	`name` varchar(512) DEFAULT NULL,
	`login` varchar(512) DEFAULT NULL,
	`password` varchar(512) DEFAULT NULL,
	`server_id` varchar(512) DEFAULT NULL,
	PRIMARY KEY (`id`),
	KEY `server_id` (`server_id`),
	CONSTRAINT `vpn_channel_unique` UNIQUE (`id`)
) 
ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;



ALTER TABLE `server`
	ADD CONSTRAINT `server_ibfk_1` FOREIGN KEY (`location_id`) REFERENCES `location` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
	
	
ALTER TABLE `vpn_channel`
	ADD CONSTRAINT `vpn_channel_ibfk_1` FOREIGN KEY (`server_id`) REFERENCES `server` (`id`) ON DELETE SET NULL;
	


-- DML	


INSERT INTO `location` (`id`, `country`, `city`, `iso2code`) VALUES
('1192fc129ff34c079a1b8763721d140fl', 'USA', 'New York', 'US'),
('2192fc129ff34c079a1b8763721d140fl', 'USA', 'San Francisco', 'US'),
('3192fc129ff34c079a1b8763721d140fl', 'USA', 'Miami', 'US');

	
INSERT INTO `server` (`id`, `name`, `ip`, `port`, `location_id`) VALUES
('1192fc129ff34c079a1b8763721d140fs', 'server1.gramvpn.com', '255.255.255.1', '5555', '1192fc129ff34c079a1b8763721d140fl'),
('2192fc129ff34c079a1b8763721d140fs', 'server2.gramvpn.com', '255.255.255.2', '5555', '2192fc129ff34c079a1b8763721d140fl'),
('3192fc129ff34c079a1b8763721d140fs', 'server3.gramvpn.com', '255.255.255.3', '5555', '3192fc129ff34c079a1b8763721d140fl');


INSERT INTO `vpn_channel` (`id`, `name`, `login`, `password`, `server_id`) VALUES
('1192fc129ff34c079a1b8763721d140fs', 'channel111', 'login1', 'password1', '1192fc129ff34c079a1b8763721d140fs'),
('2192fc129ff34c079a1b8763721d140fs', 'channel222', 'login2', 'password2', '2192fc129ff34c079a1b8763721d140fs'),
('3192fc129ff34c079a1b8763721d140fs', 'channel333', 'login3', 'password3', '3192fc129ff34c079a1b8763721d140fs');