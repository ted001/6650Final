package com.educative.ecommerce.controller;

import com.educative.ecommerce.model.Server;
import com.educative.ecommerce.service.CoordinatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoordinatorController {
    @Autowired
    CoordinatorService coordinatorService;

    @PostMapping("/getServer")
    public ResponseEntity<Object> getServer() {
        Server server = coordinatorService.getRandomServer();
        System.out.println("Assigning:"+server);
        return new ResponseEntity<>(server, HttpStatus.OK);
    }

}

