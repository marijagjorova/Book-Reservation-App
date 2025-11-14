package mk.ukim.finki.lab1.repository;

import mk.ukim.finki.lab1.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    List<Author> findAll();

    Optional<Author> findById (Long id);

    Author save(Author author);
    void delete(Long authorId);

    void deleteById(Long id);
}
