# Event Streaming Project

## Description
* My task, should I choose to accept it, is to create a program that harvest real time usage information about the users of video streaming events' server. 
  I will have to consume three streaming endpoints by running the video streaming events' server on my machine as described

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.3/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.0.3/maven-plugin/reference/html/#build-image)
* [Spring Data MongoDB](https://docs.spring.io/spring-boot/docs/3.0.3/reference/htmlsingle/#data.nosql.mongodb)
* [MongoDB](https://www.mongodb.com/docs/manual/reference/)
* [Docker](https://docs.docker.com/reference)
* [Kotlin](https://kotlinlang.org/docs/home.html)
* [Swagger](https://swagger.io/docs)
* [JUnit](https://junit.org/junit5/docs/current/user-guide/)
* [MockK](https://mockk.io)
* [Mockito](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html)
* [Test Containers](https://www.testcontainers.org)

### Usage Reasons
#### Mongodb
* MongoDB has become one of the most wanted databases in the world because it makes it easy for developers to store,
  manage, and retrieve data when creating applications with most programming languages. MongoDB also makes it easy for developers
  to store structured or unstructured data. The most significant thing to choose mongodb is data integrity because nowadays the data
  is the most important thing and you can't lose the data if you get an error when you're adding to database

#### Kotlin
* Fever bugs
    * If you code in Kotlin, the chances of making bugs are extremely less compared to other complex programming languages.
      The reason is very simple and straight forward. Because it is easy to learn and only involves less code to accomplish the same thing,
      the number of bugs will be also less. So, if you are a Kotlin programmer, the time you spend on fixing bugs will be extremely low compared
      to other languages. Definitely a catch for experienced developers who frequently spend hours figuring out the source of a bug.
* Productivity Improvement
    * Because it is easy to learn and maintain, there is a huge productivity boost compared to Java and other programming languages
* Null-aware type system allows complete control over this aspect of JVM programming and prevents a lot of typical annoying mistakes.
* Inline arguments make String generation so much less painful, because you don’t need to use either arcane formatting strings nor awkward concatenation.
* With default method argument, you don't need to create a bunch of constructors in every other case

#### MockK
* Easy to understand method coverages and returns
* Final result can be returned by specifying complete chain of calls. No need to mock each and every method output.
* Suppose offer is of different types and its return type is generic then `hint` will help to give a hint to mockk
  which version we actually are looking for
* A very exciting thing about Mockk is, objects can be mocked.

### Values
* Mongo DB is running on 27017 port.
* Event Stream application is running on [8080](http://localhost:8181) port

### Run
* We get the timezone info list from external api. If you want to run the timezones please check [TimezoneController](src/main/java/com/event/stream/controller/TimezoneController.java)
* If you don't have mongodb on running your local machine, you just need to run this command:
  ```bash 
    docker compose up -d
  ```

### Run the Tests
* If you run the docker compose yaml file it will automatically run the application tests.
  You can see the results on your terminal
### Docker
* In this project we have 1 docker file and 1 docker compose file.
* Docker file includes maven download, maven build command and java jar file build
    * You also have an option for maven manual build.
* [Docker compose file](docker-compose.yml) includes 2 different images
* The first image is Mongo DB. Mongo DB image needs 2 different files.
    * [.env](.env) file includes mongo db version, container name and spring boot ports.
    * [inventory.js](inventory.js) file includes database user creation
* The second image is Inventory Application image created by the [DockerFile](Dockerfile)
    * Application image depends on Mongo DB instance. If the application gets an error, it will restart the application by itself
    * You can add another environment variable to environment tab.
      You can use like an [application.properties](src/main/resources/application.properties) element


### Swagger
* After startup, you can access the Swagger documentation site with [this](http://localhost:8080/swagger-ui.html) URL


### Next Steps
* Currently I can't get the data live. You should hit the endpoint and you should wait for 20 seconds. Next step would be
  this one. I would get the data second by second and show it on the response
* I would add some asynchronous methods (i.e: I would be able to save events on background.)
* I can change the return type of the event response from flux to mono
* I would create a scheduled job for listening event or I would use an event sourcing tool for these events (i.e: AxonServer)
* Another one is creating a property management class. I would manage the url's, log levels etc. easily
* One of my next step would be adding more endpoints to control whole application. After endpoint enhanced,
  I would create nice UI/UX designed frontend side. It would be separated project and I would use React.js (```v1.1.0```)
* After creation process of frontend, I would create Authorization and Authentication part. we would be able to
  log in via Jwt bearer token as a result we would create data safety.(```v1.2.0```)
* Kubernetes, AWS integration and pipeline would be great for deployment. We can have automated deployment(```v2.0.0```)

### Known Bugs
* Sometimes the data json object comes wrong so that's why I skipped this part and just added logs 
* Timezone errors... For example, Russia has different timezone but I don't know which timezone should I use

