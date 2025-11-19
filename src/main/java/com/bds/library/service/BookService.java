package com.bds.library.service;

import com.bds.library.dto.BookAuthorDTO;
import com.bds.library.entity.Author;
import com.bds.library.entity.Book;
import com.bds.library.repository.AuthorRepository;
import com.bds.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service class for Book entity
 * Contains business logic for book operations
 */
@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    /**
     * Create a new book
     */
    public Book createBook(Book book) {
        // Check if ISBN already exists
        if (book.getIsbn() != null && !book.getIsbn().isEmpty()) {
            Optional<Book> existingBook = bookRepository.findByIsbn(book.getIsbn());
            if (existingBook.isPresent()) {
                throw new IllegalArgumentException("Book with ISBN " + book.getIsbn() + " already exists");
            }
        }

        // Validate that author exists
        if (book.getAuthor() != null && book.getAuthor().getId() != null) {
            Author author = authorRepository.findById(book.getAuthor().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Author not found with id: " + book.getAuthor().getId()));
            book.setAuthor(author);
        } else {
            throw new IllegalArgumentException("Author is required for creating a book");
        }

        return bookRepository.save(book);
    }

    /**
     * Get all books
     */
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * Get book by ID
     */
    @Transactional(readOnly = true)
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    /**
     * Update an existing book
     */
    public Book updateBook(Long id, Book bookDetails) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + id));

        // Check if ISBN is being changed and if it already exists
        if (bookDetails.getIsbn() != null && !bookDetails.getIsbn().equals(book.getIsbn())) {
            Optional<Book> existingBook = bookRepository.findByIsbn(bookDetails.getIsbn());
            if (existingBook.isPresent()) {
                throw new IllegalArgumentException("Book with ISBN " + bookDetails.getIsbn() + " already exists");
            }
        }

        // Validate that author exists if being changed
        if (bookDetails.getAuthor() != null && bookDetails.getAuthor().getId() != null) {
            Author author = authorRepository.findById(bookDetails.getAuthor().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Author not found with id: " + bookDetails.getAuthor().getId()));
            book.setAuthor(author);
        }

        // Update fields
        book.setTitle(bookDetails.getTitle());
        book.setIsbn(bookDetails.getIsbn());
        book.setPublishedDate(bookDetails.getPublishedDate());
        book.setPrice(bookDetails.getPrice());
        book.setPages(bookDetails.getPages());
        book.setGenre(bookDetails.getGenre());
        book.setDescription(bookDetails.getDescription());

        return bookRepository.save(book);
    }

    /**
     * Delete a book
     */
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + id));
        bookRepository.delete(book);
    }

    /**
     * Find book by ISBN
     */
    @Transactional(readOnly = true)
    public Optional<Book> findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    /**
     * Find books by genre
     */
    @Transactional(readOnly = true)
    public List<Book> findByGenre(String genre) {
        return bookRepository.findByGenre(genre);
    }

    /**
     * Find books by author ID
     */
    @Transactional(readOnly = true)
    public List<Book> findByAuthorId(Long authorId) {
        return bookRepository.findByAuthorId(authorId);
    }

    /**
     * Search books by title
     */
    @Transactional(readOnly = true)
    public List<Book> searchByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    /**
     * Get all books with author information (Inner Join)
     * This is the custom query method required by the assignment
     */
    @Transactional(readOnly = true)
    public List<BookAuthorDTO> getAllBooksWithAuthors() {
        return bookRepository.findAllBooksWithAuthors();
    }

    /**
     * Get books with authors by genre
     */
    @Transactional(readOnly = true)
    public List<BookAuthorDTO> getBooksWithAuthorsByGenre(String genre) {
        return bookRepository.findBooksWithAuthorsByGenre(genre);
    }

    /**
     * Find books by price range
     */
    @Transactional(readOnly = true)
    public List<Book> findBooksByPriceRange(Double minPrice, Double maxPrice) {
        return bookRepository.findBooksByPriceRange(minPrice, maxPrice);
    }
}
