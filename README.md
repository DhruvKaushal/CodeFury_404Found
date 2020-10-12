# CodeFury Project: Batch DA3, Team 1
## Team Name: 404Found

Our project application, the **AMP**, or the **Asset Management Portal** is an e-asset management application which keeps track of assets. The system can have multiple assets, which the employees can borrow. The admin keeps track of these transactions, and in case of any late returning, adding new items, etc. In addition to these, there is also a functionality of **sending a custom message to the user!** 



## Relevant queries for setting up database

#### Employee master table
emp_name varchar(30),emp_contact bigint unique,username varchar(30) unique,emp_email varchar(25) unique,emp_pwd varchar(50),pwd_salt varchar(15),signin_date timestamp,signup_date timestamp);

#### Admin master table
create table admin_master_record(admin_id int primary key,admin_name varchar(30),admin_contact bigint unique,username varchar(30) unique,admin_email varchar(25) unique,admin_pwd varchar(50),pwd_salt varchar(15),signin_date timestamp,signup_date timestamp);

#### Asset master table
create table asset_master (asset_type varchar(25) primary key, lending_period smallint, fine float , ban_period smallint); 

#### Asset record table
create table asset_record(asset_id int primary key generated always as identity(start with 1000, increment by 1), asset_name varchar(50), asset_type varchar(25) references asset_master(asset_type), asset_info long varchar, quantity int);

#### Transaction master table
create table transaction_master(trans_id int primary key generated always as identity(start with 100000,increment by 1),emp_id int references emp_master_record(emp_id),asset_id int references asset_record(asset_id),issued_date timestamp, default_return_date timestamp,actual_return_date timestamp, is_return boolean, current_fine float);

#### Table ban status
create table ban_status (emp_id int references emp_master_record(emp_id) on delete cascade, is_ban boolean, ban_date timestamp);

#### Message record
Create table message_record(message_id int primary key generated always as identity(start with 1 , increment by 1), message_content long varchar,trans_id int references transaction_master(trans_id), is_read boolean default false );



## Relevant dependencies

#### Apache Tomcat server 8.5
#### derbyclient.jar
#### jstl.jar
#### standard.jar
#### commons-fileupload-1.4.jar
#### commons-io-2.8.0.jar
#### json-simple-1.1.1.jar

## A guide to the basic workflow of the project
The project begins at *index.jsp*, which acts as the home page of the application. On this page, the user gets an option to register, login or import users(admin functionality) using a json file. Upon successful registration, the user gets assigned the role of employee(the admin role has been restricted to DB registration only as of now). At login, the user can choose his/her role, between Employee and Admin. Upon successfull login, the user gets redirected to the expected landing pages, from where he/she can access a plethora of functionalities depending upon his/her role.
