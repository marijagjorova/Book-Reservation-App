package mk.ukim.finki.lab1.service;

import mk.ukim.finki.lab1.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
    Author findById(Long id);
}
