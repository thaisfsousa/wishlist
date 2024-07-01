Scenario: Wishlist exists for the customer
Given a customer with ID '0' has a wishlist size 5
And the wishlist contains products
When the customer with ID '0' retrieves all products in the wishlist
Then the list of products should be returned
