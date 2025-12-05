package mk.ukim.finki.lab1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String genre;
    private double averageRating;

    @ManyToOne
    private Author author;

    public Book(String title, String genre, double averageRating, Author author) {
        this.title = title;
        this.genre = genre;
        this.averageRating = averageRating;
        this.author = author;
    }

    public Book(){

    }

    public String getTitle() {
        return title;
    }

    public Long getId() {
        return id;
    }

    public String getGenre() {
        return genre;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public Author getAuthor() {
        return author;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }


}
