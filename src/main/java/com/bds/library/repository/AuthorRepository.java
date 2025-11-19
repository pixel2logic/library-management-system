package com.bds.library.repository;

import com.bds.library.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Author entity
 * Extends JpaRepository for basic CRUD operations
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    /**
     * Find author by email
     */
    Optional<Author> findByEmail(String email);

    /**
     * Find authors by country
     */
    List<Author> findByCountry(String country);

    /**
     * Find authors whose name contains the given string (case-insensitive)
     */
    List<Author> findByNameContainingIgnoreCase(String name);

    /**
     * Custom query to find authors with at least one book
     */
    @Query("SELECT DISTINCT a FROM Author a JOIN a.books b")
    List<Author> findAuthorsWithBooks();

    /**
     * Custom query to count books by author
     */
    @Query("SELECT COUNT(b) FROM Book b WHERE b.author.id = :authorId")
    Long countBooksByAuthorId(Long authorId);
}
