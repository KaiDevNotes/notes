create database db_sample_for_jdbc;
use db_sample_for_jdbc;


CREATE TABLE IF NOT EXISTS `continents` (
    `id` int(10) NOT NULL AUTO_INCREMENT,
    `name` varchar(512) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS `locations` (
    `id` int(10) NOT NULL AUTO_INCREMENT,
    `name` varchar(512) DEFAULT NULL,
    `continent_id` int(10) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `continent_id` (`continent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS `servers` (
    `id` int(10) NOT NULL AUTO_INCREMENT,
    `name` varchar(512) DEFAULT NULL,
    `location_id` int(10) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `location_id` (`location_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;


ALTER TABLE `locations`
  ADD CONSTRAINT `locations_ibfk_1` FOREIGN KEY (`continent_id`) REFERENCES `continents` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `servers`
  ADD CONSTRAINT `servers_ibfk_1` FOREIGN KEY (`location_id`) REFERENCES `locations` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;


INSERT INTO `continents` (`id`, `name`) VALUES
(1, 'America'),
(2, 'Europe'),
(3, 'Australia');

INSERT INTO `locations` (`id`, `name`, `continent_id`) VALUES
(1, 'USA, New York', 1),
(2, 'Canada, Toronto', 1),
(3, 'Genmany, Berlin', 2),
(4, 'France, Paris', 2),
(5, 'Australia, Sidney', 3),
(6, 'Australia, Canberra', 3);

INSERT INTO `servers` (`id`, `name`, `location_id`) VALUES
(1, 'Srv 1.1', 1),
(2, 'Srv 1.2', 1),
(3, 'Srv 1.3', 1),
(4, 'Srv 2.1', 2),
(5, 'Srv 2.2', 2),
(6, 'Srv 2.3', 2),
(7, 'Srv 3.1', 3),
(8, 'Srv 3.2', 3),
(9, 'Srv 3.3', 3), 
(10, 'Srv 4.1', 4),
(11, 'Srv 4.2', 4),
(12, 'Srv 4.3', 4),
(13, 'Srv 5.1', 5),
(14, 'Srv 5.2', 5),
(15, 'Srv 5.3', 5),
(16, 'Srv 6.1', 6),
(17, 'Srv 6.2', 6),
(18, 'Srv 6.3', 6); 
  
  

