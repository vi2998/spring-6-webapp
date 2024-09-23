package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("123456");

        Author ericSaved = authorRepository.save(eric);
        Book dddSaved = bookRepository.save(ddd);

        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");

        Book noEJB = new Book();
        noEJB.setTitle("J2EE Development without EJB");
        noEJB.setIsbn("54757585");

        Author provaA = new Author();
        provaA.setFirstName("Nome");
        provaA.setLastName("Cognome");

        Book provaB = new Book();
        provaB.setTitle("Titolo");
        provaB.setIsbn("11111111");

        Publisher editore = new Publisher();
        editore.setPublisherName("Mondadori");
        editore.setAddress("Piazza Duomo");
        editore.setCity("Milano");
        editore.setState("Italy");
        editore.setZip("00000");

        // Salvataggio degli autori e dei libri
        Author rodSaved = authorRepository.save(rod);
        Book noEJBSaved = bookRepository.save(noEJB);

        // Salvataggio del publisher
        Publisher savedPublisher = publisherRepository.save(editore);

        // Associazione dei libri agli autori
        ericSaved.getBooks().add(dddSaved);
        rodSaved.getBooks().add(noEJBSaved);

        // Associazione dei libri al publisher
        savedPublisher.getBooks().add(dddSaved);
        savedPublisher.getBooks().add(noEJBSaved);

        // Salvataggio delle relazioni
        authorRepository.save(ericSaved);
        authorRepository.save(rodSaved);
        publisherRepository.save(savedPublisher); // Salvataggio del publisher

        System.out.println("In Bootstrap");
        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count: " + bookRepository.count());
        System.out.println("Publisher Count: " + publisherRepository.count()); // Aggiunto per contare i publisher
    }
}
