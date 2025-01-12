#!/bin/bash
docker-compose down
docker-compose up -d --build
echo 'ALL SET, now go to insomnia and post to http://localhost:8081/orders with this body
{
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
}
'