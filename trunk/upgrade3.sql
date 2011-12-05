insert into permission_type values ('Case Manager Report');

CREATE TABLE `CaseManagerReport` (
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

CREATE TABLE `CaseManagerNote` (
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