package mk.ukim.finki.lab1.service.impl;
import mk.ukim.finki.lab1.model.Author;
import mk.ukim.finki.lab1.repository.jpa.AuthorRepository;
import mk.ukim.finki.lab1.repository.mock.InMemoryAuthorRepository;
import mk.ukim.finki.lab1.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {

        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new RuntimeException());
    }

    @Override
    public List<Author> listAll() {
        return  authorRepository.findAll();
    }

    @Override
    public Author update(Long authorId, String name, String surname, String country, String biography) {
        if(authorId == null || name == null || name.isEmpty() || surname == null || surname.isEmpty() || country == null || country.isEmpty() || biography == null || biography.isEmpty()){
            throw new IllegalArgumentException();
        }

        Author author = findById(authorId);

        author.setId(authorId);
        author.setName(name);
        author.setSurname(surname);
        author.setCountry(country);
        author.setBiography(biography);
        return authorRepository.save(author);
    }

    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public Author create(String name,
                         String surname,
                         String country,
                         String biography) {
        if(name == null || name.isEmpty()
                || surname == null || surname.isEmpty()
                || country == null || country.isEmpty()
                || biography == null || biography.isEmpty()){
            throw new IllegalArgumentException();
        }

        Author author = new Author( name, surname, country, biography);
        return authorRepository.save(author);
    }

    @Override
    public List<Author> getAllAuthorsSortedBySurname() {
        return authorRepository.findAllByOrderBySurname();
    }
}
