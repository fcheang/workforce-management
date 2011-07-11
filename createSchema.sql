use gold;

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
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
ENGINE = InnoDB;

CREATE TABLE `permission_type` (
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`name`)
)
ENGINE = InnoDB;

CREATE TABLE `permission` (
  `id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `object` VARCHAR(100) NOT NULL,
  `userId` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `AK_permission_1`(`object`, `userId`),
  CONSTRAINT `FK_permission_1` FOREIGN KEY `FK_permission_1` (`object`)
    REFERENCES `permission_type` (`name`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
ENGINE = InnoDB;

insert into permission_type values ('User Admin');
insert into permission_type values ('Analytics');
insert into permission_type values ('Employee');
insert into permission_type values ('Daily Clinic Report');
insert into permission_type values ('Daily Contribution Report');
insert into permission_type values ('Data Compliance Personnel Tasks');
insert into permission_type values ('Billing Department Tasks');
insert into permission_type values ('UR Personnel Tasks');
insert into permission_type values ('Project List and Daily Assignment');

insert into permission (object, userId) values ('User Admin', 'Administrator');

CREATE TABLE `contribution` (
  `userId` VARCHAR(20) NOT NULL,
  `date` DATETIME NOT NULL,
  `hrs_worked` DECIMAL(10,1) UNSIGNED NOT NULL DEFAULT 0,
  `auths_entered` VARCHAR(100),
  `interpreters_ordered` VARCHAR(100),
  `collateral_received` VARCHAR(100),
  `other` VARCHAR(200),
  PRIMARY KEY (`userId`, `date`)
)
ENGINE = InnoDB;

CREATE TABLE `contribution_item` (
  `userId` VARCHAR(20) NOT NULL,
  `date` DATETIME NOT NULL,
  `type` VARCHAR(100) NOT NULL,
  `private_pay` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `hmo` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `ac` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `ac_child` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `ccc` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `ccc_child` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `sf` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `other` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (`userId`, `date`, `type`)
)
ENGINE = InnoDB;

CREATE TABLE `data_compliance_task` (
  `userId` VARCHAR(20) NOT NULL,
  `date` DATETIME NOT NULL,
  `eps_opened` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `bill_errs_found` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `charts_reviewed` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `chart_errs_found` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `items_entered` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (`userId`, `date`)
)
ENGINE = InnoDB;

CREATE TABLE `ur_personnel_task` (
  `userId` VARCHAR(20) NOT NULL,
  `date` DATETIME NOT NULL,
  `charts_reviewed` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `discharge_done` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `bill_svc_provided` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `eps_opened` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `charts_transfered` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `ru_completed` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `md_charts_audited` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `mhs_charts_audited` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `tx_team_mtgs` INTEGER UNSIGNED NOT NULL DEFAULT 0,  
  PRIMARY KEY (`userId`, `date`)
)
ENGINE = InnoDB;

CREATE TABLE `billing_task` (
  `userId` VARCHAR(20) NOT NULL,
  `date` DATETIME NOT NULL,
  `claims_dropped_c` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `claims_dropped_p` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `errors` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `denials` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `followups` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `appeals` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `hmo_amt` DECIMAL(10,2) UNSIGNED NOT NULL DEFAULT 0,  
  `ac_amt` DECIMAL(10,2) UNSIGNED NOT NULL DEFAULT 0,  
  `ccc_amt` DECIMAL(10,2) UNSIGNED NOT NULL DEFAULT 0,  
  `private_amt` DECIMAL(10,2) UNSIGNED NOT NULL DEFAULT 0,  
  `other_amt` DECIMAL(10,2) UNSIGNED NOT NULL DEFAULT 0,    
  PRIMARY KEY (`userId`, `date`)
)
ENGINE = InnoDB;

CREATE TABLE `other_billing_task` (
  `userId` VARCHAR(20) NOT NULL,
  `date` DATETIME NOT NULL,
  `task` varchar(200) NOT NULL,
  `count` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (`userId`, `date`, `task`)
)
ENGINE = InnoDB;

CREATE TABLE `project_list` (
  `userId` VARCHAR(20) NOT NULL,
  `date` DATETIME NOT NULL,
  `task` varchar(200) NOT NULL,
  `timeslot` INTEGER UNSIGNED NOT NULL,
  `order` INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY (`userId`, `date`, `task`, `timeslot`, `order`)
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