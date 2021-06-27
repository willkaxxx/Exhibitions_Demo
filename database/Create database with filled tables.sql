-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema exhibitions_db_servlet
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `exhibitions_db_servlet` ;

-- -----------------------------------------------------
-- Schema exhibitions_db_servlet
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `exhibitions_db_servlet` DEFAULT CHARACTER SET utf8 ;
USE `exhibitions_db_servlet` ;

-- -----------------------------------------------------
-- Table `exhibitions_db_servlet`.`exhibitions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `exhibitions_db_servlet`.`exhibitions` ;

CREATE TABLE IF NOT EXISTS `exhibitions_db_servlet`.`exhibitions` (
  `exhibition_id` INT NOT NULL AUTO_INCREMENT,
  `beginning` DATETIME(6) NOT NULL,
  `canceled` BIT(1) NOT NULL DEFAULT 0,
  `cost` DECIMAL(19,2) NOT NULL,
  `end` DATETIME(6) NOT NULL,
  `exhibition_name` VARCHAR(255) NOT NULL,
  `subject` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`exhibition_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `exhibitions_db_servlet`.`halls`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `exhibitions_db_servlet`.`halls` ;

CREATE TABLE IF NOT EXISTS `exhibitions_db_servlet`.`halls` (
  `hall_id` INT NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`hall_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `exhibitions_db_servlet`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `exhibitions_db_servlet`.`users` ;

CREATE TABLE IF NOT EXISTS `exhibitions_db_servlet`.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NOT NULL,
  `expired` BIT(1) NULL DEFAULT 0,
  `password` VARCHAR(255) NOT NULL,
  `role` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `exhibitions_db_servlet`.`exhibitions_halls`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `exhibitions_db_servlet`.`exhibitions_halls` ;

CREATE TABLE IF NOT EXISTS `exhibitions_db_servlet`.`exhibitions_halls` (
  `halls_id` INT NOT NULL,
  `exhibitions_id` INT NOT NULL,
  PRIMARY KEY (`halls_id`, `exhibitions_id`),
  INDEX `fk_halls_has_exhibitions_exhibitions1_idx` (`exhibitions_id` ASC) VISIBLE,
  INDEX `fk_halls_has_exhibitions_halls_idx` (`halls_id` ASC) VISIBLE,
  CONSTRAINT `fk_halls_has_exhibitions_halls`
    FOREIGN KEY (`halls_id`)
    REFERENCES `exhibitions_db_servlet`.`halls` (`hall_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_halls_has_exhibitions_exhibitions1`
    FOREIGN KEY (`exhibitions_id`)
    REFERENCES `exhibitions_db_servlet`.`exhibitions` (`exhibition_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `exhibitions_db_servlet`.`exhibitions_users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `exhibitions_db_servlet`.`exhibitions_users` ;

CREATE TABLE IF NOT EXISTS `exhibitions_db_servlet`.`exhibitions_users` (
  `exhibitions_id` INT NOT NULL,
  `users_id` INT NOT NULL,
  PRIMARY KEY (`exhibitions_id`, `users_id`),
  INDEX `fk_exhibitions_has_users_users1_idx` (`users_id` ASC) VISIBLE,
  INDEX `fk_exhibitions_has_users_exhibitions1_idx` (`exhibitions_id` ASC) VISIBLE,
  CONSTRAINT `fk_exhibitions_has_users_exhibitions1`
    FOREIGN KEY (`exhibitions_id`)
    REFERENCES `exhibitions_db_servlet`.`exhibitions` (`exhibition_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_exhibitions_has_users_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `exhibitions_db_servlet`.`users` (`user_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `exhibitions_db_servlet`.`exhibitions`
-- -----------------------------------------------------
START TRANSACTION;
USE `exhibitions_db_servlet`;
INSERT INTO `exhibitions_db_servlet`.`exhibitions` (`exhibition_id`, `beginning`, `canceled`, `cost`, `end`, `exhibition_name`, `subject`) VALUES (1, '2021-06-08 21:00:00.000000', 0, 10, '2021-06-15 21:00:00.000000', 'Окуни', 'Рыбы');
INSERT INTO `exhibitions_db_servlet`.`exhibitions` (`exhibition_id`, `beginning`, `canceled`, `cost`, `end`, `exhibition_name`, `subject`) VALUES (2, '2021-06-09 21:00:00.000000', 0, 20, '2021-06-16 21:00:00.000000', 'Animals', 'Animals');
INSERT INTO `exhibitions_db_servlet`.`exhibitions` (`exhibition_id`, `beginning`, `canceled`, `cost`, `end`, `exhibition_name`, `subject`) VALUES (3, '2021-06-10 21:00:00.000000', 0, 30, '2021-06-17 21:00:00.000000', 'Art', 'Art Show');
INSERT INTO `exhibitions_db_servlet`.`exhibitions` (`exhibition_id`, `beginning`, `canceled`, `cost`, `end`, `exhibition_name`, `subject`) VALUES (4, '2021-06-11 21:00:00.000000', 0, 40, '2021-06-18 21:00:00.000000', 'Flowers', 'Flora');
INSERT INTO `exhibitions_db_servlet`.`exhibitions` (`exhibition_id`, `beginning`, `canceled`, `cost`, `end`, `exhibition_name`, `subject`) VALUES (5, '2021-06-12 21:00:00.000000', 0, 50, '2021-06-19 21:00:00.000000', 'Іван Мазепа', 'Історія');
INSERT INTO `exhibitions_db_servlet`.`exhibitions` (`exhibition_id`, `beginning`, `canceled`, `cost`, `end`, `exhibition_name`, `subject`) VALUES (6, '2021-06-13 21:00:00.000000', 0, 60, '2021-06-20 21:00:00.000000', 'Міжнародна виставка', 'Виставка');

COMMIT;


-- -----------------------------------------------------
-- Data for table `exhibitions_db_servlet`.`halls`
-- -----------------------------------------------------
START TRANSACTION;
USE `exhibitions_db_servlet`;
INSERT INTO `exhibitions_db_servlet`.`halls` (`hall_id`, `address`) VALUES (1, 'Kyiv');
INSERT INTO `exhibitions_db_servlet`.`halls` (`hall_id`, `address`) VALUES (2, 'Третій павільйон');
INSERT INTO `exhibitions_db_servlet`.`halls` (`hall_id`, `address`) VALUES (3, 'Ottava');

COMMIT;


-- -----------------------------------------------------
-- Data for table `exhibitions_db_servlet`.`users`
-- -----------------------------------------------------
START TRANSACTION;
USE `exhibitions_db_servlet`;
INSERT INTO `exhibitions_db_servlet`.`users` (`user_id`, `email`, `expired`, `password`, `role`) VALUES (1, 'admin@admin', 0, '$2a$12$yBrQmExmY66KSU4TBhRnleUrTXYnXDEuNDETM.4qNNm65rYQ2vtLG', 2);
INSERT INTO `exhibitions_db_servlet`.`users` (`user_id`, `email`, `expired`, `password`, `role`) VALUES (2, 'user@user', 0, '$2a$12$yBrQmExmY66KSU4TBhRnleUrTXYnXDEuNDETM.4qNNm65rYQ2vtLG', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `exhibitions_db_servlet`.`exhibitions_halls`
-- -----------------------------------------------------
START TRANSACTION;
USE `exhibitions_db_servlet`;
INSERT INTO `exhibitions_db_servlet`.`exhibitions_halls` (`halls_id`, `exhibitions_id`) VALUES (1, 1);
INSERT INTO `exhibitions_db_servlet`.`exhibitions_halls` (`halls_id`, `exhibitions_id`) VALUES (2, 1);
INSERT INTO `exhibitions_db_servlet`.`exhibitions_halls` (`halls_id`, `exhibitions_id`) VALUES (2, 2);
INSERT INTO `exhibitions_db_servlet`.`exhibitions_halls` (`halls_id`, `exhibitions_id`) VALUES (2, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `exhibitions_db_servlet`.`exhibitions_users`
-- -----------------------------------------------------
START TRANSACTION;
USE `exhibitions_db_servlet`;
INSERT INTO `exhibitions_db_servlet`.`exhibitions_users` (`exhibitions_id`, `users_id`) VALUES (1, 1);
INSERT INTO `exhibitions_db_servlet`.`exhibitions_users` (`exhibitions_id`, `users_id`) VALUES (2, 1);
INSERT INTO `exhibitions_db_servlet`.`exhibitions_users` (`exhibitions_id`, `users_id`) VALUES (1, 2);

COMMIT;

