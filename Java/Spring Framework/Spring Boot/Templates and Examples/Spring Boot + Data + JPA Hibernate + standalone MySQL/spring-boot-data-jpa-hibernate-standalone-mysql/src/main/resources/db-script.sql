CREATE DATABASE spring_boot_mysql;

USE spring_boot_mysql;

CREATE TABLE IF NOT EXISTS `role` (
    `id` int(10) NOT NULL AUTO_INCREMENT,
    `role_name` varchar(50) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS `user` (
    `id` int(10) NOT NULL AUTO_INCREMENT,
    `login` varchar(512) NOT NULL,
    `password` varchar(64) NOT NULL,
    `role_id` int(10) NULL,
    `first_name` varchar(512) NULL,
    `last_name` varchar(512) NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

ALTER TABLE `user` ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

INSERT INTO `role` (`id`, `role_name`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

INSERT INTO `user` (`id`, `login`, `password`, `role_id`, `first_name`, `last_name`) VALUES
(1, 'user1', 'pwd1', 2, 'FirstName1', 'LastName1'),
(2, 'user2', 'pwd2', 2, 'FirstName2', 'LastName2'),
(3, 'user3', 'pwd3', 2, 'FirstName3', 'LastName3'),
(4, 'admin1', 'pwd1', 1, 'FirstName4', 'LastName4'),
(5, 'admin2', 'pwd2', 1, 'FirstName5', 'LastName5');