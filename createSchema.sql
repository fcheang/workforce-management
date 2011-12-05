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
  `providerId` INTEGER UNSIGNED NOT NULL,
  `date` DATETIME NOT NULL,
  `clinic` VARCHAR(30) NOT NULL,
  `hrs_worked` DECIMAL(10,2) UNSIGNED NOT NULL DEFAULT 0,
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
  `enteredBy` varchar(20) DEFAULT NULL,
  `dateEntered` datetime DEFAULT NULL,
  PRIMARY KEY (`providerId`, `date`, `clinic`)
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
insert into permission_type values ('Daily Clinic Report for Manager');
insert into permission_type values ('Intake Staff Report');
insert into permission_type values ('Data Staff Report');
insert into permission_type values ('Billing Staff Report');
insert into permission_type values ('UR Staff Report');
insert into permission_type values ('Administrative Staff Report');
insert into permission_type values ('Case Manager Report');

insert into permission (object, userId) values ('User Admin', 'Administrator');

update permission set object = 'Intake Staff Report' where object = 'Daily Contribution Report';
update permission set object = 'Data Staff Report' where object = 'Data Compliance Personnel Tasks';
update permission set object = 'Billing Staff Report' where object = 'Billing Department Tasks';
update permission set object = 'UR Staff Report' where object = 'UR Personnel Tasks';
update permission set object = 'Administrative Staff Report' where object = 'Project List & Daily Assignment';
update permission_type set name = 'Intake Staff Report' where name = 'Daily Contribution Report';
update permission_type set name = 'Data Staff Report' where name = 'Data Compliance Personnel Tasks';
update permission_type set name = 'Billing Staff Report' where name = 'Billing Department Tasks';
update permission_type set name = 'UR Staff Report' where name = 'UR Personnel Tasks';
update permission_type set name = 'Administrative Staff Report' where name = 'Project List & Daily Assignment';


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
  other_task1 VARCHAR(100),
  other_task2 varchar(100),
  other_task3 varchar(100),
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
  `task1` varchar(100),
  `task2` varchar(100),
  `task3` varchar(100),
  `task4` varchar(100),
  `task5` varchar(100),
  `task6` varchar(100),
  `task7` varchar(100),
  `task8` varchar(100),
  `task9` varchar(100),
  `task10` varchar(100),
  `task11` varchar(100),
  `task12` varchar(100),
  `task13` varchar(100),
  `task14` varchar(100),
  `task15` varchar(100),
  `task16` varchar(100),
  `task17` varchar(100),
  `task18` varchar(100),
  `task19` varchar(100),
  `task20` varchar(100),
  `task21` varchar(100),
  `task22` varchar(100),
  `task23` varchar(100),
  `task24` varchar(100),
  `task25` varchar(100),
  `task26` varchar(100),
  `task27` varchar(100),
  PRIMARY KEY (`userId`, `date`)
)
ENGINE = InnoDB;

CREATE TABLE `case_manager_report` (
  `userId` VARCHAR(20) NOT NULL,
  `date` DATETIME NOT NULL,
  `numConsumer` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `numVisits` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `numL2Ref` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `numL3Ref` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `numL2Seen` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `numL3Seen` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `numPCPReachedOut` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `numPCPAppts` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `numCM` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `numEpisodeOpened` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `numEpisodeClosed` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `numHPOnCaseloadDueToExpire` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `numOutsideMeeting` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `numVisitNextWeek` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `numNonCompliantChart` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (`date`)
)
ENGINE = InnoDB;

CREATE TABLE `case_manager_note` (
  `userId` VARCHAR(20) NOT NULL,
  `dateOfWeek` DATETIME NOT NULL,
  `plan` varchar(200),
  `action` varchar(200),
  `assistanceNeeded` varchar(200),
  `plansForNextWeek` varchar(200),
  `other` varchar(200),
  PRIMARY KEY (`dateOfWeek`)
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