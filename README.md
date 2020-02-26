# Shop

[![Build Status](https://travis-ci.com/davidgfolch/shop.svg?branch=master)](https://travis-ci.com/davidgfolch/shop)

Hexagonal architecture approach.

Gradle multi module Spring-boot project.

Sonar plugin enabled.

Swagger2 configuration enabled.

H2 in memory persistence.

## Testing

JUnit / mockito

## Lombok

Enable annotation processing in IDE

Intellij install lombok plugin.

## Run

Run the Spring Boot Application in restApi module.

You can try it with Swagger-ui accessing http://localhost:8080/swagger-ui.html

## Swagger
Create product:

    {
      "name": "Product1",
      "price": 10
    }

Create order:

    {
      "customerEmail": "d@gmail.com",
      "products": [
        1
      ]
    }
    
## Considerations
- You do not need to add authentication to your web service, but propose a protocol / method and
justify your choice.
    - Use of HTTPS.
    - Password hash.
    - Basic auth or OAuth.
- How can you make the service redundant? What considerations should you do?
    - Decoupling restApi, service, port and adapter from spring boot dependency management and communicate them via async or sync protocols/frameworks, for example, this [spring-cloud+kubernetes](https://dzone.com/articles/quick-guide-to-microservices-with-kubernetes-sprin) solution.
