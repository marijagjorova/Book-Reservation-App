package mk.ukim.finki.lab1.service;

import mk.ukim.finki.lab1.model.Author;
import mk.ukim.finki.lab1.model.Book;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
    Author findById(Long id);

    List<Author> listAll();

    Author update(Long authorId, String name, String surname, String country, String biography);
    void delete(Long id);

    Author create(String name, String surname, String country, String biography);
}
