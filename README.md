## User management system project
 This project is intended to manage users in database.
including reading, deleting and adding them. it is based on Spring framework architecture.
 
good luck.

## Run the Application
 To run this application, you need the PostgreSQL service as a database,
a JDK to build a java application , and Configuration file to connect them.

### Step 1 -- Install PostgreSQL
#### Install on Ubuntu
 Ubuntu’s default repositories contain Postgres packages, so you can install these using the apt packaging system.
  
 Since this is your first time using apt in this session, refresh your local package index. Then, install the Postgres
 package along with a -contrib package that adds some additional utilities and functionality:
 
```
sudo apt update
sudo apt install postgresql postgresql-contrib
```
 
To download and install on other operating systems  [click here](https://www.postgresql.org/download/)
 
### Step 2 -- Using PostgreSQL Roles and Databases

To run the server:

`sudo sevice postgresql start`

There are a few ways to utilize this account to access Postgres. One way is to switch over to the postgres account on 
your server and access the Postgres prompt by typing:
> `sudo -u postgres psql`

To create a user named **_spring_user_** and to encrypt it with password 'spring_user',
you can run the following command:
> postgres=# `CREATE ROLE spring_user with encrypted password 'spring_user';`

To see all roles:
> postgres=# `\du;`

You can create the user-management-system database with the _**CREATE**_ command.
> postgres=# `CREATE DATABASE user-management-system;`
_**user-management-system**_ database will be created.

To grants the CREATE , CONNECT , and TEMPORARY privileges on the **_user-management-system_** database to 
   **_spring_user_** role (users are properly referred to as roles):
> postgres=# `GRANT ALL PRIVILEGES ON DATABASE user-management-system TO spring_user;`

To show databases, run the following commands.
> postgres=# `\l;`
>
To connect to _**user-management-system**_ Database use PostgreSQL database command:
> postgres=# `\c user-management-system;`

## Configuration file
First open the project and go to _**src/main/resources**_ directory:
 
>  `cd src/main/resources`
 
now create _**application.properties**_ file and open it:
 
```
touch application.properties
vim application.properties
```
 
now put following phrases in it:

``` 
# DataSource settings: set here your own configurations for the database connection.
# In this example we have "user-management-system" as database name and 
# "spring_user" as username and password.

spring.datasource.url=jdbc:postgresql://localhost:5432/user-management-system 


# Login username of the database.

spring.datasource.username=spring_user


# Login password of the database.

spring.datasource.password=spring_user


# DDL mode. This is actually a shortcut for the "hibernate.hbm2ddl.auto" property.
# Defaults to "create-drop" when using an embedded database and no schema manager was detected. Otherwise, defaults to "none".

spring.jpa.hibernate.ddl-auto=update


# Whether to enable logging of SQL statements.

spring.jpa.show-sql=true


# server HTTP port.

server.port=8081

```

## Java Development Kit (JDK)
* Install a JDK version 11 (at least)
* Download the dependencies 
* Run the DemoApplication as main class

## Curl endpoints
 to get all users : 
 
 `curl -X GET http://localhost:8081/users`
 
 to add a user :
 
 `curl -X POST -d 'id'='1' -d 'name'='ali' -d 'emailAddress'='hello@gmail.com' http://localhost:8081/users`
 
 To get a user by id :
 
 `curl -X GET -G http://localhost:8081/user -d 'id'='1'`
 
 To delete a user by id:
 
 `curl -X DELETE -G http://localhost:8081/users -d 'id'='1'`