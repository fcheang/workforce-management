update permission_type set name = 'Intake Staff Report' where name = 'Daily Contribution Report';
update permission_type set name = 'Data Staff Report' where name = 'Data Compliance Personnel Tasks';
update permission_type set name = 'Billing Staff Report' where name = 'Billing Department Tasks';
update permission_type set name = 'UR Staff Report' where name = 'UR Personnel Tasks';
update permission_type set name = 'Administrative Staff Report' where name = 'Project List & Daily Assignment';

drop table Worksheet;

CREATE TABLE `Worksheet` (
  `providerId` INTEGER UNSIGNED NOT NULL,
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
  `modifyBy` varchar(20) DEFAULT NULL,
  `modificationDate` datetime DEFAULT NULL,  
  PRIMARY KEY (`providerId`, `date`, `clinic`)
)
ENGINE = InnoDB;
