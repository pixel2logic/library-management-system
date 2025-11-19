package com.bds.library.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;


// Book Entity Class
// Represents a book in the library system

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Book title is required")
    @Size(min = 1, max = 200, message = "Book title must be between 1 and 200 characters")
    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @NotBlank(message = "ISBN is required")
    @Pattern(regexp = "^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?$)[\\d-]+$",
             message = "Invalid ISBN format")
    @Column(name = "isbn", unique = true, length = 20)
    private String isbn;

    @Column(name = "published_date")
    private LocalDate publishedDate;

    @Min(value = 0, message = "Price must be positive")
    @Column(name = "price")
    private Double price;

    @Min(value = 0, message = "Pages must be positive")
    @Column(name = "pages")
    private Integer pages;

    @Column(name = "genre", length = 50)
    private String genre;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    // Many-to-One relationship with Author
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    @NotNull(message = "Author is required")
    private Author author;

    // Constructors
    public Book() {
    }

    public Book(String title, String isbn, LocalDate publishedDate, Double price,
                Integer pages, String genre, String description) {
        this.title = title;
        this.isbn = isbn;
        this.publishedDate = publishedDate;
        this.price = price;
        this.pages = pages;
        this.genre = genre;
        this.description = description;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publishedDate=" + publishedDate +
                ", price=" + price +
                ", genre='" + genre + '\'' +
                '}';
    }
}
