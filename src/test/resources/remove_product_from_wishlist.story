Scenario: Successfully remove a product from the wishlist
Given a customer with ID '0' has a wishlist containing product '0'
When customer '0' tries to remove the product with ID '0' from the wishlist
Then the product '0' should be removed from the wishlist

Scenario: Product does not exist in the wishlist
Given a customer with ID '1' has a wishlist not containing product '1'
When customer '1' tries to remove the product with ID '1' from the wishlist
Then a ProductNotFound exception should be thrown

Scenario: Wishlist does not exist for the customer
Given no wishlist exists for customer ID '2'
When customer '2' tries to remove the product with ID '2' from the wishlist
Then a exception NotFound should be thrown