package mk.ukim.finki.lab1.service.impl;

import mk.ukim.finki.lab1.model.BookReservation;
import mk.ukim.finki.lab1.repository.BookReservationRepository;
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
        BookReservation reservation = new BookReservation(bookTitle, readerName, readerAddress, (long) numberOfCopies);
        return bookReservationRepository.save(reservation);
    }
}
