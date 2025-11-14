package mk.ukim.finki.lab1.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.lab1.model.Book;
import mk.ukim.finki.lab1.service.AuthorService;
import mk.ukim.finki.lab1.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    // LIST + SEARCH
    @GetMapping
    public String listBooks(
            @RequestParam(required = false) String filterName,
            @RequestParam(required = false) Double filterRating,
            Model model) {

        List<Book> books = bookService.listAll();
        model.addAttribute("books", books);
        return "listBooks";
    }

    // SHOW ADD FORM
    @GetMapping("/book-form")
    public String showAddForm(Model model) {
        model.addAttribute("authors", authorService.findAll());
        return "book-form";
    }

    // SHOW EDIT FORM
    @GetMapping("/book-form/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        model.addAttribute("authors", authorService.findAll());
        return "book-form";
    }

    // SAVE BOOK (ADD/EDIT)
    @PostMapping("/add")
    public String saveBook(@RequestParam(required = false) Long id,
                           @RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Double averageRating,
                            @RequestParam Long authorId){

        if (id == null) {
            bookService.create(title, genre, averageRating, authorId);
        } else {
            bookService.update(id, title, genre, averageRating, authorId);
        }
        return "redirect:/books";
    }

    @GetMapping("/add-form")
    public String addBookPage(Model model){
        model.addAttribute("authors",authorService.findAll());
        return "book-form";
    }

    // DELETE BOOK
    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/books";
    }
}
