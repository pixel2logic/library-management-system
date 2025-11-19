package com.bds.library.service;

import com.bds.library.entity.Author;
import com.bds.library.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for AuthorService
 */
@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    private Author testAuthor;

    @BeforeEach
    public void setup() {
        testAuthor = new Author(
                "Test Author",
                "test@example.com",
                "Test Country",
                "Test biography"
        );
        testAuthor.setId(1L);
    }

    @Test
    public void testCreateAuthor_Success() {
        // Given
        when(authorRepository.findByEmail(testAuthor.getEmail())).thenReturn(Optional.empty());
        when(authorRepository.save(any(Author.class))).thenReturn(testAuthor);

        // When
        Author createdAuthor = authorService.createAuthor(testAuthor);

        // Then
        assertThat(createdAuthor).isNotNull();
        assertThat(createdAuthor.getName()).isEqualTo("Test Author");
        verify(authorRepository, times(1)).save(testAuthor);
    }

    @Test
    public void testCreateAuthor_DuplicateEmail() {
        // Given
        when(authorRepository.findByEmail(testAuthor.getEmail())).thenReturn(Optional.of(testAuthor));

        // When/Then
        assertThatThrownBy(() -> authorService.createAuthor(testAuthor))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("already exists");

        verify(authorRepository, never()).save(any(Author.class));
    }

    @Test
    public void testGetAllAuthors() {
        // Given
        List<Author> authors = Arrays.asList(testAuthor);
        when(authorRepository.findAll()).thenReturn(authors);

        // When
        List<Author> result = authorService.getAllAuthors();

        // Then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Test Author");
        verify(authorRepository, times(1)).findAll();
    }

    @Test
    public void testGetAuthorById_Success() {
        // Given
        when(authorRepository.findById(1L)).thenReturn(Optional.of(testAuthor));

        // When
        Optional<Author> result = authorService.getAuthorById(1L);

        // Then
        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo("Test Author");
        verify(authorRepository, times(1)).findById(1L);
    }

    @Test
    public void testUpdateAuthor_Success() {
        // Given
        Author updatedAuthor = new Author(
                "Updated Author",
                "updated@example.com",
                "Updated Country",
                "Updated biography"
        );

        when(authorRepository.findById(1L)).thenReturn(Optional.of(testAuthor));
        when(authorRepository.findByEmail(updatedAuthor.getEmail())).thenReturn(Optional.empty());
        when(authorRepository.save(any(Author.class))).thenReturn(testAuthor);

        // When
        Author result = authorService.updateAuthor(1L, updatedAuthor);

        // Then
        assertThat(result).isNotNull();
        verify(authorRepository, times(1)).save(any(Author.class));
    }

    @Test
    public void testUpdateAuthor_NotFound() {
        // Given
        when(authorRepository.findById(1L)).thenReturn(Optional.empty());

        // When/Then
        assertThatThrownBy(() -> authorService.updateAuthor(1L, testAuthor))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("not found");

        verify(authorRepository, never()).save(any(Author.class));
    }

    @Test
    public void testDeleteAuthor_Success() {
        // Given
        when(authorRepository.findById(1L)).thenReturn(Optional.of(testAuthor));
        when(authorRepository.countBooksByAuthorId(1L)).thenReturn(0L);

        // When
        authorService.deleteAuthor(1L);

        // Then
        verify(authorRepository, times(1)).delete(testAuthor);
    }

    @Test
    public void testDeleteAuthor_WithBooks() {
        // Given
        when(authorRepository.findById(1L)).thenReturn(Optional.of(testAuthor));
        when(authorRepository.countBooksByAuthorId(1L)).thenReturn(5L);

        // When/Then
        assertThatThrownBy(() -> authorService.deleteAuthor(1L))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Cannot delete author with existing books");

        verify(authorRepository, never()).delete(any(Author.class));
    }

    @Test
    public void testFindByEmail() {
        // Given
        when(authorRepository.findByEmail("test@example.com")).thenReturn(Optional.of(testAuthor));

        // When
        Optional<Author> result = authorService.findByEmail("test@example.com");

        // Then
        assertThat(result).isPresent();
        assertThat(result.get().getEmail()).isEqualTo("test@example.com");
    }

    @Test
    public void testSearchByName() {
        // Given
        List<Author> authors = Arrays.asList(testAuthor);
        when(authorRepository.findByNameContainingIgnoreCase("test")).thenReturn(authors);

        // When
        List<Author> result = authorService.searchByName("test");

        // Then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Test Author");
    }
}
