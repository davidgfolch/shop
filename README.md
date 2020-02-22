# Shop

Hexagonal architecture approach.

Gradle multi module Spring-boot project.

Sonar plugin enabled.

Swagger2 configuration enabled.

## Testing

JUnit / mockito

## Lombok

Enable annotation processing in IDE

Intellij install lombok plugin.

## Run

Run the Spring Boot Application in restApi module.
You can try it with Swagger-ui accesing http://localhost:8080/swagger-ui.html

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
    - Decoupling restApi, service, port and adapter from spring boot dependency management and communicate them via async or sync protocols.
    - Alternatively creating separate services with the full-stack rest+service+persistence, but it doesn't has many sense in this small project.
