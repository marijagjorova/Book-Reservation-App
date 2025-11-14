package mk.ukim.finki.lab1.service.impl;

import mk.ukim.finki.lab1.bootstrap.DataHolder;
import mk.ukim.finki.lab1.model.Author;
import mk.ukim.finki.lab1.model.Book;
import mk.ukim.finki.lab1.model.exceptions.BookNotFoundExeption;
import mk.ukim.finki.lab1.repository.AuthorRepository;
import mk.ukim.finki.lab1.repository.BookRepository;
import mk.ukim.finki.lab1.repository.BookReservationRepository;
import mk.ukim.finki.lab1.service.AuthorService;
import mk.ukim.finki.lab1.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;


    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public List<Book> listAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        return bookRepository.searchBooks(text, rating);
    }

    @Override
    public Book findById(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundExeption(bookId));
    }

    @Override
    public Book create(String title, String genre, Double averageRating, Long authorId) {
        if (title == null || title.isEmpty() || genre == null || genre.isEmpty() || averageRating == null || authorId == null) {
            throw new IllegalArgumentException();
        }

        Author author = authorService.findById(authorId);
        Book book = new Book(title, genre, averageRating, author);
        return bookRepository.save(book);
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book update(Long bookId, String title, String genre, Double averageRating, Long authorId) {
        if(bookId == null || title == null || title.isEmpty() || genre == null || genre.isEmpty() || averageRating == null || authorId == null){
            throw new IllegalArgumentException();
        }

        Book book = findById(bookId);
        Author author = authorService.findById(authorId);

        book.setTitle(title);
        book.setGenre(genre);
        book.setAverageRating(averageRating);
        book.setAuthor(author);
        return bookRepository.save(book);
    }
}
