Use Userstore;

DROP TABLE IF EXISTS User;

CREATE TABLE `Userstore`.`User` (
	`ID` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(16) NOT NULL,
  `surname` VARCHAR(16) NOT NULL,
  `patronymic` VARCHAR(16) NOT NULL,
  PRIMARY KEY (`ID`))
  COLLATE='utf8_general_ci';

INSERT INTO `User` (`name`,`surname`,`patronymic`)
    VALUES ("Cat", "Vasiliy", "Fatherovich"),
	("Ivan", "Grosniy", "Vasilyevich"),
	("Jorj", "Bush", "Bushovich"),
	("Stepan", "Petrenko", "Yuriovich");