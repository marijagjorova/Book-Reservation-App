package mk.ukim.finki.lab1.repository.mock;

import mk.ukim.finki.lab1.model.BookReservation;


public interface InMemoryBookReservationRepository {
    BookReservation save(BookReservation bookReservation);
}
