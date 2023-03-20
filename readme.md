### How to use this spring-boot project

- Install packages with `mvn package`
- Run `mvn spring-boot:run` for starting the application (or use your IDE)

Application (with the embedded H2 database) is ready to be used ! You can access the url below for testing it :

- Swagger UI : http://localhost:8080/swagger-ui.html
- H2 UI : http://localhost:8080/h2-console

> Don't forget to set the `JDBC URL` value as `jdbc:h2:mem:testdb` for H2 UI.



### Instructions

- download the zip file of this project
- create a repository in your own github named 'java-challenge'
- clone your repository in a folder on your machine
- extract the zip file in this folder
- commit and push

- Enhance the code in any ways you can see, you are free! Some possibilities:
  - Add tests
  - Change syntax
  - Protect controller end points
  - Add caching logic for database calls
  - Improve doc and comments
  - Fix any bug you might find
- Edit readme.md and add any comments. It can be about what you did, what you would have done if you had more time, etc.
- Send us the link of your repository.

#### Restrictions
- use java 8


#### What we will look for
- Readability of your code
- Documentation
- Comments in your code 
- Appropriate usage of spring boot
- Appropriate usage of packages
- Is the application running as expected
- No performance issues

#### Your experience in Java

-My Java experience

- I have about 13 years experience in Java and JavaScript, html, jsp etc and I started to use Spring 3 years ago.
- Recently, I had been supporting of introduction of CI pipeline in Azure DevOps and SonarQube for analysis the code 
- against Java-web system based on ant-build at Daiichi Life project.
- I have also experience below's languages and database etc..
- struts,VB6,VB.Net,Excel-VBA,C#,ASP.NET,oracle,sql-server,web Sphere(4,5,6),tomcat,GitLab,JIRA,confluence-page,Jenkins.


#### My additional comments
- I have added the logic for caching of h2 database and junit-testing for CRUD(create, read, update, delete) of database.
- If I had more time, I would like to add junit-testing for REST-API.
