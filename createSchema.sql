#CREATE TABLE  `user` (
#  `userId` varchar(20) NOT NULL DEFAULT '',
#  `password` varchar(100) DEFAULT NULL,
#  `firstName` varchar(30) DEFAULT NULL,
#  `lastName` varchar(30) DEFAULT NULL,
#  `description` varchar(50) DEFAULT NULL,
#  `roleName` varchar(30) DEFAULT 'Receptionist',
#  PRIMARY KEY (`userId`)
#) ENGINE=InnoDB;


CREATE TABLE `Employee` (
  `empId` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45),  
  `firstName` VARCHAR(45) NOT NULL,
  `lastName` VARCHAR(45) NOT NULL,
  `middleName` VARCHAR(45),
  `isActive` BOOLEAN NOT NULL DEFAULT 1,
  PRIMARY KEY (`empId`),
  UNIQUE INDEX `AK_Employee_1`(`firstName`, `lastName`)
)
ENGINE = InnoDB;

CREATE TABLE `Worksheet` (
  `empId` INTEGER UNSIGNED NOT NULL,
  `date` DATETIME NOT NULL,
  `clinic` VARCHAR(30) NOT NULL,
  `hrs_worked` DECIMAL(10,0) UNSIGNED NOT NULL DEFAULT 0,
  `county_seen` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `ccc_seen` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `hmo_seen` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `other_seen` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `county_face_min` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `county_other_min` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `ccc_face_min` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `ccc_other_min` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `hmo_face_min` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `other_face_min` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `num_scheduled` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `num_noshow` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `num_cancel` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `num_new` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `num_dropin` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `daily_salary` DECIMAL(10,2) UNSIGNED NOT NULL DEFAULT 0,  
  PRIMARY KEY (`empId`, `date`, `clinic`),
  CONSTRAINT `FK_Worksheet_1` FOREIGN KEY `FK_Worksheet_1` (`empId`)
    REFERENCES `employee` (`empId`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
)
ENGINE = InnoDB;

CREATE TABLE `property` (
  `name` VARCHAR(100) not null,
  `value` VARCHAR(100) not null,
  primary key (`name`)
)
ENGINE = InnoDB;

CREATE TABLE `userCapability` (
  `userid` VARCHAR(20) NOT NULL,
  `object` VARCHAR(100) NOT NULL,
  `permission` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`userid`, `object`)
)
ENGINE = InnoDB;


#CREATE TABLE  `version` (
#  `schemaVersion` int(11) DEFAULT NULL,
#  `lastUpdated` date DEFAULT NULL,
#  `description` varchar(100) DEFAULT NULL
#) ENGINE = InnoDB;

# Administrator/Administrator
#insert into user (userId, password, roleName) values ('Administrator', 'vlzdCQ519B0epX0bvCUUZHV3hlYhesLAerlXbPJPGIU=', 'Administrator');

#delete from version where schemaVersion = 1;

#insert into version (schemaVersion, lastUpdated, description) values (1, '2011-4-1', 'Daily Checkoff sheet');

commit;