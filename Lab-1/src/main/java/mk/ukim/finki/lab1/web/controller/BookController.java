package mk.ukim.finki.lab1.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.lab1.model.Book;
import mk.ukim.finki.lab1.service.AuthorService;
import mk.ukim.finki.lab1.service.BookReservationService;
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
    private final BookReservationService bookReservationService;

    public BookController(BookService bookService,
                          AuthorService authorService,
                          BookReservationService bookReservationService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.bookReservationService = bookReservationService;
    }

    @GetMapping()
    public String getBooksPage(
            @RequestParam(required = false) String searchText,
            @RequestParam(required = false) Double rating,
            Model model
    ) {
        List<Book> books;
        if (rating != null) {
            books = bookService.searchBooksByRating(rating);
        } else if (searchText != null && !searchText.isBlank()) {
            books = bookService.searchBooks(searchText, null);
        } else {
            books = bookService.listAll();
        }
        model.addAttribute("books", books);
        return "listBooks";
    }

    @GetMapping("/book-form")
    public String getAddBookPage(Model model){
        model.addAttribute("authors",authorService.findAll());
        return "book-form";
    }

    @GetMapping("/book-form/{id}")
    public String getEditBookPage(@PathVariable Long id, Model model){
        model.addAttribute("book", bookService.findById(id));
        model.addAttribute("authors", authorService.findAll());
        return "book-form";
    }

//    @PostMapping("/edit/{id}")
//    public String editBook(@PathVariable Long id,
//                           @RequestParam String title,
//                           @RequestParam String genre,
//                           @RequestParam Double averageRating,
//                           @RequestParam Long authorId) {
//        bookService.update(id, title, genre, averageRating, authorId);
//        return "redirect:/books";
//    }
//
//    @GetMapping("/add-form")
//    public String addBookPage(Model model){
//        model.addAttribute("authors",authorService.findAll());
//        return "book-form";
//    }
//
//    @PostMapping
//    public String saveBook(@RequestParam String title,
//                           @RequestParam String genre,
//                           @RequestParam Double averageRating,
//                           @RequestParam Long authorId){
//        bookService.create(title, genre, averageRating, authorId);
//        return "redirect:/books";
//    }

    @PostMapping("/add")
    public String addOrEditBook(
            @RequestParam(required = false) Long id,
            @RequestParam String title,
            @RequestParam String genre,
            @RequestParam Double averageRating,
            @RequestParam Long authorId) {

        if (id != null) {
            bookService.update(id, title, genre, averageRating, authorId);
        } else {
            bookService.create(title, genre, averageRating, authorId);
        }

        return "redirect:/books";
    }

    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/books";
    }
}
