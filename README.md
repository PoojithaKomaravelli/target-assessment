# my-retail-rest-api
This is an API which will aggregrate the product data from multiple sources and return it to the caller.

#Pre-requsites
If you donot have JDK software installed or if JAVA_HOME is not set ,install it and and set the environment variables accordingly.
Download and Install any IDE like Intellij/Eclipse to make the application run easily.
Download the project from GITHUB using the link "https://github.com/PoojithaKomaravelli/target-assessment.git"
Open the project using installed IDE 

#Runnig the application
Build the project with Maven using Intellij by clicking on the "Build" Lifecycle,
Run "MyRetailRestApiApplication" class which will trigger Spring Boot and starts the application on the default  port '8080'

#Swaggger 
The swagger documentation is available once the application is running on the port 8080 at http://localhost:8080/swagger-ui.html
This API documentation helps to understand the exposed services with expected requests and responses.

#Good URL examples
A single Product in JSON format
    GET : http://localhost:8080/api/products/13860428
Update Pricing details for existing Product
    PUT : http://localhost:8080/api/products/13860428
          RequestBody : {
                            "id": 13860428,
                            "name": "The Big Lebowski (Blu-ray)",
                            "current_price": {
                                "currency_code": "USD"
                                "value": 134
                            }
                        }

