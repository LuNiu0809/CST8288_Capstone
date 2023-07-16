-- MySQL Workbench Forward Engineering

-- MY SQL Workbench Version : 8.0.33

-- -----------------------------------------------------
-- Database RestuarantReviews
-- -----------------------------------------------------
CREATE DATABASE IF NOT EXISTS `RestuarantReviews` DEFAULT CHARACTER SET utf8 ;
USE `RestuarantReviews` ;

-- -----------------------------------------------------
-- Create user
-- Source for create user code. 
-- https://stackoverflow.com/questions/13357760/mysql-create-user-if-not-exists
-- Source for granting user permissions 
-- https://dev.mysql.com/doc/refman/8.0/en/grant.html

-- -----------------------------------------------------
CREATE USER IF NOT EXISTS 'rr_user'@'localhost' IDENTIFIED BY 'rr_password';
GRANT INSERT, SELECT, UPDATE, DELETE ON RestuarantReviews. * TO 'rr_user'@'localhost';



-- -----------------------------------------------------
-- Table `RestuarantReviews`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RestuarantReviews`.`user` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `UserName` VARCHAR(45) NULL,
  `Password` VARCHAR(45) NULL,
  `Email` VARCHAR(45) NULL,
  `LastName` VARCHAR(45) NULL,
  `FirstName` VARCHAR(45) NULL,
  `RoleName` VARCHAR(45) NULL,
  `AccountVerified` TINYINT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RestuarantReviews`.`business`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RestuarantReviews`.`business` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NULL,
  `Address` VARCHAR(45) NULL,
  `Description` VARCHAR(250) NULL,
  `PhoneNumber` VARCHAR(45) NULL,
  `Email` VARCHAR(45) NULL,
  `OverallRating` INT NULL,
  `PriceRating` INT NULL,
  `FoodType` VARCHAR(45) NULL,
  `PhotosFileName` VARCHAR(45) NULL,
  `HoursOfOperation` VARCHAR(45) NULL,
  `User_ID` INT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_business_user1_idx` (`User_ID` ASC) VISIBLE,
  CONSTRAINT `fk_business_user1`
    FOREIGN KEY (`User_ID`)
    REFERENCES `RestuarantReviews`.`user` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RestuarantReviews`.`review`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RestuarantReviews`.`review` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `user_ID` INT NOT NULL,
  `business_ID` INT NOT NULL,
  `Date` DATETIME NULL,
  `Content` VARCHAR(250) NULL,
  `PriceRating` INT NULL,
  `OverallRating` INT NULL,
  `FoodRating` INT NULL,
  `ServiceRating` INT NULL,
  `AtmosphereRating` INT NULL,
  `PhotosFileName` VARCHAR(45) NULL,
  `UsefulCount` INT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_review_user_idx` (`user_ID` ASC) VISIBLE,
  INDEX `fk_review_business1_idx` (`business_ID` ASC) VISIBLE,
  CONSTRAINT `fk_review_user`
    FOREIGN KEY (`user_ID`)
    REFERENCES `RestuarantReviews`.`user` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_review_business1`
    FOREIGN KEY (`business_ID`)
    REFERENCES `RestuarantReviews`.`business` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `RestuarantReviews`.`user_review_useful`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RestuarantReviews`.`user_review_useful` (
  `user_ID` INT NOT NULL,
  `review_ID` INT NOT NULL,
  INDEX `fk_user_review_useful_user_idx` (`user_ID` ASC) VISIBLE,
  INDEX `fk_user_review_useful_review1_idx` (`review_ID` ASC) VISIBLE,
  CONSTRAINT `fk_user_review_useful_user`
    FOREIGN KEY (`user_ID`)
    REFERENCES `restuarantreviews`.`user` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_review_useful_review1`
    FOREIGN KEY (`review_ID`)
    REFERENCES `restuarantreviews`.`review` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Insert sample data into users table. 
-- -----------------------------------------------------
INSERT INTO user (ID, UserName, Password, Email, LastName, FirstName, RoleName, AccountVerified)
VALUES (1, 'Admin', 'admin', 'admin@gmail.com', '', '', 'Admin', 1);

INSERT INTO user (ID, UserName, Password, Email, LastName, FirstName, RoleName, AccountVerified)
VALUES (2, 'JaneDoe123', 'password', 'janeDoe123@gmail.com', 'Doe', 'Jane', 'Contributor', 1);

INSERT INTO user (ID, UserName, Password, Email, LastName, FirstName, RoleName, AccountVerified)
VALUES (3, 'JohnDoe456', 'password', 'johnDoe456@gmail.com', 'Doe', 'John', 'Contributor', 1);

INSERT INTO user (ID, UserName, Password, Email, LastName, FirstName, RoleName, AccountVerified)
VALUES (4, 'JaneDoe123', 'password', 'janeDoe123@gmail.com', 'Doe', 'Jane', 'Contributor', 1);

INSERT INTO user (ID, UserName, Password, Email, LastName, FirstName, RoleName, AccountVerified)
VALUES (5, 'JaneDoe456', 'password', 'JaneDoe456@gmail.com', 'Doe', 'Jane', 'Contributor', 1);

INSERT INTO user (ID, UserName, Password, Email, LastName, FirstName, RoleName, AccountVerified)
VALUES (6, 'user6', 'password', 'user6@gmail.com', '6', 'user', 'Contributor', 1);

INSERT INTO user (ID, UserName, Password, Email, LastName, FirstName, RoleName, AccountVerified)
VALUES (7, 'user7', 'password', 'user7@gmail.com', '7', 'user', 'Contributor', 1);



-- -----------------------------------------------------
-- Insert sample data into business table. 
-- -----------------------------------------------------
INSERT INTO business (ID, Name, Address, Description, PhoneNumber, Email, OverallRating, PriceRating, FoodType, HoursOfOperation)
VALUES(1, 'Bar and Grill', '123 Fake Street Ottawa ON', 'Bar and Grill Description', '613-123-4567', 'BarAndGrill@gmail.com', 4, 2, 'Barbecue', '11:00AM - 11:OOPM');

INSERT INTO business (ID, Name, Address, Description, PhoneNumber, Email, OverallRating, PriceRating, FoodType, HoursOfOperation)
VALUES(2, 'The Greasy Spoon', '453 Made Up Cresent, Ottawa ON', 'The Greasy Spoon Description', '613-123-8912', 'GreasySppon@gmail.com', 5, 1, 'Breakfast', '6:00AM - 6:OOPM');

INSERT INTO business (ID, Name, Address, Description, PhoneNumber, Email, OverallRating, PriceRating, FoodType, HoursOfOperation)
VALUES(3, 'Irish Pub', '789 Fake Street, Ottawa ON', 'Irish Pub Description', '613-123-3456', 'IrishPub@gmail.com', 3, 1, 'Irish Pub', '11:00AM - 1:OOAM');

INSERT INTO business (ID, Name, Address, Description, PhoneNumber, Email, OverallRating, PriceRating, FoodType, HoursOfOperation)
VALUES(4, 'cafe', '124 Le Fake Rue, Ottawa ON', 'cafe Description', '613-123-7891', 'cafe@gmail.com', 5, 4, 'French', '7:00AM - 7:OOPM');

INSERT INTO business (ID, Name, Address, Description, PhoneNumber, Email, OverallRating, PriceRating, FoodType, HoursOfOperation)
VALUES(5, 'Biergarten', '456 Beer Garden Way, Ottawa ON', 'Biergarten Description', '613-123-2198', 'Biergarten@gmail.com', 4, 2, 'German', '11:00AM - 1:OOAM');

INSERT INTO business (ID, Name, Address, Description, PhoneNumber, Email, OverallRating, PriceRating, FoodType, HoursOfOperation)
VALUES(6, 'Dive Bar', '146 Back Alley Street, Ottawa ON', 'Dive Bar Description', '613-123-7654', 'Dive Bar@gmail.com', 2, 1, 'Bar', '1:00PM - 1:OOAM');



-- -----------------------------------------------------
-- Insert sample data into review table. 
-- -----------------------------------------------------
INSERT INTO review (ID, user_ID, business_ID, Date, Content, PriceRating, OverallRating, FoodRating, ServiceRating, AtmosphereRating, UsefulCount)
VALUES(1, 2, 1, NOW(), 'Great Steak!', 2, 4, 4, 4, 4, 1);

INSERT INTO review (ID, user_ID, business_ID, Date, Content, PriceRating, OverallRating, FoodRating, ServiceRating, AtmosphereRating, UsefulCount)
VALUES(2, 3, 2, NOW(), 'Great Breakfast!', 1, 5, 5, 5, 5, 2);

INSERT INTO review (ID, user_ID, business_ID, Date, Content, PriceRating, OverallRating, FoodRating, ServiceRating, AtmosphereRating, UsefulCount)
VALUES(3, 4, 3, NOW(), 'Standard Pub grub.', 1, 3, 3, 3, 3, 1);

INSERT INTO review (ID, user_ID, business_ID, Date, Content, PriceRating, OverallRating, FoodRating, ServiceRating, AtmosphereRating, UsefulCount)
VALUES(4, 5, 4, NOW(), 'Amazing', 4, 5, 5, 5, 5, 6);

INSERT INTO review (ID, user_ID, business_ID, Date, Content, PriceRating, OverallRating, FoodRating, ServiceRating, AtmosphereRating, UsefulCount)
VALUES(5, 6, 5, NOW(), 'Great Beer!', 2, 4, 4, 4, 4, 3);

INSERT INTO review (ID, user_ID, business_ID, Date, Content, PriceRating, OverallRating, FoodRating, ServiceRating, AtmosphereRating, UsefulCount)
VALUES(6, 7, 6, NOW(), 'Standard dive bar', 1, 2, 2, 2, 2, 0);



