INSERT PRODUCT

Always post a seller first using localhost:9001/seller

All sellers POST /seller/
Seller names must be non-null & unique CRUD functionality on Product GET /product/
All products GET /product/{id}
Get a single product
We should get a 404 error when we try to access a non-existed product. POST /product/ - Add a single product
Product ids should be non-null and unique
Product names should be non-null
Price should be over 0
Seller name should refer to an actually existing seller PUT /product/{id} - Update a single product
Product names should be non-null
Price should be over 0
Seller name should refer to an actually existing seller DELETE /product/{id} - Delete a single product
DELETE should always return 200, regardless of if the item existed at the start or not. This is convention.
Unit testing of service classes



