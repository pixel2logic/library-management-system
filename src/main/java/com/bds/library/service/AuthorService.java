package com.bds.library.service;

import com.bds.library.entity.Author;
import com.bds.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service class for Author entity
 * Contains business logic for author operations
 */
@Service
@Transactional
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    /**
     * Create a new author
     */
    public Author createAuthor(Author author) {
        // Check if email already exists
        if (author.getEmail() != null && !author.getEmail().isEmpty()) {
            Optional<Author> existingAuthor = authorRepository.findByEmail(author.getEmail());
            if (existingAuthor.isPresent()) {
                throw new IllegalArgumentException("Author with email " + author.getEmail() + " already exists");
            }
        }
        return authorRepository.save(author);
    }

    /**
     * Get all authors
     */
    @Transactional(readOnly = true)
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    /**
     * Get author by ID
     */
    @Transactional(readOnly = true)
    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    /**
     * Update an existing author
     */
    public Author updateAuthor(Long id, Author authorDetails) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Author not found with id: " + id));

        // Check if email is being changed and if it already exists
        if (authorDetails.getEmail() != null && !authorDetails.getEmail().equals(author.getEmail())) {
            Optional<Author> existingAuthor = authorRepository.findByEmail(authorDetails.getEmail());
            if (existingAuthor.isPresent()) {
                throw new IllegalArgumentException("Author with email " + authorDetails.getEmail() + " already exists");
            }
        }

        // Update fields
        author.setName(authorDetails.getName());
        author.setEmail(authorDetails.getEmail());
        author.setCountry(authorDetails.getCountry());
        author.setBiography(authorDetails.getBiography());

        return authorRepository.save(author);
    }

    /**
     * Delete an author
     */
    public void deleteAuthor(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Author not found with id: " + id));

        // Check if author has books
        Long bookCount = authorRepository.countBooksByAuthorId(id);
        if (bookCount > 0) {
            throw new IllegalStateException("Cannot delete author with existing books. Delete the books first.");
        }

        authorRepository.delete(author);
    }

    /**
     * Find author by email
     */
    @Transactional(readOnly = true)
    public Optional<Author> findByEmail(String email) {
        return authorRepository.findByEmail(email);
    }

    /**
     * Find authors by country
     */
    @Transactional(readOnly = true)
    public List<Author> findByCountry(String country) {
        return authorRepository.findByCountry(country);
    }

    /**
     * Search authors by name
     */
    @Transactional(readOnly = true)
    public List<Author> searchByName(String name) {
        return authorRepository.findByNameContainingIgnoreCase(name);
    }

    /**
     * Get count of books by author
     */
    @Transactional(readOnly = true)
    public Long getBookCountByAuthor(Long authorId) {
        return authorRepository.countBooksByAuthorId(authorId);
    }
}
