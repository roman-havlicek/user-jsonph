## User info
This application fetches information about requested user. This information contains basic user information and his existing posts.

### Structure
Application is divided into 2 modules:

 - **user-connector**: contains main logic (fetching and consolidating data). It is built as a library (*.jar*) and can be used by other project as a dependency.
 - **user-micro**: deployable application (*.war*) exposing REST endpoint for fetching user data

### Properties
*user-connector* defines multiple properties. These properties has default values (see below) and does not have to be set by application using *user-connector*:
 - **connector.jsonph.host**: host name of the resource for fetching user data
*default value*: *http://jsonplaceholder.typicode.com*
- **connector.jsonph.user-resource**: web-context of the user resource
*default value*: */users/{userId}*
- **connector.jsonph.post-resource**: web-context of the user's posts resource
*default value*: */posts?userId={userId}*
- **connector.async-executor.corePoolSize**: thread pool size
*default value*: *5*
- **connector.async-executor.maxPoolSize**: maximum pool size
*default value*: *20*
- **connector.async-executor.queueCapacity**: size of the thread queue
*default value*: *50*

connector.async-executor.corePoolSize: 5
connector.async-executor.maxPoolSize: 20
connector.async-executor.queueCapacity: 40

### Build
Application can be build with maven:
***mvn clean package***

This produces 2 artifacts:
 - */user-connector/target/user-micro.jar* - library for fetching user data
 - */user-micro/target/user-micro-1.0.0.war* - deployable *.war* artifact

### How to run
Application (*user-micro*) can be run by using following command:
**java -jar user-micro/target/user-micro-1.0.0.war**

### How to use
 - ***library (user-connector)***
To fetch user data using library (*user-connector*), **UserService** can be used.
This service exposes method to fetch consolidated data about user (including his posts). For more details, see *cz.havlicek.user.service.UserService#getUserInfo(Long)*.
- ***REST resource (user-micro)***
*user-micro* exposes user resource with following URI:
**localhost:8080/users/{userId}**