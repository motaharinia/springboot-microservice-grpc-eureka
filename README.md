## Spring Boot Microservice with gRPC and Eureka
this project is a demo for all technologies i have in the repository, containing:
1. springboot-actuator
2. springboot-aop
3. springboot-async
4. springboot-caching
5. springboot-datajpaoracle
6. springboot-eureka
7. springboot-grpcstarter
8. springboot-indexinghibernatesearch
9. springboot-profileproperties
10. springboot-scheduling

### Project Stack:
- Language: Java 13
- Framework: Spring Boot 2.3.0
- Authentication: Spring Security + OAuth2
- Data: Spring Data + JPA + Hibernate-ORM + Oracle Database
- Caching: Spring Data + Redis
- Indexing: Spring Data + Hibernate Search
- Microservice: gRpc + ProtoBuffer3 + Eureka Server
- Front-end: ReactJs 16.8

### Projects Domain:
1. msutility: this project is a shared common utility project for microservice projects as a pom dependency to reduce boilerplate code in multiple projects.
1. msjpautility: this project is a shared jpa utility project(msutility is used inside msjpautility) for microservice projects as a pom dependency to reduce boilerplate code in multiple projects.
2. msdiscoveryserver: a eureka discovery server project
3. msgeo: a business microservice which contains geo domain like city
4. mslogin: a business microservice which contains authentication and user and etc.


### Project Descriptions:
steps:
1. java requirements:
    - install java 13 and add belo windows system environment variable and add "%JAVA_HOME%\bin" to PATH variable:
        - JAVA_HOME = C:\Program Files\Java\jdk-13.0.2
        - Add to path : %JAVA_HOME%\bin
2. oracle requirements:
    - download oracle 18 express from : https://drive.google.com/file/d/1sdMKUH9eXfYFyRRYMcYG2-rDc2Nav5Yz/view
    - install oracle and in the setup wizard set password:"123456" for "sys" and "system" users
    - in windows environment add these variables:
        - ORACLE_HOME = C:\app\MyUser\product\18.0.0\dbhomeXE
        - ORACLE_SID = XE
    - make default profile and "system" user expiration date to unlimited (default is 180 days). open cmd as administrator and run: sqlplus /nolog
        - connect / as SYSDBA;
        - ALTER PROFILE DEFAULT LIMIT PASSWORD_LIFE_TIME UNLIMITED;
        - ALTER USER system IDENTIFIED BY 123456 ACCOUNT UNLOCK;
    - execute "C:\app\MyUser\product\18.0.0\dbhomeXE\bin\sqlplus.exe" and enter with "system" and "123456" and run these commands to create msgeo and mslogin schema with 123456 password:
        - ALTER SESSION SET CONTAINER = XEPDB1;
        - CREATE BIGFILE TABLESPACE tbsmot_perm_01 DATAFILE 'tbsmot_perm_01.dat' SIZE 20M AUTOEXTEND ON;
        - CREATE TEMPORARY TABLESPACE tbsmot_temp_01 TEMPFILE 'tbsmot_temp_01.dbf' SIZE 20M AUTOEXTEND ON;
        - CREATE USER msgeo IDENTIFIED BY 123456 DEFAULT TABLESPACE tbsmot_perm_01 TEMPORARY TABLESPACE tbsmot_temp_01 QUOTA 20M on tbsmot_perm_01;
        - GRANT create session TO msgeo;
        - GRANT create table TO msgeo;
        - GRANT create view TO msgeo;
        - GRANT create any trigger TO msgeo;
        - GRANT create any procedure TO msgeo;
        - GRANT create sequence TO msgeo;
        - GRANT create synonym TO msgeo;
        - GRANT connect TO msgeo;
        - alter user msgeo default role all;
        - CREATE USER mslogin IDENTIFIED BY 123456 DEFAULT TABLESPACE tbsmot_perm_01 TEMPORARY TABLESPACE tbsmot_temp_01 QUOTA 20M on tbsmot_perm_01;
        - GRANT create session TO mslogin;
        - GRANT create table TO mslogin;
        - GRANT create view TO mslogin;
        - GRANT create any trigger TO mslogin;
        - GRANT create any procedure TO mslogin;
        - GRANT create sequence TO mslogin;
        - GRANT create synonym TO mslogin;
        - GRANT connect TO mslogin;
        - alter user mslogin default role all;
3. msutility build:
    - if your windows account is for example "myuser" and you have "C:\Users\MyUser\.m2\repository\com\motaharinia\MsUtility" folder, remove it.
    - open msutility project by IntellliJ IDEA(enable auto import , Windows defender automatic fix) and check in "project structure" that jdk 11 is selected.
    - in IntellliJ IDEA open right side maven panel and select lifecycle/install and run maven to build it in "C:\Users\MyUser\.m2\repository\com\motaharinia\MsUtility"
4. msjpautility build:
    - if your windows account is for example "myuser" and you have "C:\Users\MyUser\.m2\repository\com\motaharinia\MsJpaUtility" folder, remove it.
    - open msjpautility project by IntellliJ IDEA(enable auto import , Windows defender automatic fix) and check in "project structure" that jdk 11 is selected.
    - in IntellliJ IDEA open right side maven panel and select lifecycle/install and run maven to build it in "C:\Users\MyUser\.m2\repository\com\motaharinia\MsJpaUtility"    
5. msdiscoveryserver start:
    - open msdiscoveryserver project by IntellliJ IDEA (enable auto import , Windows defender automatic fix) and check in "project structure" that jdk 11 is selected.
    - run msdiscoveryserver project and open "http://localhost:8761" to monitor eureka clients that register themselves later on eureka server.
6. msgeo and mslogin start:
    - follow msgeo and mslogin steps like "springboot-caching" and "springboot-indexinghibernatesearch" steps in their repository readme project description, that you can run these two projects.
    - open msgeo project by IntellliJ IDEA (enable auto import , Windows defender automatic fix) and check in "project structure" that jdk 11 is selected.
    - in IntelijIDEA for build stub files from proto file: view menu>tool windows>maven>start
	- run msgeo project with "dev" active profile (IntelijIDEA: Run -> Edit Configuration -> Spring Boot -> XXXApplication -> Environment -> VM Options: -Dspring.profiles.active=dev)
    - open mslogin project by IntellliJ IDEA (enable auto import , Windows defender automatic fix) and check in "project structure" that jdk 11 is selected.
    - if table "OAUTH_CLIENT_DETAILS" is empty or in table "ADMIN_USER" there is not any user with username "eng.motahari@gmail.com" and bcrypted password in mslogin database, before go to next step, only one time rename file from "resources/data-oracle222.sql" to "resources/data-oracle.sql", and after next step(running mslogin project), one row will be inserted to "OAUTH_CLIENT_DETAILS" and "ADMIN_USER" tables in mslogin database.
    - run mslogin project with "dev" active profile (IntelijIDEA: Run -> Edit Configuration -> Spring Boot -> XXXApplication -> Environment -> VM Options: -Dspring.profiles.active=dev)
    - do not forget to rename sql file again to "resources/data-oracle222.sql". this file only used for the first time that "OAUTH_CLIENT_DETAILS" and "ADMIN_USER" tables in mslogin database are empty.
7. client side changes and apps like "reactjs-graphqlapollo" is inside the "client" folder:
yarn install
yarn add fine-uploader
yarn add react-fine-uploader
yarn add fine-uploader-wrappers
yarn add js-file-downloader
yarn start
    
### IntellliJ IDEA Configurations:
- IntelijIDEA: Help -> Edit Custom Vm Options -> add these two line:
    - -Dfile.encoding=UTF-8
    - -Dconsole.encoding=UTF-8
- IntelijIDEA: File -> Settings -> Editor -> File Encodings-> Project Encoding: form "System default" to UTF-8. May be it affected somehow.
- IntelijIDEA: File -> Settings -> Editor -> General -> Code Completion -> check "show the documentation popup in 500 ms"
- IntelijIDEA: File -> Settings -> Editor -> General -> Auto Import -> check "Optimize imports on the fly (for current project)"
- IntelijIDEA: File -> Settings -> Editor -> Color Scheme -> Color Scheme Font -> Scheme: Default -> uncheck "Show only monospaced fonts" and set font to "Tahoma"
- IntelijIDEA: Run -> Edit Configuration -> Spring Boot -> XXXApplication -> Configuration -> Environment -> VM Options: -Dspring.profiles.active=dev
- IntelijIDEA: Run -> Edit Configuration -> Spring Boot -> XXXApplication -> Code Coverage -> Fix the package in include box

<hr/>
<a href="mailto:eng.motahari@gmail.com?"><img src="https://img.shields.io/badge/gmail-%23DD0031.svg?&style=for-the-badge&logo=gmail&logoColor=white"/></a>











