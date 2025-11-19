package com.bds.library.repository;

import com.bds.library.entity.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for AuthorRepository
 */
@DataJpaTest
public class AuthorRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AuthorRepository authorRepository;

    private Author testAuthor;

    @BeforeEach
    public void setup() {
        testAuthor = new Author(
                "Test Author",
                "test@example.com",
                "Test Country",
                "Test biography"
        );
    }

    @Test
    public void testSaveAuthor() {
        // When
        Author savedAuthor = authorRepository.save(testAuthor);

        // Then
        assertThat(savedAuthor).isNotNull();
        assertThat(savedAuthor.getId()).isNotNull();
        assertThat(savedAuthor.getName()).isEqualTo("Test Author");
        assertThat(savedAuthor.getEmail()).isEqualTo("test@example.com");
    }

    @Test
    public void testFindAuthorById() {
        // Given
        Author savedAuthor = entityManager.persistAndFlush(testAuthor);

        // When
        Optional<Author> foundAuthor = authorRepository.findById(savedAuthor.getId());

        // Then
        assertThat(foundAuthor).isPresent();
        assertThat(foundAuthor.get().getName()).isEqualTo("Test Author");
    }

    @Test
    public void testFindAuthorByEmail() {
        // Given
        entityManager.persistAndFlush(testAuthor);

        // When
        Optional<Author> foundAuthor = authorRepository.findByEmail("test@example.com");

        // Then
        assertThat(foundAuthor).isPresent();
        assertThat(foundAuthor.get().getName()).isEqualTo("Test Author");
    }

    @Test
    public void testFindAuthorsByCountry() {
        // Given
        entityManager.persistAndFlush(testAuthor);
        Author anotherAuthor = new Author(
                "Another Author",
                "another@example.com",
                "Test Country",
                "Another biography"
        );
        entityManager.persistAndFlush(anotherAuthor);

        // When
        List<Author> authors = authorRepository.findByCountry("Test Country");

        // Then
        assertThat(authors).hasSize(2);
        assertThat(authors).extracting(Author::getCountry)
                .containsOnly("Test Country");
    }

    @Test
    public void testFindByNameContainingIgnoreCase() {
        // Given
        entityManager.persistAndFlush(testAuthor);

        // When
        List<Author> authors = authorRepository.findByNameContainingIgnoreCase("test");

        // Then
        assertThat(authors).hasSize(1);
        assertThat(authors.get(0).getName()).isEqualTo("Test Author");
    }

    @Test
    public void testUpdateAuthor() {
        // Given
        Author savedAuthor = entityManager.persistAndFlush(testAuthor);

        // When
        savedAuthor.setName("Updated Author");
        savedAuthor.setCountry("Updated Country");
        Author updatedAuthor = authorRepository.save(savedAuthor);

        // Then
        assertThat(updatedAuthor.getName()).isEqualTo("Updated Author");
        assertThat(updatedAuthor.getCountry()).isEqualTo("Updated Country");
    }

    @Test
    public void testDeleteAuthor() {
        // Given
        Author savedAuthor = entityManager.persistAndFlush(testAuthor);
        Long authorId = savedAuthor.getId();

        // When
        authorRepository.deleteById(authorId);

        // Then
        Optional<Author> deletedAuthor = authorRepository.findById(authorId);
        assertThat(deletedAuthor).isEmpty();
    }

    @Test
    public void testFindAllAuthors() {
        // Given
        entityManager.persistAndFlush(testAuthor);
        Author anotherAuthor = new Author(
                "Another Author",
                "another@example.com",
                "Another Country",
                "Another biography"
        );
        entityManager.persistAndFlush(anotherAuthor);

        // When
        List<Author> authors = authorRepository.findAll();

        // Then
        assertThat(authors).hasSize(2);
    }
}
