-- -----------------------------------------------------
-- Schema emp_manage
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `emp_manage`;
USE `emp_manage` ;

-- -----------------------------------------------------
-- Table `emp_manage`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `address` (
                                                    `address_id` INT(11) NOT NULL AUTO_INCREMENT,
                                                    `country` VARCHAR(45) NOT NULL,
                                                    `city` VARCHAR(45) NOT NULL,
                                                    `street` VARCHAR(45) NOT NULL,
                                                    `house_number` VARCHAR(45) NOT NULL,
                                                    `apartment_number` VARCHAR(45) NULL DEFAULT NULL,
                                                    `postal_code` VARCHAR(45) NOT NULL,
                                                    PRIMARY KEY (`address_id`),
                                                    UNIQUE INDEX `address_id_UNIQUE` (`address_id` ASC));

-- -----------------------------------------------------
-- Table `emp_manage`.`department`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS department (
                                                       `department_id` INT(11) NOT NULL AUTO_INCREMENT,
                                                       `name` VARCHAR(45) NOT NULL,
                                                       `description` VARCHAR(128) NULL DEFAULT NULL,
                                                       `phone_number` VARCHAR(45) NULL DEFAULT NULL,
                                                       `formation_date` DATE NOT NULL,
                                                       PRIMARY KEY (`department_id`),
                                                       UNIQUE INDEX `department_id_UNIQUE` (`department_id` ASC),
                                                       UNIQUE INDEX `department_name_UNIQUE` (`name` ASC));


-- -----------------------------------------------------
-- Table `emp_manage`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `employee` (
                                                     `employee_id` INT(11) NOT NULL AUTO_INCREMENT,
                                                     `name` VARCHAR(128) NOT NULL,
                                                     `birth_date` DATE NOT NULL,
                                                     `phone_number` VARCHAR(45) NULL DEFAULT NULL,
                                                     `email` VARCHAR(45) NOT NULL,
                                                     `position` VARCHAR(45) NOT NULL,
                                                     `salary` INT(11) NOT NULL,
                                                     `hire_date` DATE NOT NULL,
                                                     `passport_identification_number` VARCHAR(45) NOT NULL,
                                                     `address_id` INT(11) NOT NULL,
                                                     `department_id` INT(11) NOT NULL,
                                                     PRIMARY KEY (`employee_id`),
                                                     UNIQUE INDEX `employee_id_UNIQUE` (`employee_id` ASC),
                                                     UNIQUE INDEX `employee_email_UNIQUE` (`email` ASC),
                                                     UNIQUE INDEX `passport_identification_number_UNIQUE` (`passport_identification_number` ASC),
                                                     CONSTRAINT `fk_employee_address1`
                                                       FOREIGN KEY (`address_id`)
                                                         REFERENCES `emp_manage`.`address` (`address_id`),
                                                     CONSTRAINT `fk_employee_department1`
                                                       FOREIGN KEY (`department_id`)
                                                         REFERENCES `emp_manage`.`department` (`department_id`));


-- -----------------------------------------------------
-- Table `emp_manage`.`education`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `education` (
                                                      `education_id` INT(11) NOT NULL AUTO_INCREMENT,
                                                      `school_name` VARCHAR(128) NOT NULL,
                                                      `degree` VARCHAR(45) NULL DEFAULT NULL,
                                                      `field_of_study` VARCHAR(45) NULL DEFAULT NULL,
                                                      `grade` VARCHAR(45) NULL DEFAULT NULL,
                                                      `start_year` CHAR(4) NOT NULL,
                                                      `end_year` CHAR(4) NOT NULL,
                                                      `description` VARCHAR(128) NULL DEFAULT NULL,
                                                      `employee_id` INT(11) NOT NULL,
                                                      PRIMARY KEY (`education_id`),
                                                      UNIQUE INDEX `education_id_UNIQUE` (`education_id` ASC),
                                                      CONSTRAINT `fk_education_employee1`
                                                        FOREIGN KEY (`employee_id`)
                                                          REFERENCES `emp_manage`.`employee` (`employee_id`));

-- -----------------------------------------------------
-- Table `emp_manage`.`image`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `image` (
                                                  `image_id` INT(11) NOT NULL AUTO_INCREMENT,
                                                  `url` VARCHAR(45) NOT NULL,
                                                  PRIMARY KEY (`image_id`),
                                                  UNIQUE INDEX `image_id_UNIQUE` (`image_id` ASC),
                                                  UNIQUE INDEX `image_url_UNIQUE` (`url` ASC));


-- -----------------------------------------------------
-- Table `emp_manage`.`supervisor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `supervisor` (
                                                       `supervisor_id` INT(11) NOT NULL AUTO_INCREMENT,
                                                       `employee_id` INT(11) NOT NULL,
                                                       `department_id` INT(11) NOT NULL,
                                                       PRIMARY KEY (`supervisor_id`),
                                                       UNIQUE INDEX `supervisor_id_UNIQUE` (`supervisor_id` ASC),
                                                       UNIQUE INDEX `department_id_UNIQUE1` (`department_id` ASC),
                                                       UNIQUE INDEX `employee_id_UNIQUE1` (`employee_id` ASC),
                                                       CONSTRAINT `fk_supervisor_department1`
                                                         FOREIGN KEY (`department_id`)
                                                           REFERENCES `emp_manage`.`department` (`department_id`),
                                                       CONSTRAINT `fk_supervisor_employee1`
                                                         FOREIGN KEY (`employee_id`)
                                                           REFERENCES `emp_manage`.`employee` (`employee_id`));


-- -----------------------------------------------------
-- Table `emp_manage`.`user_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_role` (
                                                      `user_role_id` INT(11) NOT NULL AUTO_INCREMENT,
                                                      `name` VARCHAR(45) NOT NULL,
                                                      PRIMARY KEY (`user_role_id`),
                                                      UNIQUE INDEX `user_role_id_UNIQUE` (`user_role_id` ASC));


-- -----------------------------------------------------
-- Table `emp_manage`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user` (
                                                 `user_id` INT(11) NOT NULL AUTO_INCREMENT,
                                                 `name` VARCHAR(45) NOT NULL,
                                                 `email` VARCHAR(128) NOT NULL,
                                                 `password` VARCHAR(128) NOT NULL,
                                                 `user_role_id` INT(11) NOT NULL,
                                                 `employee_id` INT(11) NULL DEFAULT NULL,
                                                 `image_id` INT(11) NULL,
                                                 `enabled` TINYINT NOT NULL,
                                                 PRIMARY KEY (`user_id`),
                                                 UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC),
                                                 UNIQUE INDEX `user_email_UNIQUE` (`email` ASC),
                                                 CONSTRAINT `fk_user_employee1`
                                                   FOREIGN KEY (`employee_id`)
                                                     REFERENCES `emp_manage`.`employee` (`employee_id`),
                                                 CONSTRAINT `fk_user_user_role`
                                                   FOREIGN KEY (`user_role_id`)
                                                     REFERENCES `emp_manage`.`user_role` (`user_role_id`),
                                                 CONSTRAINT `fk_user_image1`
                                                   FOREIGN KEY (`image_id`)
                                                     REFERENCES `emp_manage`.`image` (`image_id`)
                                                     ON DELETE NO ACTION
                                                     ON UPDATE NO ACTION);
-- -----------------------------------------------------
-- Table `emp_manage`.`verification_token`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `emp_manage`.`verification_token` (
                                                               `verification_token_id` INT NOT NULL AUTO_INCREMENT,
                                                               `user_id` INT(11) NOT NULL,
                                                               `token` VARCHAR(128) NOT NULL,
                                                               PRIMARY KEY (`verification_token_id`),
                                                               UNIQUE INDEX `verification_token_id_UNIQUE` (`verification_token_id` ASC),
                                                               INDEX `fk_verification_token_user1_idx` (`user_id` ASC),
                                                               CONSTRAINT `fk_verification_token_user1`
                                                                 FOREIGN KEY (`user_id`)
                                                                   REFERENCES `emp_manage`.`user` (`user_id`)
                                                                   ON DELETE NO ACTION
                                                                   ON UPDATE NO ACTION)

