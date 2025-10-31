package mk.ukim.finki.lab1.model;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class BookReservation {

    private String bookTitle;
    private String readerName;
    private String readerAddress;
    private Long numberOfCopies;

    public BookReservation() {
    }


    public BookReservation(String bookTitle, String readerName, String readerAddress, Long numberOfCopies) {
        this.bookTitle = bookTitle;
        this.readerName = readerName;
        this.readerAddress = readerAddress;
        this.numberOfCopies = numberOfCopies;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }

    public String getReaderAddress() {
        return readerAddress;
    }

    public void setReaderAddress(String readerAddress) {
        this.readerAddress = readerAddress;
    }

    public Long getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(Long numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }
}