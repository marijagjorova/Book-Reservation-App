package mk.ukim.finki.lab1.service.impl;

import mk.ukim.finki.lab1.model.BookReservation;
import mk.ukim.finki.lab1.repository.jpa.BookReservationRepository;
import mk.ukim.finki.lab1.repository.mock.InMemoryBookReservationRepository;
import mk.ukim.finki.lab1.service.BookReservationService;
import org.springframework.stereotype.Service;

@Service
public class BookReservationServiceImpl implements BookReservationService {

    private final BookReservationRepository bookReservationRepository;

    public BookReservationServiceImpl(BookReservationRepository bookReservationRepository) {
        this.bookReservationRepository = bookReservationRepository;
    }

    @Override
    public BookReservation placeReservation(String bookTitle, String readerName, String readerAddress, int numberOfCopies) {
        if(bookTitle == null || bookTitle.isEmpty() || readerName == null || readerName.isEmpty() || readerAddress == null || readerAddress.isEmpty()){
            throw  new IllegalArgumentException();
        }
        BookReservation reservation = new BookReservation(bookTitle, readerName, readerAddress, (long) numberOfCopies);
        return this.bookReservationRepository.save(reservation);

    }
}
