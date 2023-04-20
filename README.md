# 6650Final

# How to Run

## Server Side

1. Start Cooridinator

> cd bin
> 
> Coordinator always runs at port 8088. Run the coordinator app by typing
> 
> java -jar ecommerce.jar --coordinator

2. Start Other Servers
> cd bin
> Run the server app by typing the command, and changing the ports:
> 
> java -jar ecommerce.jar localhost --server.port=8011
> 
> java -jar ecommerce.jar localhost --server.port=8022
> 
> java -jar ecommerce.jar localhost --server.port=8033


Login the H2 Database Console to check the database, eg:

http://localhost:8088/h2-console/

http://localhost:8011/h2-console/

http://localhost:8021/h2-console/

![image](https://user-images.githubusercontent.com/8579944/233249245-93c169bb-e16b-4d6c-9791-9e53c2ea4644.png)


## Client Side

1. use the test account to signin 

![image](https://user-images.githubusercontent.com/8579944/233251329-a9f226f1-b5e9-47c8-8721-45c4c98c6b9b.png)

2. Add Product to the cart

![image](https://user-images.githubusercontent.com/8579944/233252067-1e43bbff-9ae8-411c-b79f-68d86229a18e.png)

