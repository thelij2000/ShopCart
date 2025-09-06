package com;

public class Book {
    private Long id;
    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private double price;
    private int stockQuantity;
    private String description;
    private Category category;

    // constructor, getters, setters

    public Book(Long id, String title, String author, String publisher, String isbn, double price, int stockQuantity, String description, Category category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.description = description;
        this.category = category;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
