package guru.springframework.spring6webapp.services;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;

public interface AuthorService {

    Iterable<Author> findAll();
}
