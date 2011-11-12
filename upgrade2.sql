insert into permission_type values ('Accounting Report');

create table account_name (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,  
  name varchar(45) not null,
  primary key (id),
  constraint UK_account_name_1 unique (name)
) 
engine = InnoDB;

insert into account_name (id, name) values (1, 'BHR');
insert into account_name (id, name) values (2, 'HHI Oak/UC');
insert into account_name (id, name) values (3, 'HHI PL');
insert into account_name (id, name) values (4, 'HHI CCC');
insert into account_name (id, name) values (5, 'AA');
insert into account_name (id, name) values (6, 'Waraich');

CREATE TABLE `accounting_report` (
  `dateOfWeek` DATETIME NOT NULL,
  `accountId` INTEGER UNSIGNED NOT NULL,
  `numBills` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `amtPaid` DECIMAL(10,2) UNSIGNED NOT NULL DEFAULT 0,  
  `numChecks` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `amtChecks` DECIMAL(10,2) UNSIGNED NOT NULL DEFAULT 0,  
  `amtCashCollected` DECIMAL(10,2) UNSIGNED NOT NULL DEFAULT 0,  
  `numCheckDeposited` INTEGER UNSIGNED NOT NULL DEFAULT 0,  
  `amtDeposited` DECIMAL(10,2) UNSIGNED NOT NULL DEFAULT 0,  
  PRIMARY KEY (`dateOfWeek`, `accountId`),
  CONSTRAINT `FK_accounting_report_1` FOREIGN KEY `FK_accounting_report_1` (`accountId`)
    REFERENCES `account_name` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
)
ENGINE = InnoDB;
