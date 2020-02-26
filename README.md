# Shop

[![Build Status](https://travis-ci.com/davidgfolch/shop.svg?branch=master)](https://travis-ci.com/davidgfolch/shop)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=13814eaa9b43ba5e25d2df7d0f0130c37635a8de&metric=alert_status)](https://sonarcloud.io/dashboard?id=13814eaa9b43ba5e25d2df7d0f0130c37635a8de)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=13814eaa9b43ba5e25d2df7d0f0130c37635a8de&metric=bugs)](https://sonarcloud.io/dashboard?id=13814eaa9b43ba5e25d2df7d0f0130c37635a8de)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=13814eaa9b43ba5e25d2df7d0f0130c37635a8de&metric=code_smells)](https://sonarcloud.io/dashboard?id=13814eaa9b43ba5e25d2df7d0f0130c37635a8de)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=13814eaa9b43ba5e25d2df7d0f0130c37635a8de&metric=coverage)](https://sonarcloud.io/dashboard?id=13814eaa9b43ba5e25d2df7d0f0130c37635a8de)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=13814eaa9b43ba5e25d2df7d0f0130c37635a8de&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=13814eaa9b43ba5e25d2df7d0f0130c37635a8de)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=13814eaa9b43ba5e25d2df7d0f0130c37635a8de&metric=ncloc)](https://sonarcloud.io/dashboard?id=13814eaa9b43ba5e25d2df7d0f0130c37635a8de)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=13814eaa9b43ba5e25d2df7d0f0130c37635a8de&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=13814eaa9b43ba5e25d2df7d0f0130c37635a8de)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=13814eaa9b43ba5e25d2df7d0f0130c37635a8de&metric=alert_status)](https://sonarcloud.io/dashboard?id=13814eaa9b43ba5e25d2df7d0f0130c37635a8de)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=13814eaa9b43ba5e25d2df7d0f0130c37635a8de&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=13814eaa9b43ba5e25d2df7d0f0130c37635a8de)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=13814eaa9b43ba5e25d2df7d0f0130c37635a8de&metric=security_rating)](https://sonarcloud.io/dashboard?id=13814eaa9b43ba5e25d2df7d0f0130c37635a8de)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=13814eaa9b43ba5e25d2df7d0f0130c37635a8de&metric=sqale_index)](https://sonarcloud.io/dashboard?id=13814eaa9b43ba5e25d2df7d0f0130c37635a8de)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=13814eaa9b43ba5e25d2df7d0f0130c37635a8de&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=13814eaa9b43ba5e25d2df7d0f0130c37635a8de)

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


## Travis

### Sonarqube

Generate encrypted key

    travis login --pro
    travis encrypt --pro 7abce0510365e6f776e1f6b95727245001c43113
    
copy to .travis.yml

Follow instruccions in https://sonarcloud.io