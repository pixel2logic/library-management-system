package com.bds.library.dto;

/**
 * Data Transfer Object for Book-Author Join Query Result
 * Used to return combined data from Book and Author entities
 */
public class BookAuthorDTO {

    private Long bookId;
    private String bookTitle;
    private String isbn;
    private Double price;
    private String genre;
    private Long authorId;
    private String authorName;
    private String authorEmail;
    private String authorCountry;

    // Constructors
    public BookAuthorDTO() {
    }

    public BookAuthorDTO(Long bookId, String bookTitle, String isbn, Double price,
                         String genre, Long authorId, String authorName,
                         String authorEmail, String authorCountry) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.isbn = isbn;
        this.price = price;
        this.genre = genre;
        this.authorId = authorId;
        this.authorName = authorName;
        this.authorEmail = authorEmail;
        this.authorCountry = authorCountry;
    }

    // Getters and Setters
    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    public String getAuthorCountry() {
        return authorCountry;
    }

    public void setAuthorCountry(String authorCountry) {
        this.authorCountry = authorCountry;
    }

    @Override
    public String toString() {
        return "BookAuthorDTO{" +
                "bookTitle='" + bookTitle + '\'' +
                ", authorName='" + authorName + '\'' +
                ", genre='" + genre + '\'' +
                ", price=" + price +
                '}';
    }
}
