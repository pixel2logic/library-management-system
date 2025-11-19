package com.bds.library.config;

import com.bds.library.entity.Author;
import com.bds.library.entity.Book;
import com.bds.library.repository.AuthorRepository;
import com.bds.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

// Data Initialization Component
// Populates the database with sample data on application startup

@Component
public class DataInitializer implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Autowired
    public DataInitializer(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        bookRepository.deleteAll();
        authorRepository.deleteAll();

        // Create 10 Authors
        Author author1 = new Author(
                "J.K. Rowling",
                "jk.rowling@example.com",
                "United Kingdom",
                "British author best known for the Harry Potter fantasy series"
        );

        Author author2 = new Author(
                "George Orwell",
                "george.orwell@example.com",
                "United Kingdom",
                "English novelist and essayist, journalist and critic"
        );

        Author author3 = new Author(
                "Jane Austen",
                "jane.austen@example.com",
                "United Kingdom",
                "English novelist known for her romantic fiction"
        );

        Author author4 = new Author(
                "Stephen King",
                "stephen.king@example.com",
                "United States",
                "American author of horror, supernatural fiction, and suspense"
        );

        Author author5 = new Author(
                "Agatha Christie",
                "agatha.christie@example.com",
                "United Kingdom",
                "English writer known for detective novels"
        );

        Author author6 = new Author(
                "Dan Brown",
                "dan.brown@example.com",
                "United States",
                "American author best known for thriller novels"
        );

        Author author7 = new Author(
                "Harper Lee",
                "harper.lee@example.com",
                "United States",
                "American novelist widely known for To Kill a Mockingbird"
        );

        Author author8 = new Author(
                "F. Scott Fitzgerald",
                "f.fitzgerald@example.com",
                "United States",
                "American novelist and short story writer of the Jazz Age"
        );

        Author author9 = new Author(
                "Leo Tolstoy",
                "leo.tolstoy@example.com",
                "Russia",
                "Russian writer who is regarded as one of the greatest authors of all time"
        );

        Author author10 = new Author(
                "Mark Twain",
                "mark.twain@example.com",
                "United States",
                "American writer, humorist, entrepreneur, and lecturer"
        );

        // Save all authors
        authorRepository.save(author1);
        authorRepository.save(author2);
        authorRepository.save(author3);
        authorRepository.save(author4);
        authorRepository.save(author5);
        authorRepository.save(author6);
        authorRepository.save(author7);
        authorRepository.save(author8);
        authorRepository.save(author9);
        authorRepository.save(author10);

        System.out.println("10 Authors created successfully!");

        // Create 10 Books
        Book book1 = new Book(
                "Harry Potter and the Philosopher's Stone",
                "978-0-7475-3269-9",
                LocalDate.of(1997, 6, 26),
                29.99,
                223,
                "Fantasy",
                "The first novel in the Harry Potter series"
        );
        book1.setAuthor(author1);

        Book book2 = new Book(
                "1984",
                "978-0-452-28423-4",
                LocalDate.of(1949, 6, 8),
                19.99,
                328,
                "Dystopian Fiction",
                "A dystopian social science fiction novel and cautionary tale"
        );
        book2.setAuthor(author2);

        Book book3 = new Book(
                "Pride and Prejudice",
                "978-0-14-143951-8",
                LocalDate.of(1813, 1, 28),
                14.99,
                432,
                "Romance",
                "A romantic novel of manners"
        );
        book3.setAuthor(author3);

        Book book4 = new Book(
                "The Shining",
                "978-0-385-12167-5",
                LocalDate.of(1977, 1, 28),
                24.99,
                447,
                "Horror",
                "A horror novel about a family staying at an isolated hotel"
        );
        book4.setAuthor(author4);

        Book book5 = new Book(
                "Murder on the Orient Express",
                "978-0-00-711931-8",
                LocalDate.of(1934, 1, 1),
                16.99,
                256,
                "Mystery",
                "A detective novel featuring Hercule Poirot"
        );
        book5.setAuthor(author5);

        Book book6 = new Book(
                "The Da Vinci Code",
                "978-0-385-50420-1",
                LocalDate.of(2003, 3, 18),
                27.99,
                489,
                "Thriller",
                "A mystery thriller novel following symbologist Robert Langdon"
        );
        book6.setAuthor(author6);

        Book book7 = new Book(
                "To Kill a Mockingbird",
                "978-0-06-112008-4",
                LocalDate.of(1960, 7, 11),
                18.99,
                324,
                "Fiction",
                "A novel about racial injustice in the Deep South"
        );
        book7.setAuthor(author7);

        Book book8 = new Book(
                "The Great Gatsby",
                "978-0-7432-7356-5",
                LocalDate.of(1925, 4, 10),
                15.99,
                180,
                "Fiction",
                "A novel set in the Jazz Age on Long Island"
        );
        book8.setAuthor(author8);

        Book book9 = new Book(
                "War and Peace",
                "978-0-14-044793-4",
                LocalDate.of(1869, 1, 1),
                32.99,
                1225,
                "Historical Fiction",
                "A literary work examining history and politics in the Napoleonic era"
        );
        book9.setAuthor(author9);

        Book book10 = new Book(
                "The Adventures of Tom Sawyer",
                "978-0-14-303952-7",
                LocalDate.of(1876, 6, 1),
                12.99,
                274,
                "Adventure",
                "A novel about a boy growing up along the Mississippi River"
        );
        book10.setAuthor(author10);

        // Save all books
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
        bookRepository.save(book4);
        bookRepository.save(book5);
        bookRepository.save(book6);
        bookRepository.save(book7);
        bookRepository.save(book8);
        bookRepository.save(book9);
        bookRepository.save(book10);

        System.out.println("10 Books created successfully!");
        System.out.println("Database initialization completed!");
    }
}
