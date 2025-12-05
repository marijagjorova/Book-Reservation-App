package mk.ukim.finki.lab1.repository.mock;

import mk.ukim.finki.lab1.model.Book;
import java.util.List;
import java.util.Optional;

public interface InMemoryBookRepository {
    Book save(Book book);
    void delete(Long bookId);
    List<Book> findAll();
    List<Book> searchBooks(String text, Double rating);

    Optional<Book> findById(Long id);

    void deleteById(Long id);
}
