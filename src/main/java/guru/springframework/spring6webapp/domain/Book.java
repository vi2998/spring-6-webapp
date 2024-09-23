package guru.springframework.spring6webapp.domain;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe `Book` che rappresenta un libro nel sistema.
 * È una entità JPA che viene mappata a una tabella nel database.
 */
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  // Campo 'id' è la chiave primaria, generata automaticamente dal database.
    private Long id;
    private String title;   // Campo che rappresenta il titolo del libro.
    private String isbn;    // Campo che rappresenta il codice ISBN del libro.

    // Relazione ManyToMany tra libro e autore, con una tabella di join intermedia chiamata "author_book".
    @ManyToMany
    @JoinTable(
            name = "author_book",  // Nome della tabella di join.
            joinColumns = @JoinColumn(name = "book_id"),  // Colonna che fa riferimento alla chiave primaria del libro.
            inverseJoinColumns = @JoinColumn(name = "author_id")  // Colonna che fa riferimento alla chiave primaria dell'autore.
    )
    private Set<Author> authors = new HashSet<>();  // Inizializzazione di `authors` come HashSet per evitare null pointer.

    // Relazione ManyToOne tra libro e publisher (editore), un editore può avere più libri.
    @ManyToOne
    private Publisher publisher;

    // Getter per il publisher
    public Publisher getPublisher() {
        return publisher;
    }

    // Setter per il publisher
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    // Getter per la lista di autori associati al libro.
    public Set<Author> getAuthors() {
        return authors;
    }

    // Setter per la lista di autori associati al libro.
    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    // Getter per l'id del libro.
    public Long getId() {
        return id;
    }

    // Setter per l'id del libro.
    public void setId(Long id) {
        this.id = id;
    }

    // Getter per il titolo del libro.
    public String getTitle() {
        return title;
    }

    // Setter per il titolo del libro.
    public void setTitle(String title) {
        this.title = title;
    }

    // Getter per l'ISBN del libro.
    public String getIsbn() {
        return isbn;
    }

    // Setter per l'ISBN del libro.
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    // Override del metodo toString per una rappresentazione stringa dell'oggetto `Book`.
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", authors=" + authors +
                '}';
    }

    // Override del metodo equals per confrontare oggetti `Book`.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;  // Verifica se i due oggetti sono lo stesso oggetto in memoria.
        if (!(o instanceof Book)) return false;  // Controlla se `o` è un'istanza di `Book`.

        Book book = (Book) o;

        return getId() != null ? getId().equals(book.getId()) : book.getId() == null;  // Confronta gli ID dei due libri.
    }

    // Override del metodo hashCode per generare un hash basato sull'id del libro.
    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
