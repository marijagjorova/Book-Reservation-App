package mk.ukim.finki.lab1.repository.mock.impl;

import mk.ukim.finki.lab1.bootstrap.DataHolder;
import mk.ukim.finki.lab1.model.BookReservation;
import mk.ukim.finki.lab1.repository.mock.InMemoryBookReservationRepository;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryBookReservationRepositoryImpl implements InMemoryBookReservationRepository {

    @Override
    public BookReservation save(BookReservation bookReservation){
        DataHolder.reservations.add(bookReservation);
        return bookReservation;
    }

}
