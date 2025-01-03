# Microservice for ordering a food

## tecnologies/arquitecture

- Hexagonal Arquitecture
- Springboot
- Jpa
- Relational database
- H2

### curl to create an order:

curl --request POST \
  --url http://localhost:8080/orders \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/10.0.0' \
  --data '{
	"customer_id": 10,
	"order_items": [
		{
			"food_id": 1,
			"quantity": 3
		},
		{
			"food_id": 1,
			"quantity": 3
		},
		{
			"food_id": 2,
			"quantity": 3
		}
	]
}'

![image](https://github.com/user-attachments/assets/f657422f-b413-490a-a36d-dbbfab2a2669)


### output
![image](https://github.com/user-attachments/assets/85a03531-a68f-4bc4-b478-68bbeed646e1)
