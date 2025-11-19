package com.bds.library.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


// Author Entity Class
// Represents an author in the library system

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Author name is required")
    @Size(min = 2, max = 100, message = "Author name must be between 2 and 100 characters")
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Email(message = "Invalid email format")
    @Column(name = "email", unique = true, length = 100)
    private String email;

    @Column(name = "country", length = 50)
    private String country;

    @Column(name = "biography", columnDefinition = "TEXT")
    private String biography;

    // One-to-Many relationship with Book
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Book> books = new ArrayList<>();

    // Constructors
    public Author() {
    }

    public Author(String name, String email, String country, String biography) {
        this.name = name;
        this.email = email;
        this.country = country;
        this.biography = biography;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    // Helper method to add a book
    public void addBook(Book book) {
        books.add(book);
        book.setAuthor(this);
    }

    // Helper method to remove a book
    public void removeBook(Book book) {
        books.remove(book);
        book.setAuthor(null);
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
