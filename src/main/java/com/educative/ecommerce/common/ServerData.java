package com.educative.ecommerce.common;

import com.educative.ecommerce.model.Cart;
import com.educative.ecommerce.model.User;

import java.awt.print.Book;
import java.util.List;

public class ServerData {
    private List<User> users;
    private List<Cart> carts;

    public ServerData(List<User> users, List<Cart> carts) {
        this.users = users;
        this.carts = carts;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }


    @Override
    public String toString() {
        return "ServerData{" +
                "users=" + users +
                ", carts=" + carts +
                '}';
    }
}
