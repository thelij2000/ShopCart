package com;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class CartOrderServiceTest {

    private User testUser;
    private Book testBook;
    private CartService cartService;
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        testUser = new User(1L, "john_doe", "pass123", "john@example.com", "John Doe", "123 St", "1234567890", UserRole.CUSTOMER);

        testBook = new Book(1L, "Clean Code", "Robert C. Martin", "Pearson", "1234567890", 50.0, 10, "A classic book on software craftsmanship", new Category(1L, "Programming", "Technical books"));

        cartService = new CartService();
        orderService = new OrderService();
    }

    @Test
    void addToCart_shouldAddCorrectQuantity_whenStockIsAvailable() {
        Cart cart = new Cart(1L, testUser, new ArrayList<>());

        cartService.addToCart(cart, testBook, 5);

        assertEquals(1, cart.getItems().size());
        assertEquals(5, cart.getItems().get(0).getQuantity());
        assertEquals(testBook, cart.getItems().get(0).getBook());
    }

    @Test
    void addToCart_shouldAddMaxAvailableQuantity_whenRequestedExceedsStock() {
        testBook.setStockQuantity(3);
        Cart cart = new Cart(1L, testUser, new ArrayList<>());

        cartService.addToCart(cart, testBook, 5);

        assertEquals(1, cart.getItems().size());
        assertEquals(3, cart.getItems().get(0).getQuantity()); // Only 3 in stock
    }

    @Test
    void checkout_shouldReduceStockAfterOrder() {
        Cart cart = new Cart(1L, testUser, new ArrayList<>());
        cartService.addToCart(cart, testBook, 4);

        Order order = orderService.checkout(cart);

        assertEquals(6, testBook.getStockQuantity()); // 10 - 4
        assertNotNull(order);
        assertEquals(testBook, order.getBook());
        assertEquals(4, order.getQuantity());
    }

    @Test
    void cancelOrder_shouldRestoreStock() {
        testBook.setStockQuantity(5); // After previous checkout
        Order order = new Order(1L, testBook, 5);

        orderService.cancelOrder(order);

        assertEquals(10, testBook.getStockQuantity());
    }

    @Test
    void addToCart_shouldNotAddItem_whenQuantityIsZero() {
        Cart cart = new Cart(1L, testUser, new ArrayList<>());
        cartService.addToCart(cart, testBook, 0);
        assertEquals(0, cart.getItems().size());
    }

    @Test
    void addToCart_shouldNotAddItem_whenQuantityIsNegative() {
        Cart cart = new Cart(1L, testUser, new ArrayList<>());
        cartService.addToCart(cart, testBook, -2);
        assertEquals(0, cart.getItems().size());
    }

    @Test
    void checkout_shouldReturnNull_whenCartIsEmpty() {
        Cart cart = new Cart(1L, testUser, new ArrayList<>());
        Order order = orderService.checkout(cart);
        assertNull(order);
    }

    @Test
    void addToCart_shouldIncreaseQuantity_whenSameBookAddedTwice() {
        Cart cart = new Cart(1L, testUser, new ArrayList<>());
        cartService.addToCart(cart, testBook, 2);
        cartService.addToCart(cart, testBook, 3);
        assertEquals(1, cart.getItems().size());
        assertEquals(5, cart.getItems().get(0).getQuantity());
    }

    @Test
    void cancelOrder_shouldHandleNullOrderGracefully() {
        assertDoesNotThrow(() -> orderService.cancelOrder(null));
    }

    @Test
    void addToCart_shouldNotAddItem_whenBookStockIsZero() {
        testBook.setStockQuantity(0);
        Cart cart = new Cart(1L, testUser, new ArrayList<>());
        cartService.addToCart(cart, testBook, 1);
        assertEquals(0, cart.getItems().size());
    }

    @Test
    void checkout_shouldReturnNull_whenRequestedQuantityExceedsStock() {
        testBook.setStockQuantity(2);
        Cart cart = new Cart(1L, testUser, new ArrayList<>());
        cartService.addToCart(cart, testBook, 5);
        Order order = orderService.checkout(cart);
        assertNull(order);
    }

    @Test
    void cancelOrder_shouldNotChangeStock_whenOrderQuantityIsZero() {
        testBook.setStockQuantity(5);
        Order order = new Order(2L, testBook, 0);
        orderService.cancelOrder(order);
        assertEquals(5, testBook.getStockQuantity());
    }

    @Test
    void addToCart_shouldAddMultipleDifferentBooks() {
        Book anotherBook = new Book(2L, "Refactoring", "Martin Fowler", "Addison-Wesley", "0987654321", 60.0, 5, "Refactoring techniques", new Category(2L, "Programming", "Technical books"));
        Cart cart = new Cart(1L, testUser, new ArrayList<>());
        cartService.addToCart(cart, testBook, 2);
        cartService.addToCart(cart, anotherBook, 3);
        assertEquals(2, cart.getItems().size());
        assertEquals(2, cart.getItems().get(0).getQuantity());
        assertEquals(3, cart.getItems().get(1).getQuantity());
    }

    @Test
    void cancelOrder_shouldNotExceedOriginalStock() {
        testBook.setStockQuantity(10);
        Order order = new Order(3L, testBook, 5);
        orderService.cancelOrder(order);
        orderService.cancelOrder(order); // Cancel twice
        assertEquals(20, testBook.getStockQuantity());
    }
}
