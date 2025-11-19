package com.bds.library.controller;

import com.bds.library.dto.BookAuthorDTO;
import com.bds.library.entity.Author;
import com.bds.library.entity.Book;
import com.bds.library.service.AuthorService;
import com.bds.library.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Controller for Book entity
 * Handles HTTP requests for book operations
 */
@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    /**
     * Display list of all books (READ operation)
     */
    @GetMapping
    public String listBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "books/list";
    }

    /**
     * Display books with author information using inner join
     */
    @GetMapping("/with-authors")
    public String listBooksWithAuthors(Model model) {
        List<BookAuthorDTO> booksWithAuthors = bookService.getAllBooksWithAuthors();
        model.addAttribute("booksWithAuthors", booksWithAuthors);
        return "books/list-with-authors";
    }

    /**
     * Show form to create a new book
     */
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new Book());
        List<Author> authors = authorService.getAllAuthors();
        model.addAttribute("authors", authors);
        return "books/create";
    }

    /**
     * Handle book creation (CREATE operation)
     */
    @PostMapping
    public String createBook(@Valid @ModelAttribute("book") Book book,
                            BindingResult result,
                            Model model,
                            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            List<Author> authors = authorService.getAllAuthors();
            model.addAttribute("authors", authors);
            return "books/create";
        }

        try {
            bookService.createBook(book);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Book '" + book.getTitle() + "' created successfully!");
            return "redirect:/books";
        } catch (IllegalArgumentException e) {
            result.rejectValue("isbn", "error.book", e.getMessage());
            List<Author> authors = authorService.getAllAuthors();
            model.addAttribute("authors", authors);
            return "books/create";
        } catch (Exception e) {
            result.rejectValue("title", "error.book",
                    "An error occurred while creating the book: " + e.getMessage());
            List<Author> authors = authorService.getAllAuthors();
            model.addAttribute("authors", authors);
            return "books/create";
        }
    }

    /**
     * Show form to edit an existing book
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Book book = bookService.getBookById(id)
                .orElse(null);

        if (book == null) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Book not found with id: " + id);
            return "redirect:/books";
        }

        model.addAttribute("book", book);
        List<Author> authors = authorService.getAllAuthors();
        model.addAttribute("authors", authors);
        return "books/edit";
    }

    /**
     * Handle book update (UPDATE operation)
     */
    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable Long id,
                            @Valid @ModelAttribute("book") Book book,
                            BindingResult result,
                            Model model,
                            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            book.setId(id);
            List<Author> authors = authorService.getAllAuthors();
            model.addAttribute("authors", authors);
            return "books/edit";
        }

        try {
            bookService.updateBook(id, book);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Book '" + book.getTitle() + "' updated successfully!");
            return "redirect:/books";
        } catch (IllegalArgumentException e) {
            result.rejectValue("isbn", "error.book", e.getMessage());
            book.setId(id);
            List<Author> authors = authorService.getAllAuthors();
            model.addAttribute("authors", authors);
            return "books/edit";
        } catch (Exception e) {
            result.rejectValue("title", "error.book",
                    "An error occurred while updating the book: " + e.getMessage());
            book.setId(id);
            List<Author> authors = authorService.getAllAuthors();
            model.addAttribute("authors", authors);
            return "books/edit";
        }
    }

    /**
     * Handle book deletion
     */
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            bookService.deleteBook(id);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Book deleted successfully!");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Book not found with id: " + id);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "An error occurred while deleting the book: " + e.getMessage());
        }
        return "redirect:/books";
    }

    /**
     * View book details
     */
    @GetMapping("/view/{id}")
    public String viewBook(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Book book = bookService.getBookById(id)
                .orElse(null);

        if (book == null) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Book not found with id: " + id);
            return "redirect:/books";
        }

        model.addAttribute("book", book);
        return "books/view";
    }
}
