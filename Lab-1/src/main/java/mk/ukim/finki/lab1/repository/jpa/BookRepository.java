package mk.ukim.finki.lab1.repository.jpa;

import mk.ukim.finki.lab1.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByAuthor_Id(Long authorId);
    List<Book> findByTitleContainingIgnoreCaseOrAverageRatingGreaterThanEqual(String text, Double rating);
}

