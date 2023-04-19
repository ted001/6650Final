package com.educative.ecommerce.service;

import com.educative.ecommerce.model.Server;
import com.educative.ecommerce.repository.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class CoordinatorService {

    @Autowired
    ServerRepository serverRepository;

    public Server addServer(Server server) {
        if(serverRepository.getServerByHostAndPort(server.getHost(), server.getPort()).isEmpty()) {
            serverRepository.save(server);
        }
        return serverRepository.getServerByHostAndPort(server.getHost(), server.getPort()).get();
    }

    public Server getRandomServer() {
        List<Server> serverList= serverRepository.findAll();
        int random = new Random().nextInt(serverList.size());
        return serverList.get(random);
    }

    public List<Server> serverList() {
        return serverRepository.findAll();
    }

}

