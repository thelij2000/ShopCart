package com;

public class CartService {

    public void addToCart(Cart cart, Book book, int requestedQuantity) {
        int allowedQuantity = Math.min(requestedQuantity, book.getStockQuantity());
        CartItem item = new CartItem(book, allowedQuantity);
        cart.getItems().add(item);
    }
}

