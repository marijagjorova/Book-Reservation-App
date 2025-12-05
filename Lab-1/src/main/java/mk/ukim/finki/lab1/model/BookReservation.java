package mk.ukim.finki.lab1.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class BookReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bookTitle;
    private String readerName;
    private String readerAddress;
    private Long numberOfCopies;


    public BookReservation(String bookTitle, String readerName, String readerAddress, Long numberOfCopies) {
        this.bookTitle = bookTitle;
        this.readerName = readerName;
        this.readerAddress = readerAddress;
        this.numberOfCopies = numberOfCopies;
    }


    public String getReaderName() {
        return readerName;
    }

    public String getReaderAddress() {
        return readerAddress;
    }

    public Long getNumberOfCopies() {
        return numberOfCopies;
    }

    public String getBookTitle() {
        return bookTitle;
    }

}