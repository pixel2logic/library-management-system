package com.bds.library.repository;

import com.bds.library.dto.BookAuthorDTO;
import com.bds.library.entity.Author;
import com.bds.library.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for BookRepository
 */
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    private Author testAuthor;
    private Book testBook;

    @BeforeEach
    public void setup() {
        testAuthor = new Author(
                "Test Author",
                "test@example.com",
                "Test Country",
                "Test biography"
        );
        entityManager.persistAndFlush(testAuthor);

        testBook = new Book(
                "Test Book",
                "978-1234567890",
                LocalDate.of(2024, 1, 1),
                29.99,
                300,
                "Fiction",
                "Test description"
        );
        testBook.setAuthor(testAuthor);
    }

    @Test
    public void testSaveBook() {
        // When
        Book savedBook = bookRepository.save(testBook);

        // Then
        assertThat(savedBook).isNotNull();
        assertThat(savedBook.getId()).isNotNull();
        assertThat(savedBook.getTitle()).isEqualTo("Test Book");
        assertThat(savedBook.getAuthor()).isNotNull();
    }

    @Test
    public void testFindBookById() {
        // Given
        Book savedBook = entityManager.persistAndFlush(testBook);

        // When
        Optional<Book> foundBook = bookRepository.findById(savedBook.getId());

        // Then
        assertThat(foundBook).isPresent();
        assertThat(foundBook.get().getTitle()).isEqualTo("Test Book");
    }

    @Test
    public void testFindBookByIsbn() {
        // Given
        entityManager.persistAndFlush(testBook);

        // When
        Optional<Book> foundBook = bookRepository.findByIsbn("978-1234567890");

        // Then
        assertThat(foundBook).isPresent();
        assertThat(foundBook.get().getTitle()).isEqualTo("Test Book");
    }

    @Test
    public void testFindBooksByGenre() {
        // Given
        entityManager.persistAndFlush(testBook);
        Book anotherBook = new Book(
                "Another Book",
                "978-0987654321",
                LocalDate.of(2024, 2, 1),
                19.99,
                250,
                "Fiction",
                "Another description"
        );
        anotherBook.setAuthor(testAuthor);
        entityManager.persistAndFlush(anotherBook);

        // When
        List<Book> books = bookRepository.findByGenre("Fiction");

        // Then
        assertThat(books).hasSize(2);
        assertThat(books).extracting(Book::getGenre)
                .containsOnly("Fiction");
    }

    @Test
    public void testFindBooksByAuthorId() {
        // Given
        entityManager.persistAndFlush(testBook);

        // When
        List<Book> books = bookRepository.findByAuthorId(testAuthor.getId());

        // Then
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getTitle()).isEqualTo("Test Book");
    }

    @Test
    public void testFindByTitleContainingIgnoreCase() {
        // Given
        entityManager.persistAndFlush(testBook);

        // When
        List<Book> books = bookRepository.findByTitleContainingIgnoreCase("test");

        // Then
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getTitle()).isEqualTo("Test Book");
    }

    @Test
    public void testFindAllBooksWithAuthors() {
        // Given
        entityManager.persistAndFlush(testBook);

        // When
        List<BookAuthorDTO> results = bookRepository.findAllBooksWithAuthors();

        // Then
        assertThat(results).isNotEmpty();
        assertThat(results.get(0).getBookTitle()).isEqualTo("Test Book");
        assertThat(results.get(0).getAuthorName()).isEqualTo("Test Author");
    }

    @Test
    public void testFindBooksWithAuthorsByGenre() {
        // Given
        entityManager.persistAndFlush(testBook);

        // When
        List<BookAuthorDTO> results = bookRepository.findBooksWithAuthorsByGenre("Fiction");

        // Then
        assertThat(results).isNotEmpty();
        assertThat(results.get(0).getGenre()).isEqualTo("Fiction");
        assertThat(results.get(0).getBookTitle()).isEqualTo("Test Book");
    }

    @Test
    public void testFindBooksByPriceRange() {
        // Given
        entityManager.persistAndFlush(testBook);

        // When
        List<Book> books = bookRepository.findBooksByPriceRange(20.0, 40.0);

        // Then
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getPrice()).isEqualTo(29.99);
    }

    @Test
    public void testUpdateBook() {
        // Given
        Book savedBook = entityManager.persistAndFlush(testBook);

        // When
        savedBook.setTitle("Updated Book");
        savedBook.setPrice(39.99);
        Book updatedBook = bookRepository.save(savedBook);

        // Then
        assertThat(updatedBook.getTitle()).isEqualTo("Updated Book");
        assertThat(updatedBook.getPrice()).isEqualTo(39.99);
    }

    @Test
    public void testDeleteBook() {
        // Given
        Book savedBook = entityManager.persistAndFlush(testBook);
        Long bookId = savedBook.getId();

        // When
        bookRepository.deleteById(bookId);

        // Then
        Optional<Book> deletedBook = bookRepository.findById(bookId);
        assertThat(deletedBook).isEmpty();
    }
}
