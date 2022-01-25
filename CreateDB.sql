-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema hostel_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema hostel_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `hostel_db` DEFAULT CHARACTER SET utf8 ;
USE `hostel_db` ;

-- -----------------------------------------------------
-- Table `hostel_db`.`user_roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hostel_db`.`user_roles` (
  `role_id` INT NOT NULL AUTO_INCREMENT,
  `role_name` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE INDEX `role_name_UNIQUE` (`role_name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hostel_db`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hostel_db`.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(20) NOT NULL,
  `password` CHAR(60) NOT NULL,
  `role_id` INT NOT NULL,
  INDEX `fk_users_user_roles1_idx` (`role_id` ASC) VISIBLE,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
  CONSTRAINT `fk_users_user_roles1`
    FOREIGN KEY (`role_id`)
    REFERENCES `hostel_db`.`user_roles` (`role_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hostel_db`.`all_clients`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hostel_db`.`all_clients` (
  `client_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NULL,
  `last_name` VARCHAR(30) NOT NULL,
  `first_name` VARCHAR(30) NOT NULL,
  `passport_id` VARCHAR(30) NOT NULL,
  `date_of_birth` DATE NOT NULL,
  `country` VARCHAR(20) NULL,
  `phone_number` VARCHAR(20) NOT NULL,
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`client_id`),
  INDEX `fk_all_clients_users1_idx` (`user_id` ASC) VISIBLE,
  UNIQUE INDEX `passport_id_UNIQUE` (`passport_id` ASC) VISIBLE,
  CONSTRAINT `fk_all_clients_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `hostel_db`.`users` (`user_id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hostel_db`.`rooms`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hostel_db`.`rooms` (
  `room_number` INT NOT NULL,
  `cost` INT NOT NULL,
  `capacity` INT NOT NULL,
  `gender` VARCHAR(2) NULL,
  `bathroom` TINYINT NOT NULL,
  `notes` VARCHAR(250) NULL,
  PRIMARY KEY (`room_number`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hostel_db`.`blacklist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hostel_db`.`blacklist` (
  `client_id` INT NOT NULL,
  `reason` VARCHAR(250) NOT NULL,
  `since_date` DATE NOT NULL,
  PRIMARY KEY (`client_id`),
  CONSTRAINT `fk_blacklist_all_clients1`
    FOREIGN KEY (`client_id`)
    REFERENCES `hostel_db`.`all_clients` (`client_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hostel_db`.`regular_customers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hostel_db`.`regular_customers` (
  `client_id` INT NOT NULL,
  `since_date` DATE NOT NULL,
  `discount` INT NOT NULL,
  `notes` VARCHAR(250) NULL,
  PRIMARY KEY (`client_id`),
  CONSTRAINT `fk_regular_customers_all_clients1`
    FOREIGN KEY (`client_id`)
    REFERENCES `hostel_db`.`all_clients` (`client_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hostel_db`.`booking`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hostel_db`.`booking` (
  `booking_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `room_number` INT NOT NULL,
  `from_date` DATE NOT NULL,
  `to_date` DATE NOT NULL,
  `guests_count` INT NOT NULL,
  `approved` TINYINT NULL,
  `approve_date` DATE NULL,
  `paid` TINYINT NULL,
  PRIMARY KEY (`booking_id`),
  INDEX `fk_booking_rooms1_idx` (`room_number` ASC) VISIBLE,
  INDEX `fk_booking_users1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_booking_rooms1`
    FOREIGN KEY (`room_number`)
    REFERENCES `hostel_db`.`rooms` (`room_number`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_booking_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `hostel_db`.`users` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hostel_db`.`all_stays`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hostel_db`.`all_stays` (
  `stay_id` INT NOT NULL AUTO_INCREMENT,
  `client_id` INT NOT NULL,
  `room_number` INT NOT NULL,
  `from_date` DATE NOT NULL,
  `to_date` DATE NOT NULL,
  `notes` VARCHAR(250) NULL,
  PRIMARY KEY (`stay_id`),
  INDEX `fk_current_guests_rooms1_idx` (`room_number` ASC) VISIBLE,
  INDEX `fk_all_stays_all_clients1_idx` (`client_id` ASC) VISIBLE,
  CONSTRAINT `fk_current_guests_rooms1`
    FOREIGN KEY (`room_number`)
    REFERENCES `hostel_db`.`rooms` (`room_number`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_all_stays_all_clients1`
    FOREIGN KEY (`client_id`)
    REFERENCES `hostel_db`.`all_clients` (`client_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hostel_db`.`images`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hostel_db`.`images` (
  `img_id` INT NOT NULL AUTO_INCREMENT,
  `img_path` VARCHAR(150) NOT NULL,
  `room_number` INT NOT NULL,
  PRIMARY KEY (`img_id`),
  INDEX `fk_pictures_rooms1_idx` (`room_number` ASC) VISIBLE,
  CONSTRAINT `fk_pictures_rooms1`
    FOREIGN KEY (`room_number`)
    REFERENCES `hostel_db`.`rooms` (`room_number`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hostel_db`.`admins`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hostel_db`.`admins` (
  `user_id` INT NOT NULL,
  `name` VARCHAR(20) NOT NULL,
  `photo` VARCHAR(150) NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `fk_admins_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `hostel_db`.`users` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
