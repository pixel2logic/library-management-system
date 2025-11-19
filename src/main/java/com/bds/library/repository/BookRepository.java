package com.bds.library.repository;

import com.bds.library.dto.BookAuthorDTO;
import com.bds.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Book entity
 * Extends JpaRepository for basic CRUD operations
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    /**
     * Find book by ISBN
     */
    Optional<Book> findByIsbn(String isbn);

    /**
     * Find books by genre
     */
    List<Book> findByGenre(String genre);

    /**
     * Find books by author ID
     */
    List<Book> findByAuthorId(Long authorId);

    /**
     * Find books whose title contains the given string (case-insensitive)
     */
    List<Book> findByTitleContainingIgnoreCase(String title);

    /**
     * Custom query method to perform inner join between Book and Author
     * Returns a combined result with book and author information
     */
    @Query("SELECT new com.bds.library.dto.BookAuthorDTO(" +
           "b.id, b.title, b.isbn, b.price, b.genre, " +
           "a.id, a.name, a.email, a.country) " +
           "FROM Book b INNER JOIN b.author a")
    List<BookAuthorDTO> findAllBooksWithAuthors();

    /**
     * Custom query to find books with authors filtered by genre
     */
    @Query("SELECT new com.bds.library.dto.BookAuthorDTO(" +
           "b.id, b.title, b.isbn, b.price, b.genre, " +
           "a.id, a.name, a.email, a.country) " +
           "FROM Book b INNER JOIN b.author a " +
           "WHERE b.genre = :genre")
    List<BookAuthorDTO> findBooksWithAuthorsByGenre(@Param("genre") String genre);

    /**
     * Custom query to find books within a price range
     */
    @Query("SELECT b FROM Book b WHERE b.price BETWEEN :minPrice AND :maxPrice")
    List<Book> findBooksByPriceRange(@Param("minPrice") Double minPrice,
                                     @Param("maxPrice") Double maxPrice);
}
