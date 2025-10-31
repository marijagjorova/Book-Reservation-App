package mk.ukim.finki.lab1.repository;

import mk.ukim.finki.lab1.model.Book;
import java.util.List;

public interface BookRepository {
    List<Book> findAll();
    List<Book> searchBooks(String text, Double rating);
}
