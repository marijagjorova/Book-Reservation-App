package mk.ukim.finki.lab1.web.controller;


import mk.ukim.finki.lab1.model.Author;
import mk.ukim.finki.lab1.model.Book;
import mk.ukim.finki.lab1.service.AuthorService;
import mk.ukim.finki.lab1.service.BookService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final BookService bookService;
    private final AuthorService authorService;

    public AuthorController(BookService bookService,
                            AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping
    public String listAuthors(Model model) {
        List <Author> authors = authorService.getAllAuthorsSortedBySurname();
        model.addAttribute("authors", authors);
        return "listAuthors";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ADMIN')")
    public String showAddForm(Model model) {
        return "author-add-form";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("author", authorService.findById(id));
        return "author-add-form";
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editAuthor(@PathVariable Long id,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam String country,
                           @RequestParam String biography ){
        authorService.update(id, name, surname, country, biography);
        return "redirect:/authors";
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String saveAuthor(@RequestParam String name,
                             @RequestParam String surname,
                             @RequestParam String country,
                             @RequestParam String biography){
            authorService.create(name, surname, country, biography);
        return "redirect:/authors";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteAuthor(@PathVariable Long id) {
        authorService.delete(id);
        return "redirect:/authors";
    }

}
