[![Build Status][travis-badge]][travis-badge-url]

![](./img/x.png)

SpringFox (Swagger) UI From JSON File Example
=============================================
This is an example on generating [SpringFox/Swagger](http://springfox.github.io/springfox/) 
UI from API definition stored in a static JSON file.

### SpringFox Dependency
Only Springfox dependency required in this example is the `springfox-swagger-ui`
JAR.

```xml
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>${swagger.version}</version>
</dependency>
```

### Swagger JSON File
A `swagger.json` file containing the JSON definition is located under
`resources/config` folder. The location of the file can be changed by 
modifying `swagger.json.location` property in `application.yml` file.

### Swagger Controller
A custom controller the following endpoints to meet all the requests from 
`swagger-ui.html`:

1. `/swagger-resources` endpoint which returns a JSON string representing a 
`springfox.documentation.swagger.web.SwaggerResource` object.

1. `/swagger-resources/configuration/ui` endpoint which returns a JSON string 
representing a `springfox.documentation.swagger.web.UiConfiguration` object.

1. `/swagger-resources/configuration/security` endpoint which returns a JSON string 
   representing a `springfox.documentation.swagger.web.SecurityConfiguration` object.
   
1. `/v2/api-docs` endpoint which returns a JSON representing Swagger API
definition.   

### Build
To build the JAR, execute the following command from the parent directory:

```
mvn clean install
```

### Run
To run the application fromm command line,

```
java -jar target/springfox-ui-from-json-example-1.0.0.jar
```

### Access Swagger Endpoints

##### Swagger UI
You can view the Swagger UI at `http://localhost:8080/swagger-ui.html`.


##### Swagger JSON
You can view Swagger JSON doc at `http://localhost:8080/v2/api-docs?group=book`


[travis-badge]: https://travis-ci.org/indrabasak/springfox-ui-from-json-example.svg?branch=master
[travis-badge-url]: https://travis-ci.org/indrabasak/springfox-ui-from-json-example/