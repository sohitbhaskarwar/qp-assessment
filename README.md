# qp-assessment

For Grocery Mngt Service
  - Entity consists of Product which 
      - name
      - price 
      - inventory
  - User types are USER and ADMIN case senitive. Pass user type(User-Type)  with of them for any apiin request header else it will give 403 forbidden erro.
  

1. To add a product to our grocery application
   - Here I have given access to ADMIN (User-Type) only. So, Admin can only add a product to a grocery app.
```
curl --location 'http://localhost:8280/api/v1/gbs/add' \
--header 'User-Type: ADMIN' \
--header 'Content-Type: application/json' \
--data '{
    "name": "butter",
    "price": 56,
    "inventory": 4
}'
```

2. To fetch all products present in our grocery application use below api
     - Again access is to ADMIN only because this will give response of all active and inactive product present in grocery
```
curl --location --request GET 'http://localhost:8280/api/v1/gbs/all' \
--header 'User-Type: ADMIN' \
--header 'Content-Type: application/json' \
--data '{
    "name": "butter",
    "price": 56,
    "inventory": 4
}'
```

3. Api to fetch all available product present in grocery app.
     - Access is all i.e USER and ADMIN
   ```
   curl --location --request GET 'http://localhost:8280/api/v1/gbs/available' \
--header 'User-Type: USER' \
--header 'Content-Type: application/json' \
--data '{
    "name": "butter",
    "price": 56,
    "inventory": 4
}'
   ```

4. Api to delete product from grocery app
    - Access is to ADMIN only
    - It will soft delete data from database (meaning kept flag active for each product)
        - **True**: Product is visible
        - **False**: Product is delete ofr inactive.
```
curl --location --request DELETE 'http://localhost:8280/api/v1/gbs/<product_id>' \
--header 'User-Type: ADMIN'
```


5. Api/Curl to place multiple order
      - Access to all USER and ADMIN.
```
curl --location 'http://localhost:8280/api/v1/gbs/order/place' \
--header 'User-Type: ADMIN' \
--header 'Content-Type: application/json' \
--data '{
    "orders": [
        {
            "product_id": 2,
            "unit": 2
        },
        {
            "product_id": 1,
            "unit": 2
        }
    ]
}'
````

