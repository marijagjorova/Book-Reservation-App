package mk.ukim.finki.lab1.repository.impl;

import mk.ukim.finki.lab1.bootstrap.DataHolder;
import mk.ukim.finki.lab1.model.Book;
import mk.ukim.finki.lab1.repository.BookRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InMemoryBookRepository implements BookRepository {

    @Override
    public List<Book> findAll(){
        return DataHolder.books;
    }

    public List<Book> searchBooks(String text, Double rating){
        return DataHolder.books.stream().filter(b -> b.getTitle().toLowerCase().contains(text.toLowerCase()) && b.getAverageRating() >= rating).toList();
    }

}
