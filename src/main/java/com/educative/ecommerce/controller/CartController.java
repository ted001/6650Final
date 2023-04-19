package com.educative.ecommerce.controller;

import com.educative.ecommerce.common.ApiResponse;
import com.educative.ecommerce.common.Transaction;
import com.educative.ecommerce.common.command.AddToCartCommand;
import com.educative.ecommerce.common.command.Command;
import com.educative.ecommerce.dto.cart.AddToCartDto;
import com.educative.ecommerce.dto.cart.CartDto;
import com.educative.ecommerce.model.User;
import com.educative.ecommerce.repository.MyServerRepository;
import com.educative.ecommerce.service.AuthenticationService;
import com.educative.ecommerce.service.CartService;
import com.educative.ecommerce.service.ProductService;
import com.educative.ecommerce.service.RestService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private MyServerRepository myServer;

    @Autowired
    RestService restService;

    // post cart api
    @PostMapping("/add")
    public ResponseEntity<Object> addToCart(@RequestBody AddToCartDto addToCartDto,
                                                 @RequestParam("token") String token) {
        // authenticate the token
        authenticationService.authenticate(token);
        // find the user
        User user = authenticationService.getUser(token);

        AddToCartCommand command = new AddToCartCommand(user, addToCartDto);
        Transaction transaction = new Transaction(Long.parseLong(System.currentTimeMillis()+""+myServer.getMyServerById(1).getPort()), command);
        return restService.post(restService.generateURL("localhost", 8088, "propose"), transaction);

        /*cartService.addToCart(addToCartDto, user);
        return new ResponseEntity<>(new ApiResponse(true, "Added to cart"), HttpStatus.CREATED);*/
    }


    // get all cart items for a user
    @GetMapping("/")
    public ResponseEntity<CartDto> getCartItems(@RequestParam("token") String token) {
        // authenticate the token
        authenticationService.authenticate(token);
        // find the user
        User user = authenticationService.getUser(token);
        // get cart items
        CartDto cartDto = cartService.listCartItems(user);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    // delete a cart item for a user

    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") Integer itemId,
                                                      @RequestParam("token") String token) {

        // authenticate the token
        authenticationService.authenticate(token);
        // find the user
        User user = authenticationService.getUser(token);
        cartService.deleteCartItem(itemId, user);

        return new ResponseEntity<>(new ApiResponse(true, "Item has been removed"), HttpStatus.OK);

    }

}
