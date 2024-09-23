package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Classe di bootstrap dei dati.
 * Viene eseguita all'avvio dell'applicazione per popolare il database con dati iniziali.
 */
@Component  // Indica a Spring che questa classe è un componente gestito dal framework.
public class BootstrapData implements CommandLineRunner {

    // Repository per salvare e recuperare dati di autori, libri e editori.
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    // Costruttore per iniettare le dipendenze (repository).
    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    /**
     * Metodo eseguito all'avvio dell'applicazione per popolare i dati.
     * Il parametro `args` contiene eventuali argomenti di avvio passati all'applicazione.
     */
    @Override
    public void run(String... args) throws Exception {
        // Crea un nuovo autore Eric Evans.
        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        // Crea un nuovo libro "Domain Driven Design" con ISBN 123456.
        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("123456");

        // Salva l'autore Eric e il libro nel repository (nel database).
        Author ericSaved = authorRepository.save(eric);
        Book dddSaved = bookRepository.save(ddd);

        // Crea un nuovo autore Rod Johnson.
        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");

        // Crea un nuovo libro "J2EE Development without EJB" con ISBN 54757585.
        Book noEJB = new Book();
        noEJB.setTitle("J2EE Development without EJB");
        noEJB.setIsbn("54757585");

        // Salva l'autore Rod e il libro nel repository (nel database).
        Author rodSaved = authorRepository.save(rod);
        Book noEJBSaved = bookRepository.save(noEJB);

        // Associa il libro "Domain Driven Design" all'autore Eric.
        ericSaved.getBooks().add(dddSaved);

        // Associa il libro "J2EE Development without EJB" all'autore Rod.
        rodSaved.getBooks().add(noEJBSaved);

        // Aggiunge gli autori ai rispettivi libri.
        dddSaved.getAuthors().add(ericSaved);
        noEJBSaved.getAuthors().add(rodSaved);

        // Crea un nuovo editore (Publisher).
        Publisher publisher = new Publisher();
        publisher.setPublisherName("My Publisher");
        publisher.setAddress("123 Main");

        // Salva l'editore nel repository (nel database).
        Publisher savedPublisher = publisherRepository.save(publisher);

        // Associa l'editore al libro "Domain Driven Design".
        dddSaved.setPublisher(savedPublisher);

        // Associa l'editore al libro "J2EE Development without EJB".
        noEJBSaved.setPublisher(savedPublisher);

        // Aggiorna le associazioni nei repository.
        authorRepository.save(ericSaved);
        authorRepository.save(rodSaved);
        bookRepository.save(dddSaved);
        bookRepository.save(noEJBSaved);

        // Stampa informazioni di debug nel terminale.
        System.out.println("In Bootstrap");
        System.out.println("Author Count: " + authorRepository.count()); // Stampa il numero totale di autori salvati.
        System.out.println("Book Count: " + bookRepository.count());     // Stampa il numero totale di libri salvati.
        System.out.println("Publisher Count: " + publisherRepository.count()); // Stampa il numero totale di editori salvati.
    }
}
