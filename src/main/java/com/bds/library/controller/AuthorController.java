package com.bds.library.controller;

import com.bds.library.entity.Author;
import com.bds.library.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Controller for Author entity
 * Handles HTTP requests for author operations
 */
@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    /**
     * Display list of all authors (READ operation)
     */
    @GetMapping
    public String listAuthors(Model model) {
        List<Author> authors = authorService.getAllAuthors();
        model.addAttribute("authors", authors);
        return "authors/list";
    }

    /**
     * Show form to create a new author
     */
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("author", new Author());
        return "authors/create";
    }

    /**
     * Handle author creation (CREATE operation)
     */
    @PostMapping
    public String createAuthor(@Valid @ModelAttribute("author") Author author,
                              BindingResult result,
                              RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "authors/create";
        }

        try {
            authorService.createAuthor(author);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Author '" + author.getName() + "' created successfully!");
            return "redirect:/authors";
        } catch (IllegalArgumentException e) {
            result.rejectValue("email", "error.author", e.getMessage());
            return "authors/create";
        } catch (Exception e) {
            result.rejectValue("name", "error.author",
                    "An error occurred while creating the author: " + e.getMessage());
            return "authors/create";
        }
    }

    /**
     * Show form to edit an existing author
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Author author = authorService.getAuthorById(id)
                .orElse(null);

        if (author == null) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Author not found with id: " + id);
            return "redirect:/authors";
        }

        model.addAttribute("author", author);
        return "authors/edit";
    }

    /**
     * Handle author update (UPDATE operation)
     */
    @PostMapping("/update/{id}")
    public String updateAuthor(@PathVariable Long id,
                              @Valid @ModelAttribute("author") Author author,
                              BindingResult result,
                              RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            author.setId(id);
            return "authors/edit";
        }

        try {
            authorService.updateAuthor(id, author);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Author '" + author.getName() + "' updated successfully!");
            return "redirect:/authors";
        } catch (IllegalArgumentException e) {
            result.rejectValue("email", "error.author", e.getMessage());
            author.setId(id);
            return "authors/edit";
        } catch (Exception e) {
            result.rejectValue("name", "error.author",
                    "An error occurred while updating the author: " + e.getMessage());
            author.setId(id);
            return "authors/edit";
        }
    }

    /**
     * Handle author deletion
     */
    @GetMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            authorService.deleteAuthor(id);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Author deleted successfully!");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Author not found with id: " + id);
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "An error occurred while deleting the author: " + e.getMessage());
        }
        return "redirect:/authors";
    }

    /**
     * View author details
     */
    @GetMapping("/view/{id}")
    public String viewAuthor(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Author author = authorService.getAuthorById(id)
                .orElse(null);

        if (author == null) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Author not found with id: " + id);
            return "redirect:/authors";
        }

        Long bookCount = authorService.getBookCountByAuthor(id);
        model.addAttribute("author", author);
        model.addAttribute("bookCount", bookCount);
        return "authors/view";
    }
}
