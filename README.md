# Product-fetcher-Service
This is a simple Spring Boot application that provides REST APIs for managing and getting shopper-products and product metadata. 
The application uses JPA for database interactions and supports auto-generated entities. 

## Features
* Manage shopper products with auto-generated IDs.
* Manage product metadata.
* Retrieve shopper products based on various filter criteria.
* Use Jakarta Persistence (JPA) for database operations.

## Configuration
* Simply replace the below properties with your own database URL and credentials in the application.properties file.
* By default the configuration uses postgresSQL as the DB
```
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=localuser
spring.datasource.password=12345
```
* Replace the application port if you need the app to start in a custom port.
```
server.port=8081
```
## Endpoints
### Internal Endpoints
### Create/ Update Product API
```
POST [api/internal/product]
Request Parameters: N/A
Remarks: creates a product and updates meta-data if an existing product is available
Sample Request Body: 
{
        "productId": "MB-20931937838",
        "brand": "hitachi",
        "category": "electronics"
}
Sample Response:
Persisted new product with product ID: MB-20931937838
```
```
POST [/api/internal/shopper-products]
Remarks: Persists shopper-products to the database for an existing product.
Request Parameters: N/A
Sample Request Body: 
{
    "shopperId": "S-1000",
    "shelf": [
        {
            "product": {
                "productId": "MB-20931937838"
            },
            "relevancyScore": 31.948
        },
        {
            "product": {
                "productId": "MB-20931937839"
            },
            "relevancyScore": 32.948
        }
    ]
}
Sample Response:
200 OK
```
### External Endpoints
```
GET [/api/external/shopper-products?shopperId=S-1000&category=electronics&brand=hitachi&limit=5]
Remarks: Retrieves shopper-products from the database for a shopperId with filters with product meta-data.
Request Parameters: shopperId (Mandatory), brand, category, limit                   
Sample Request Body: N/A
Sample Response:
[
    {
        "id": 1,
        "product": {
            "productId": "MB-20931937838",
            "category": "electronics",
            "brand": "hitachi"
        },
        "relevancyScore": 31.948
    }
]
```
### Usage 
Once the application is running, you can interact with it using tools like curl, Postman, or your preferred HTTP client.
