package mk.ukim.finki.lab1.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class BookNotFoundExeption extends RuntimeException{
    public BookNotFoundExeption(Long bookId){
        super(String.format("Book with id %d does not exist!", bookId));
    }
}
