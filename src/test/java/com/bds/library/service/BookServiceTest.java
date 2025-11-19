package com.bds.library.service;

import com.bds.library.dto.BookAuthorDTO;
import com.bds.library.entity.Author;
import com.bds.library.entity.Book;
import com.bds.library.repository.AuthorRepository;
import com.bds.library.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for BookService
 */
@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private BookService bookService;

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
        testAuthor.setId(1L);

        testBook = new Book(
                "Test Book",
                "978-1234567890",
                LocalDate.of(2024, 1, 1),
                29.99,
                300,
                "Fiction",
                "Test description"
        );
        testBook.setId(1L);
        testBook.setAuthor(testAuthor);
    }

    @Test
    public void testCreateBook_Success() {
        // Given
        when(bookRepository.findByIsbn(testBook.getIsbn())).thenReturn(Optional.empty());
        when(authorRepository.findById(1L)).thenReturn(Optional.of(testAuthor));
        when(bookRepository.save(any(Book.class))).thenReturn(testBook);

        // When
        Book createdBook = bookService.createBook(testBook);

        // Then
        assertThat(createdBook).isNotNull();
        assertThat(createdBook.getTitle()).isEqualTo("Test Book");
        verify(bookRepository, times(1)).save(testBook);
    }

    @Test
    public void testCreateBook_DuplicateIsbn() {
        // Given
        when(bookRepository.findByIsbn(testBook.getIsbn())).thenReturn(Optional.of(testBook));

        // When/Then
        assertThatThrownBy(() -> bookService.createBook(testBook))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("already exists");

        verify(bookRepository, never()).save(any(Book.class));
    }

    @Test
    public void testCreateBook_AuthorNotFound() {
        // Given
        when(bookRepository.findByIsbn(testBook.getIsbn())).thenReturn(Optional.empty());
        when(authorRepository.findById(1L)).thenReturn(Optional.empty());

        // When/Then
        assertThatThrownBy(() -> bookService.createBook(testBook))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Author not found");

        verify(bookRepository, never()).save(any(Book.class));
    }

    @Test
    public void testGetAllBooks() {
        // Given
        List<Book> books = Arrays.asList(testBook);
        when(bookRepository.findAll()).thenReturn(books);

        // When
        List<Book> result = bookService.getAllBooks();

        // Then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo("Test Book");
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    public void testGetBookById_Success() {
        // Given
        when(bookRepository.findById(1L)).thenReturn(Optional.of(testBook));

        // When
        Optional<Book> result = bookService.getBookById(1L);

        // Then
        assertThat(result).isPresent();
        assertThat(result.get().getTitle()).isEqualTo("Test Book");
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    public void testUpdateBook_Success() {
        // Given
        Book updatedBook = new Book(
                "Updated Book",
                "978-0987654321",
                LocalDate.of(2024, 2, 1),
                39.99,
                400,
                "Science Fiction",
                "Updated description"
        );
        updatedBook.setAuthor(testAuthor);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(testBook));
        when(bookRepository.findByIsbn(updatedBook.getIsbn())).thenReturn(Optional.empty());
        when(authorRepository.findById(1L)).thenReturn(Optional.of(testAuthor));
        when(bookRepository.save(any(Book.class))).thenReturn(testBook);

        // When
        Book result = bookService.updateBook(1L, updatedBook);

        // Then
        assertThat(result).isNotNull();
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    public void testUpdateBook_NotFound() {
        // Given
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        // When/Then
        assertThatThrownBy(() -> bookService.updateBook(1L, testBook))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("not found");

        verify(bookRepository, never()).save(any(Book.class));
    }

    @Test
    public void testDeleteBook_Success() {
        // Given
        when(bookRepository.findById(1L)).thenReturn(Optional.of(testBook));

        // When
        bookService.deleteBook(1L);

        // Then
        verify(bookRepository, times(1)).delete(testBook);
    }

    @Test
    public void testFindByIsbn() {
        // Given
        when(bookRepository.findByIsbn("978-1234567890")).thenReturn(Optional.of(testBook));

        // When
        Optional<Book> result = bookService.findByIsbn("978-1234567890");

        // Then
        assertThat(result).isPresent();
        assertThat(result.get().getIsbn()).isEqualTo("978-1234567890");
    }

    @Test
    public void testFindByGenre() {
        // Given
        List<Book> books = Arrays.asList(testBook);
        when(bookRepository.findByGenre("Fiction")).thenReturn(books);

        // When
        List<Book> result = bookService.findByGenre("Fiction");

        // Then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getGenre()).isEqualTo("Fiction");
    }

    @Test
    public void testGetAllBooksWithAuthors() {
        // Given
        BookAuthorDTO dto = new BookAuthorDTO(
                1L, "Test Book", "978-1234567890", 29.99, "Fiction",
                1L, "Test Author", "test@example.com", "Test Country"
        );
        List<BookAuthorDTO> dtos = Arrays.asList(dto);
        when(bookRepository.findAllBooksWithAuthors()).thenReturn(dtos);

        // When
        List<BookAuthorDTO> result = bookService.getAllBooksWithAuthors();

        // Then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getBookTitle()).isEqualTo("Test Book");
        assertThat(result.get(0).getAuthorName()).isEqualTo("Test Author");
    }

    @Test
    public void testSearchByTitle() {
        // Given
        List<Book> books = Arrays.asList(testBook);
        when(bookRepository.findByTitleContainingIgnoreCase("test")).thenReturn(books);

        // When
        List<Book> result = bookService.searchByTitle("test");

        // Then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo("Test Book");
    }
}
