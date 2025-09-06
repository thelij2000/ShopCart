package com;

public class OrderService {

    public Order checkout(Cart cart) {
        for (CartItem item : cart.getItems()) {
            Book book = item.getBook();
            int quantity = item.getQuantity();

            if (book.getStockQuantity() >= quantity) {
                book.setStockQuantity(book.getStockQuantity() - quantity);
                return new Order(1L, book, quantity);
            }
        }
        return null;
    }

    public void cancelOrder(Order order) {
        Book book = order.getBook();
        book.setStockQuantity(book.getStockQuantity() + order.getQuantity());
    }
}
