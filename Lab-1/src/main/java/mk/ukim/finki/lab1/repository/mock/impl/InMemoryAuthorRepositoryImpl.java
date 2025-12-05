package mk.ukim.finki.lab1.repository.mock.impl;

import mk.ukim.finki.lab1.bootstrap.DataHolder;
import mk.ukim.finki.lab1.model.Author;
import mk.ukim.finki.lab1.repository.mock.InMemoryAuthorRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryAuthorRepositoryImpl implements InMemoryAuthorRepository {

    @Override
    public List<Author> findAll() {
        return DataHolder.authors;
    }

    @Override
    public Optional<Author> findById(Long id) {
        return DataHolder.authors.stream().filter(a -> a.getId().equals(id)).findFirst();
    }

    @Override
    public void delete(Long authorId) {
        DataHolder.authors.removeIf(a -> a.getId().equals(authorId));
    }

    @Override
    public void deleteById(Long id) {
        DataHolder.authors.removeIf(a -> a.getId().equals(id));
    }


    @Override
    public Author save(Author author) {
        delete(author.getId());
        DataHolder.authors.add(author);
        return author;
    }


}
