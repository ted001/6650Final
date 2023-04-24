package com.educative.ecommerce.common.command;

import com.educative.ecommerce.common.ApiResponse;
import com.educative.ecommerce.dto.cart.AddToCartDto;
import com.educative.ecommerce.model.User;
import com.educative.ecommerce.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AddToCartCommand extends Command {

    public AddToCartCommand(User user, AddToCartDto cart) {
        super();
        this.type = "addToCart";
        this.cart = cart;
        this.user = user;
    }

    public ResponseEntity<Object> execute(CartService cartService) {
        //System.out.println("Add:" + cart);
        cartService.addToCart(cart, user);
        return new ResponseEntity<>(new ApiResponse(true, "Added to cart"), HttpStatus.CREATED);
    }
}

