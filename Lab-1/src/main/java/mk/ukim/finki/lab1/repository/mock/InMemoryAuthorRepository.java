package mk.ukim.finki.lab1.repository.mock;

import mk.ukim.finki.lab1.model.Author;

import java.util.List;
import java.util.Optional;

public interface InMemoryAuthorRepository {

    List<Author> findAll();

    Optional<Author> findById (Long id);

    Author save(Author author);
    void delete(Long authorId);

    void deleteById(Long id);
}
