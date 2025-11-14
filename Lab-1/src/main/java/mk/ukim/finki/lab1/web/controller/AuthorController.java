package mk.ukim.finki.lab1.web.controller;


import mk.ukim.finki.lab1.model.Author;
import mk.ukim.finki.lab1.model.Book;
import mk.ukim.finki.lab1.service.AuthorService;
import mk.ukim.finki.lab1.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final BookService bookService;
    private final AuthorService authorService;

    public AuthorController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping
    public String listAuthors(
            @RequestParam(required = false) String filterName,
            @RequestParam(required = false) Double filterRating,
            Model model) {

        List<Author> authors = authorService.listAll();
        model.addAttribute("authors", authors);
        return "listAuthors";
    }

    @GetMapping("/author-add-form")
    public String showAddForm(Model model) {
        return "author-add-form";
    }

    @PostMapping("/edit/{authorId}")
    public String editAuthor(@PathVariable Long authorId,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam String country,
                           @RequestParam String biography ){
        authorService.update(authorId, name, surname, country, biography);
        return "redirect:/authors";
    }

    @PostMapping("/add")
    public String saveAuthor(@RequestParam(required = false) Long id,
                             @RequestParam String name,
                             @RequestParam String surname,
                             @RequestParam String country,
                             @RequestParam String biography){

        if (id== null) {
            authorService.create(name, surname, country, biography);
        } else {
            authorService.update(id, name, surname, country, biography);
        }
        return "redirect:/authors";
    }

    @GetMapping("/add-form")
    public String addAuthorPage(Model model){
        return "author-add-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        authorService.delete(id);
        return "redirect:/authors";
    }

}
