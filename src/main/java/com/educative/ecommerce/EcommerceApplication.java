package com.educative.ecommerce;

import com.educative.ecommerce.model.*;
import com.educative.ecommerce.paxos.Paxos;
import com.educative.ecommerce.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@SpringBootApplication
public class EcommerceApplication {
    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(MyServerRepository myServerRepository,
                                 UserRepository userRepository,
                                 TokenRepository tokenRepository,
                                 CategoryRepo categoryRepo,
                                 ProductRepository productRepository,
                                 PaxosRepository paxosRepository) throws Exception {
        return (String[] args) -> {
            String host = "localhost";
            int port = 8088;
            int index = 1;
            if (args.length == 1 && args[0].equals("--coordinator")) {
                initUserData(userRepository, tokenRepository);

            } else if (args.length == 2) {
                host = args[0];
                String portString = args[1].split("=")[1];
                port = Integer.parseInt(portString);
            }

            initCategoryProductData(categoryRepo, productRepository);

            String jsonInputString = "{\"host\": \"" + host + "\", \"port\": " + port + "}";
            System.out.println(jsonInputString);

            URL url = new URL("http://localhost:8088/addserver");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
                String ss = response.toString().split("\"id\":")[1].split(",")[0];
                index = Integer.parseInt(ss);
            }

            myServerRepository.save(new MyServer(host, port, index));
            paxosRepository.save(new Paxos(0, null));
        };

    }

    private void initCategoryProductData(CategoryRepo categoryRepo, ProductRepository productRepository) {
        Category category1 = new Category(1, "Electronics", "iPhone", "https://images.unsplash" +
                ".com/photo-1526738549149-8e07eca6c147?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1325&q=80");
        Category category2 = new Category(2, "Kites", "These are Kites", "https://images.unsplash" +
                ".com/photo-1600387822941-a6ac49b2f0a3?ixid=MXwxMjA3fDB8MHxzZWFyY2h8OHx8a2l0ZXN8ZW58MHx8MHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60");

        Category category3 = new Category(3, "Television", "Television", "https://images.unsplash" +
                ".com/photo-1593359677879-a4bb92f829d1?ixid=MXwxMjA3fDB8MHxzZWFyY2h8NXx8dGVsZXZpc2lvbnxlbnwwfHwwfA%3D%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60");
        categoryRepo.save(category1);
        categoryRepo.save(category2);
        categoryRepo.save(category3);

        Product product1 = new Product("iphone", "https://images.unsplash" +
                ".com/photo-1530319067432-f2a729c03db5?ixlib=rb-4.0" +
                ".3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OHx8aXBob25lfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500" +
                "&q=60", 10000f, "this is iphone 100", category1);
        Product product2 = new Product("Hello World", "https://images.unsplash" +
                ".com/photo-1609584862854-57387c661076?ixid" +
                "=MXwxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHwxNHx8fGVufDB8fHw%3D&ixlib=rb-1.2" +
                ".1&auto=format&fit=crop&w=500&q=60", 100f, "hello World", category2);
        Product product3 = new Product("Ultrasonic sensor", "https://images.unsplash" +
                ".com/photo-1497997457905-3f85463eb0bc?ixid=MXwxMjA3fDB8MHxzZWFyY2h8MXx8c2Vuc29yfGVufDB8fDB8" +
                "&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60", 500f, "Highly accurate", category3);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
    }

    private void initUserData(UserRepository userRepository, TokenRepository tokenRepository) {
        User user = new User("user1", "user1", "1@1.com", "123");
        AuthenticationToken authenticationToken = new AuthenticationToken(user);
        userRepository.save(user);
        tokenRepository.save(authenticationToken);

        User user2 = new User("user2", "user2", "2@2.com", "123");
        AuthenticationToken authenticationToken2 = new AuthenticationToken(user2);
        userRepository.save(user2);
        tokenRepository.save(authenticationToken2);

        User user3 = new User("user3", "user3", "3@3.com", "123");
        AuthenticationToken authenticationToken3 = new AuthenticationToken(user3);
        userRepository.save(user3);
        tokenRepository.save(authenticationToken3);
    }
}
