Directory structure:
bhr_client - flex client code
bhr_server - server code

To build the project:
1. import the bhr_client project into your Eclipse IDE
2. import the bhr_server project into your Eclipse IDE
3. from the bhr_client project run Project->Export Release Build...
4. from the bhr_server project run Poject->Build Automatically
4. run ant from the current directory to create the bhr2.war file
5. you can deploy the bhr2.war file to Tomcat

Todo:
9/10
- print report
- MHS staff report
- QA staff report
//- Daily CLinic Report
  //- remove County(other)$, CCC$, daily salary, revenue, balance, 
  //- add modifyBy
  //- add DailyClinicReportForManager
  //- use provider list
  //- support hours worked in decimal
- Daily Contribution report: pull data from scheduler 
  
9/17
//- aggregate on analytics  

9/18
- Daily clinic report
  - pull data from scheduler: hrs worked, county seen (alameda county), ccc seen, hmo seen, total scheduled, total noshow, total cancel, # news, # dropins
  - add Expense % field
//- Daily clinic report for manager
//  - Find by clinic, provider, date range
//  - add print datagrid

9/24
- DCRForMgr: allow search by any provider and clinic
 
