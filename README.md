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
git clone https://github.com/MarshmelloRice/frontend.git

In the project folder terminal, run
> npm install
> 
> npm run serve


1. Open Browser and input http://localhost:8080/



2. use the test account to signin

> Email: 1@1.com
> 
> Password: 123

![image](https://user-images.githubusercontent.com/8579944/234124670-fe2b55dd-b836-4d23-8b5b-66a18aa262ff.png)


3. Browse and Add Product to the cart

![image](https://user-images.githubusercontent.com/8579944/234124884-834f422e-6e48-4f8a-9a8a-cb141c26e5ab.png)

![image](https://user-images.githubusercontent.com/8579944/234124852-6861e155-5a71-417b-8c54-a6cc38da4633.png)


