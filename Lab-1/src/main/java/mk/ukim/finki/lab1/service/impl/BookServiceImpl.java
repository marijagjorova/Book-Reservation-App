package mk.ukim.finki.lab1.service.impl;

import mk.ukim.finki.lab1.model.Book;
import mk.ukim.finki.lab1.repository.BookRepository;
import mk.ukim.finki.lab1.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> listAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        return bookRepository.searchBooks(text, rating);
    }
}
