package com.educative.ecommerce.controller;

import com.educative.ecommerce.common.ServerData;
import com.educative.ecommerce.dto.ProductDto;
import com.educative.ecommerce.model.Category;
import com.educative.ecommerce.model.Product;
import com.educative.ecommerce.model.Server;
import com.educative.ecommerce.repository.CartRepository;
import com.educative.ecommerce.repository.CategoryRepo;
import com.educative.ecommerce.repository.ProductRepository;
import com.educative.ecommerce.repository.UserRepository;
import com.educative.ecommerce.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Controller
public class ServerController {

    @Autowired
    CoordinatorService coordinatorService;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartService cartService;

    @Autowired
    RestService restService;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryRepo categoryRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @PostMapping("/addserver")
    public ResponseEntity<Object> addServer(@RequestBody Server server) {
        Server servero = coordinatorService.addServer(server);
        System.out.println("Added Server:" + server);
        ServerData serverData = new ServerData(userService.allUsers(), cartService.allCarts(),
                categoryService.listCategory(), productService.allProducts());
        restService.post(restService.generateURL(server.getHost(), server.getPort(), "serverdata"), serverData);
        return new ResponseEntity<>(servero, HttpStatus.CREATED);
    }

    @PostMapping("/allservers")
    public ResponseEntity<List<Server>> allServer() {
        return new ResponseEntity<>(coordinatorService.serverList(), HttpStatus.OK);
    }

    @PostMapping("/serverdata")
    public ResponseEntity<Object> makeData(@RequestBody ServerData serverData) {
        System.out.println("Copying state from Coordinator");

        userRepository.saveAll(serverData.getUsers());
        cartRepository.saveAll(serverData.getCarts());
        //categoryRepository.saveAll(serverData.getCategories());
        //productRepository.saveAll(serverData.getProducts());

        System.out.println("State Copied");
        return new ResponseEntity<>(false, HttpStatus.OK);
    }
}

