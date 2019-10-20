# selective-process-zup-innovation
CRUD software for selection process at ZUP Innovation, for a position of technical leader

## Requirements
* Java 8
*  Docker
*  Docker-Compose

* Gradle 4+ (The recommended way to execute the build is using the Gradle Wrapper: ./gradlew ...)
* Docker Compose (For local environment testing)

## Installation Linux:

Update System:
    $ sudo apt update
    $ sudo apt upgrade

Install Java 8
    $ sudo add-apt-repository ppa:webupd8team/java
    $ sudo apt-get install oracle-java8-installer
    
Install Packages Prerequisites:    
    $ sudo apt-get install  curl apt-transport-https ca-certificates software-properties-common
    
Add Docker Repositories:
    $ curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -

Add the repository: (64 bits)
    $ sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"

Install Docker   
    $ sudo apt install docker-ce
    
Install Docker-Compose
    https://docs.docker.com/compose/install/    

## Local environment dependencies
The project uses docker compose to run the required dependencies(database, services, etc).

## Collections Postman consume API

https://www.getpostman.com/collections/d6898f8d4f4f96ce29ba


##Starting the containers PostgreSQL and Aplication:
```sh
    ./buildRun.sh
```

## Building Project

    ./gradlew clean build

## Unit Test

    ./gradlew test

## Integration Test

    ./gradlew integrationTest  

## Run Application

    ./gradlew bootRun   

## Contributing   
    