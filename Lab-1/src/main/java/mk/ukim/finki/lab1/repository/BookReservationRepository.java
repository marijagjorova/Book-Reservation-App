package mk.ukim.finki.lab1.repository;

import mk.ukim.finki.lab1.model.BookReservation;


public interface BookReservationRepository {
    BookReservation save(BookReservation bookReservation);
}
