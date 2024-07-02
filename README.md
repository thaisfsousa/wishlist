# Wishlist

Wishlist is a service designed to manage wishlist functionalities for customers. This project handles operations such as adding products to a wishlist, retrieving products from a wishlist, checking if a product is in a wishlist, and removing products from a wishlist.
<hr>

## Setup

### Requirements
- Java 17
- Maven 3
- Docker Compose

### Building
- Clone the repository: ```git clone https://github.com/thaisfsousa/wishlist.git```
- Navigate into project directory: ```cd wishlist```
- Build project using Maven: ```mvn clean package```
<hr>

## Run
```
$docker compose up -d
```
<hr>

## Usage

#### Access:
http://localhost:8080/swagger-ui/index.html

### Endpoints

#### GET ```customers/{customerId}/products```
Retrieves all Products in Wishlist for a specified customer.
Example of a success response:
```
[
    {
        id: "1",
        name: "Product 1",
        price: 100
    },
    {
        id: "2",
        name: "Product 2",
        price: 50.1
    }
]
```

#### GET ```customers/{customerId}/products/{productId}```
Checks if a specific product is in the customer's Wishlist.
Example of a success response:
```
    true | false
```

#### POST ```customers/{customerId}/products```
Adds a product into the Wishlist of a specified customer and returns updated Wishlist.
Example of Body:
```
{
    id: "1",
    name: "Product 1",
    price: 100
}
```
Example of a success response:
```
{
    "customerId": "1",
    "products": [
        {
            "id": "1",
            "name": "Product 1",
            "price": 100
        }
    ]
}
```

#### DELETE ```customers/{customerId}/products/{productId}```
Removes a specific product from customer's Wishlist.
Example of a success response:
```
Product deleted.
```


