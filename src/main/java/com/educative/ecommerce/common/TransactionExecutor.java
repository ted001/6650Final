package com.educative.ecommerce.common;

import com.educative.ecommerce.common.command.AddToCartCommand;
import com.educative.ecommerce.dto.cart.AddToCartDto;
import com.educative.ecommerce.model.User;
import com.educative.ecommerce.service.CartService;
import com.educative.ecommerce.service.UserService;
import org.springframework.http.ResponseEntity;

public class TransactionExecutor {

    public static ResponseEntity<Object> execute(Transaction transaction, UserService userService, CartService cartService) {
        if(transaction.getCommand().getType().equals("addToCart")) {
            User user = transaction.getCommand().getUser();
            AddToCartDto cart = transaction.getCommand().getCart();
            return new AddToCartCommand(user, cart).execute(cartService);
        }
        return null;
    }
}

