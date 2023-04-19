package com.educative.ecommerce.common.command;

import com.educative.ecommerce.dto.cart.AddToCartDto;
import com.educative.ecommerce.dto.user.SignupDto;
import com.educative.ecommerce.model.User;

public class Command {
    String type;
    User user;
    AddToCartDto cart;

    public Command() {
    }

    public AddToCartDto getCart() {
        return cart;
    }

    public void setCart(AddToCartDto cart) {
        this.cart = cart;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Command{" +
                "user=" + user +
                ", cart=" + cart +
                ", type='" + type + '\'' +
                '}';
    }
}
