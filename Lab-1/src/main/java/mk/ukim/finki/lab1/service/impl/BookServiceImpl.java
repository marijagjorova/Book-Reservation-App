package mk.ukim.finki.lab1.service.impl;

import mk.ukim.finki.lab1.model.Author;
import mk.ukim.finki.lab1.model.Book;
import mk.ukim.finki.lab1.model.exceptions.BookNotFoundExeption;
import mk.ukim.finki.lab1.repository.jpa.BookRepository;
import mk.ukim.finki.lab1.service.AuthorService;
import mk.ukim.finki.lab1.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;


    public BookServiceImpl(BookRepository bookRepository,
                           AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public List<Book> listAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        return bookRepository.findByTitleContainingIgnoreCaseOrAverageRatingGreaterThanEqual(text, rating);
    }

    @Override
    public List<Book> findAllByAuthor_Id(Long authorId){
        return bookRepository.findAllByAuthor_Id(authorId);
    }

    @Override
    public Book findById(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundExeption(bookId));
    }

    @Override
    public Book create(String title,
                       String genre,
                       Double averageRating,
                       Long authorId) {
        if (title == null || title.isEmpty() ||
                genre == null || genre.isEmpty() ||
                averageRating == null ||
                authorId == null) {
            throw new IllegalArgumentException();
        }

        Author author = authorService.findById(authorId);

        Book book = new Book (title, genre, averageRating,author);
        return bookRepository.save(book);
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> listAllFiltered(String filterName, Double filterRating) {
        return bookRepository.findAll().stream()
                .filter(book -> filterName == null || book.getTitle().toLowerCase().contains(filterName.toLowerCase()))
                .filter(book -> filterRating == null || book.getAverageRating() >= filterRating)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Book update(Long bookId,
                       String title,
                       String genre,
                       Double averageRating,
                       Long authorId) {
        if(bookId == null ||
                title == null || title.isEmpty() ||
                genre == null || genre.isEmpty() ||
                averageRating == null ||
                authorId == null){
            throw new IllegalArgumentException();
        }

        Book book = findById(bookId);
        Author author = authorService.findById(authorId);

        book.setTitle(title);
        book.setGenre(genre);
        book.setAverageRating(averageRating);
        book.setAuthor(author);
        return bookRepository.save(book);
    }
}
