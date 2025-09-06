package com;

public class OrderService {

    public Order checkout(Cart cart) {
        if (cart == null || cart.getItems().isEmpty()) return null;
        CartItem item = cart.getItems().get(0);
        Book book = item.getBook();
        int quantity = item.getQuantity();

        // Return null if requested quantity exceeds stock
        if (quantity > book.getStockQuantity() || quantity <= 0) return null;

        // Do NOT reduce stock here (already handled in CartService)
        return new Order(1L, book, quantity);
    }

    public void cancelOrder(Order order) {
        if (order == null) return;
        Book book = order.getBook();
        book.setStockQuantity(book.getStockQuantity() + order.getQuantity());
    }
}
