package mk.ukim.finki.lab1.bootstrap;
import jakarta.annotation.PostConstruct;
import mk.ukim.finki.lab1.model.Author;
import mk.ukim.finki.lab1.model.Book;
import mk.ukim.finki.lab1.model.BookReservation;
import mk.ukim.finki.lab1.repository.mock.InMemoryAuthorRepository;
import mk.ukim.finki.lab1.repository.mock.InMemoryBookRepository;
import mk.ukim.finki.lab1.repository.mock.InMemoryBookReservationRepository;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Book> books = new ArrayList<>();
    public static List<BookReservation> reservations = new ArrayList<>();
    public static List<Author> authors = new ArrayList<>();

    private final InMemoryBookRepository bookRepository;
    private final InMemoryBookReservationRepository bookReservationRepository;
    private final InMemoryAuthorRepository authorRepository;

    public DataHolder(InMemoryBookRepository bookRepository,
                      InMemoryBookReservationRepository bookReservationRepository,
                      InMemoryAuthorRepository authorRepository
    ) {
        this.bookRepository = bookRepository;
        this.bookReservationRepository = bookReservationRepository;
        this.authorRepository = authorRepository;
    }


    @PostConstruct
    public void init() {
        if (authorRepository.findAll().isEmpty()) {
            authors.add(new Author("J.K.", "Rowling", "United Kingdom", "British author, best known for Harry Potter."));
            authors.add(new Author("J.R.R.", "Tolkien", "United Kingdom", "Author of The Lord of the Rings."));
            authors.add(new Author("George", "Orwell", "United Kingdom", "Author of dystopian novels."));
        }

        if (bookRepository.findAll().isEmpty()) {
            books.add(new Book("Harry Potter", "Fantasy", 5.0, authors.get(0)));
            books.add(new Book("Lord of the Rings", "Fantasy", 3.2, authors.get(1)));
            books.add(new Book("1984", "Dystopian", 4.5, authors.get(2)));
            books.add(new Book("Project Hail Mary", "Science Fiction", 4.8, authors.get(2)));
            books.add(new Book("The Hitchhiker's Guide To The Galaxy", "Sci-Fi", 4.2, authors.get(1)));
            books.add(new Book("Brave New World", "Dystopian", 3.7, authors.get(2)));
            books.add(new Book("The Hobbit", "Fantasy", 2.0, authors.get(1)));
            books.add(new Book("Dune", "Science Fiction", 4.3, authors.get(2)));
            books.add(new Book("The Hunger Games", "Dystopian", 4.9, authors.get(2)));
            books.add(new Book("2001: A Space Odyssey", "Science Fiction", 4.5, authors.get(1)));
        }
    }
}
