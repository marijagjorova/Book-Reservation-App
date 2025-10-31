package mk.ukim.finki.lab1.repository.impl;

import mk.ukim.finki.lab1.bootstrap.DataHolder;
import mk.ukim.finki.lab1.model.BookReservation;
import mk.ukim.finki.lab1.repository.BookReservationRepository;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryBookReservationRepository implements BookReservationRepository {

    @Override
    public BookReservation save(BookReservation bookReservation){
        DataHolder.reservations.add(bookReservation);
        return bookReservation;
    }

}
