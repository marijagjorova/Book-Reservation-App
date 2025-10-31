package mk.ukim.finki.lab1.service;

import mk.ukim.finki.lab1.model.BookReservation;

public interface BookReservationService {

    BookReservation placeReservation(String bookTitle, String readerName, String readerAddress, int numberOfCopies);

}
