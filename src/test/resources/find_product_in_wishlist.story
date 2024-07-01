Scenario: Product exists in the wishlist
Given a customer with ID '0' has a wishlist with size 1
And the wishlist contains a product with ID '0'
When the customer searches for product ID '0'
Then the result should be true

Scenario: Product does not exist in the wishlist
Given a customer with ID '0' has a wishlist with size 1
And the wishlist does not contain a product with ID '1'
When the customer searches for product ID '1'
Then the result should be false

Scenario: Wishlist not found for customer
Given a wishlist doesnt exists for customer ID '0'
When the customer searches for product ID '1'
Then a WishlistNotFound exception should be thrown
