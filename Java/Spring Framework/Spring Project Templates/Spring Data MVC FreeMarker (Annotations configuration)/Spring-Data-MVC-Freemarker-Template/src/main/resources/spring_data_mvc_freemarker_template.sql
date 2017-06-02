create database spring_data_mvc_freemarker_template;

use spring_data_mvc_freemarker_template;


CREATE TABLE IF NOT EXISTS `user` (
    `id` int(10) NOT NULL AUTO_INCREMENT,
    `login` varchar(512) DEFAULT NULL,
    `password` varchar(512) DEFAULT NULL,    
    `first_name` varchar(512) DEFAULT NULL,    
    `last_name` varchar(512) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

INSERT INTO `user` (`id`, `login`, `password`, `first_name`, `last_name`) VALUES
(1, 'user1', 'pwd1', 'Ivan', 'Sidorov'),
(2, 'user2', 'pwd2', 'Semen', 'Ivanov');