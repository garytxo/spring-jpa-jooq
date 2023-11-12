# Spring JPA and JOOQ DEMO

This sample project illustrates how to build a simple CRUD application
using [spring data jpa](https://spring.io/projects/spring-data-jpa) for the persistence layer.

The application also illustrates how to replace the persistence layer for an alternative solution built
with [JOOQ](https://www.jooq.org)
using spring [profiles](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.profiles)
feature.

## What is JOOQ

The following is to illustrate some of the JOOQ basic features :

1, How to generate JOOQ Code DSL classes from existing jpa entities classes which then can
be used to generate native queries, instead of using nest query strings.

2, How to generate JOOQ Code DSL classes from an existing database and use its DAOs to
build a simple CRUD application

## Table of contents
1. [Prerequisites and frameworks](#prerequisites)
2. [Get code](#2-clone-repository-locally-using)
3. [Start docker container](#3-docker-container)
4. [Create database and JOOQ stubs](#4-tech-blog-database-schema-and-jooq-code-generator)
5. [Install dependencies](#install-dependencies-and-run-test)
6. [Start application](#starting-the-application)
7. [Swagger endpoints](#tech-blog-applications-rest-endpoints)
8. [How this repo is organized](#how-this-repository-is-organized)




## Prerequisites
In order to install and start the application please ensure that you have the following:
- Java JDK (v17+)
- Maven (v3+)
- Kotlin (1.8.20) (via Maven plugin)
- Docker (Postgres) 

### Frameworks used 
- Spring Boot 3+
- JOOQ 3.18.X
- Open API for documenting REST endpoints and contracts
- Spock (Groovy) for testing
- REST assured for system testing
- Open API for documenting REST endpoints and contracts

## Instructions 

### 1. Checkout the code
The code can be found in GitHub repo https://github.com/garytxo/jooq-demo

### 2. Clone repository locally using
Clone repository locally using
   ```shell 
     git clone git@ggithub.com/garytxo/jooq-demo.git
   ```
### 3. Docker Container

#### Start all containers (**localstack** and **postgres**) :
   ```shell
   cd jooq-demo-docker-setup
   make start-services
   ```

#### Stop all containers:
   ```shell
   cd jooq-demo-docker-setup
   make stop-services
   ```

#### Restart all containers:
   ```shell
   cd jooq-demo-docker-setup
   make restart-services
   ```

####  Manage containers individually:
   ```shell
   cd recall2-data-transfer-docker-setup
   make start-localstack-service / stop-localstack-service / restart-localstack-service
   ```
   ```shell
   cd recall2-data-transfer-docker-setup
   make start-postgres-db-service / stop-postgres-db-service / restart-postgres-db-service
   ```

### 4. Tech blog database schema and JOOQ code generator

#### Create demo_tech_blog schema
Create the empty database using the following
   ```shell
    psql -h localhost -p 5435 -U admin -c "CREATE DATABASE demo_tech_blog";
    psql -h localhost -p 5435 -U admin -c "CREATE DATABASE demo_tech_blog_test";
   ```
If you need to DROP the database execute
   ```shell    
    
    psql -h localhost -p 5436 -U admin -c "DROP DATABASE demo_tech_blog";
    psql -h localhost -p 5436 -U admin -c "DROP DATABASE demo_tech_blog_test";
   
   ```

#### Generate infrastructure repository metadata (JOOQ codegen)

JOOQ _Repository metadata_ is useful to be _type-safe_ when working against DB and in order to be notified by compiler
when code doesn't match what is present on DB tables. This metadata is generated by `jooq` via `maven` CLI.So in
order to regenerate database models execute:

 ```shell
 bash scripts/jooq-codegen.sh
 ```

**NOTE**: This is step is only needed to be done when you write a new migration.

## Install dependencies and run test
    
```shell
    cd server
    mvn clean install -DskipTests -Dgib.d=true
   ``` 
   
## Verify code coverage

```shell 
   cd server
   mvn clean verify
``` 

## Starting the application
In order to start the tech-blog application using the JPA persistence layer you can 
run the following command in the terminal 
```shell
    cd server
    mvn clean install  -DskipTests=true
    java -jar -Dspring.profiles.active=jpa tech-blog-app/target/tech-blog-app-0.0.1-SNAPSHOT-app.jar
```
Or if you want to start the application using the jooq persistence layer then use the following 
```shell
    cd server
    mvn clean install  -DskipTests=true
    java -jar -Dspring.profiles.active=jooq tech-blog-app/target/tech-blog-app-0.0.1-SNAPSHOT-app.jar

```


## Tech Blog applications REST endpoints
Once the above apps are up and running you can access each of the following campaign application
swagger endpoints : `http://localhost:7001/api-tech-blog/swagger-ui/index.html`



## How this repository is organized
Before starting to install and start the applications it makes senses to discuss the
project submodules and explain what they are and certain rules that we need to adhere whilst working on them.

### tech-blog-data-
This module is the common (or shared) database persistence module for performing database action on the data_transfer data model.
The module is responsible for managing the database migrations (flyway) which are only execute in the tech-blog-app .
Additionally, the ``tech-blog-data`` has a dependency on ``tech-blog-data-jooq``

### tech-blog-data-jooq
This module contains JOOQ autogenerated classes that are generate from the tech blog
database data model which are used all the CRUD actions. Furthermore, it contaings some code generation strategies

### tech-blog-app
This is the  main spring boot application that will contain all the logic to handle
and  has dependencies on modules ``tech-blog-data``


### tech-blog-test
This module contain all the testing for all modules,
``tech-blog-data``,  ``tech-blog-data-jooq`` and ``tech-blog-app``

### tech-blog-sonar-report (TBC)
This is the aggregate jacoco report which we supply to  sonarqube for code coverage.





## JOOQ JPADatabase code generator 
The module

Note that the JPADatabase offers a quick win by integrating with simple JPA defined schemas very quickly. 
It has its caveats. For best results its recommended going DDL first (and generate both jOOQ code and JPA model from that), 
because it will be much easier to put your schema change management under version control, e.g. via Flyway or Liquibase.



## Managing Java version with ASDF
The following section explain how to install ASDF and manage our JDKs for local development
### Installing ASDF
First install asdf via brew
```shell
brew install asdf
```

Now add java plugin

```shell
asdf plugin-add java https://github.com/halcyon/asdf-java.git
```

Add the setting java_macos_integration_enable to yes on .asdfrc file enables this integration.
````shell
echo "java_macos_integration_enable = yes" >> ~/.asdfrc

````
To set JAVA_HOME in your shell's initialization add the following:
For zsh shell add the following to the bottom on the .zshrc

```shell
. ~/.asdf/plugins/java/set-java-home.zsh
```

### ASDF Commands
now can list all the JDKS versions

```shell
asdf list-all java
```

Install a candidate listed from the previous command like this:
```shell
asdf install java corretto-17.0.8.8.1
```

Select an installed candidate for use like this:

```shell
asdf global java corretto-17.0.8.8.1
```


## Useful links

https://www.marcobehler.com/guides/jooq


https://vladmihalcea.com/jooq-facts-from-jpa-annotations-to-jooq-table-mappings/


https://blog.astradot.com/why-we-use-jooq-instead-of-hibernate/


Checkout the multset query and record that we can use with  jooq:
https://www.youtube.com/watch?v=UuNrcBgRGXw
https://github.com/simasch/jooq-quickstart


https://www.baeldung.com/spring-boot-support-for-jooq 

https://dzone.com/articles/implementing-infinite-scroll-in-jOOQ


https://thorben-janssen.com/hibernate-jooq-a-match-made-in-heaven/
https://www.jooq.org/doc/latest/manual/sql-execution/alternative-execution-models/using-jooq-with-jpa/

https://www.jooq.org/doc/latest/manual/sql-execution/alternative-execution-models/using-jooq-with-jpa/using-jooq-with-jpa-entityresult/

https://blog.gitguardian.com/8-easy-steps-to-set-up-multiple-git-accounts/
