package com.educative.ecommerce.common;

import com.educative.ecommerce.model.Cart;
import com.educative.ecommerce.model.Category;
import com.educative.ecommerce.model.Product;
import com.educative.ecommerce.model.User;

import java.util.List;

public class ServerData {
    private List<User> users;
    private List<Cart> carts;
    private List<Category> categories;
    private List<Product> products;

    public ServerData(List<User> users, List<Cart> carts, List<Category> categories, List<Product> products) {
        this.users = users;
        this.carts = carts;
        this.categories = categories;
        this.products = products;
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

    public List<Category> getCategories() {
        return categories;
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "ServerData{" +
                "users=" + users +
                ", carts=" + carts +
                '}';
    }
}
