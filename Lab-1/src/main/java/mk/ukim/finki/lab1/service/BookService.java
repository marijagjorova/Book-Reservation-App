package mk.ukim.finki.lab1.service;

import mk.ukim.finki.lab1.model.Book;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import java.util.List;

public interface BookService {

    List<Book> listAll();
    List<Book> searchBooks(String text, Double rating);

    Book update(Long bookId,
                String title,
                String genre,
                Double averageRating,
                Long authorId);

    List<Book> findAllByAuthor_Id(Long authorId);

    Book findById(Long bookId);

    Book create(String title,
                String genre,
                Double averageRating,
                Long authorId);

    void delete(Long id);

    List<Book> listAllFiltered(String filterName, Double filterRating);
}
