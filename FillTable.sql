Use Userstore;

DROP TABLE IF EXISTS User;

CREATE TABLE `Userstore`.`User` (
    `name` VARCHAR(16) NOT NULL,
  `surname` VARCHAR(16) NOT NULL,
  `patronymic` VARCHAR(16) NOT NULL,
  PRIMARY KEY (`name`))
  COLLATE='utf8_general_ci';

INSERT INTO `User` (`name`,`surname`,`patronymic`)
    VALUES ("Cat", "Vasiliy", "Fatherovich"),
	("Ivan", "Grosniy", "Vasilyevich"),
	("Jorj", "Bush", "Bushovich"),
	("Stepan", "Petrenko", "Yuriovich");