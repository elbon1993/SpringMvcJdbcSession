# SpringMvcJdbcSession
Spring MVC + JDBC Template + Login page with Session

Queries: MySQL - schema: crud

CREATE TABLE `employee` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `DEPT` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `age` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
)

CREATE TABLE `olog` (
  `id` int(10) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL
)

INSERT INTO `crud`.`olog`
(`id`,
`name`,
`username`,
`password`)
VALUES
(1,
'chandrahas',
'admin',
'admin123');


