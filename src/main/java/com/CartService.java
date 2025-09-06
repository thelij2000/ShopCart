package com;

public class CartService {
    public void addToCart(Cart cart, Book book, int quantity) {
        if (quantity <= 0) return; // Ignore zero or negative quantities

        // Find existing CartItem for the book
        for (CartItem item : cart.getItems()) {
            if (item.getBook().equals(book)) {
                int available = book.getStockQuantity();
                int addQty = Math.min(quantity, available);
                item.setQuantity(item.getQuantity() + addQty);
                book.setStockQuantity(available - addQty);
                return;
            }
        }

        // If not found, add new CartItem
        int available = book.getStockQuantity();
        int addQty = Math.min(quantity, available);
        if (addQty > 0) {
            cart.getItems().add(new CartItem(book, addQty));
            book.setStockQuantity(available - addQty);
        }
    }
}

