package com;

import java.util.List;

public class Cart {
    private Long id;
    private User user;
    private List<CartItem> items;

    public Cart(Long id, User user, List<CartItem> items) {
        this.id = id;
        this.user = user;
        this.items = items;
    }

    public List<CartItem> getItems() {
        return items;
    }
}
