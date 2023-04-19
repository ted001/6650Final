package com.educative.ecommerce;

import com.educative.ecommerce.model.AuthenticationToken;
import com.educative.ecommerce.model.MyServer;
import com.educative.ecommerce.model.User;
import com.educative.ecommerce.paxos.Paxos;
import com.educative.ecommerce.repository.MyServerRepository;
import com.educative.ecommerce.repository.PaxosRepository;
import com.educative.ecommerce.repository.TokenRepository;
import com.educative.ecommerce.repository.UserRepository;
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

@SpringBootApplication
public class EcommerceApplication {
    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(MyServerRepository myServerRepository,
                                 UserRepository userRepository,
                                 TokenRepository tokenRepository,
                                 PaxosRepository paxosRepository) throws Exception {
        return (String[] args) -> {
            String host = "localhost";
            int port = 8088;
            int index = 1;
            if (args.length == 1 && args[0].equals("--coordinator")) {
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
            } else if(args.length == 2) {
                host = args[0];
                String portString = args[1].split("=")[1];
                port = Integer.parseInt(portString);
            }
            String jsonInputString = "{\"host\": \""+host+"\", \"port\": "+port+"}";
            System.out.println(jsonInputString);

            URL url = new URL ("http://localhost:8088/addserver");
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            try(OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
            try(BufferedReader br = new BufferedReader(
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
}
