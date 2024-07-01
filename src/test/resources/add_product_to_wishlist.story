Narrative:
As a user
I want to add products to my wishlist
So that I can keep track of items I'm interested in

Scenario: Successfully add a new product to an existing wishlist
Given a wishlist for customer '0' with 2 products
When I try to add a new product with ID '3' to the wishlist
Then the product should be added with ID '3'

Scenario: Attempt to add an existing product to the wishlist
Given a wishlist for customer '1' with products including '10' and size 8
When I try to add a new product with ID '5' to the wishlist
Then The product list size should be 8


