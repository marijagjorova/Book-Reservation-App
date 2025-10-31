package mk.ukim.finki.lab1.service;

import mk.ukim.finki.lab1.model.Book;

import java.util.List;

public interface BookService {

    List<Book> listAll();
    List<Book> searchBooks(String text, Double rating);

}
