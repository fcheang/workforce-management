insert into permission_type values ('Case Manager Report for Manager');

ALTER TABLE casemanagerreport DROP PRIMARY KEY,
ADD PRIMARY KEY  USING BTREE(`userId`, `date`);

ALTER TABLE casemanagernote DROP PRIMARY KEY,
ADD PRIMARY KEY  USING BTREE(`userId`, `dateOfWeek`);