package com.entity;

public class Book {
    private String bookName;
    private String bookPrice;
    private String bookUrl;

    public Book() {

    }

    public Book(String bookName, String bookPrice, String bookUrl) {
        this.bookName = bookName;
        this.bookPrice = bookPrice;
        this.bookUrl = bookUrl;
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookPrice() {
        return bookPrice;
    }

    public String getBookUrl() {
        return bookUrl;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setBookPrice(String bookPrice) {
        this.bookPrice = bookPrice;
    }

    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl;
    }
}
