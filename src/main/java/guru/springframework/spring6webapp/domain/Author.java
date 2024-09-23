package guru.springframework.spring6webapp.domain;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe `Author` che rappresenta un autore nel sistema.
 * È una entità JPA che viene mappata a una tabella nel database.
 */
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  // Campo 'id' è la chiave primaria, generata automaticamente.
    private Long id;
    private String firstName;  // Campo che rappresenta il nome dell'autore.
    private String lastName;   // Campo che rappresenta il cognome dell'autore.

    // Relazione ManyToMany con la classe Book. `mappedBy = "authors"` indica che questa entità
    // è il lato inverso della relazione gestita dalla proprietà 'authors' nella classe `Book`.
    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();  // Inizializzazione di `books` come HashSet per evitare null pointer.

    // Getter per il set di libri associati all'autore.
    public Set<Book> getBooks() {
        return books;
    }

    // Setter per il set di libri associati all'autore.
    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    // Getter per l'id dell'autore.
    public Long getId() {
        return id;
    }

    // Setter per l'id dell'autore.
    public void setId(Long id) {
        this.id = id;
    }

    // Getter per il nome dell'autore.
    public String getFirstName() {
        return firstName;
    }

    // Setter per il nome dell'autore.
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Getter per il cognome dell'autore.
    public String getLastName() {
        return lastName;
    }

    // Setter per il cognome dell'autore.
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Override del metodo toString per una rappresentazione stringa dell'oggetto `Author`.
    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", books=" + books +
                '}';
    }

    // Override del metodo equals per confrontare oggetti `Author`.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;  // Verifica se i due oggetti sono lo stesso oggetto in memoria.
        if (!(o instanceof Author)) return false;  // Controlla se `o` è un'istanza di `Author`.

        Author author = (Author) o;

        return getId() != null ? getId().equals(author.getId()) : author.getId() == null;  // Confronta gli ID degli autori.
    }

    // Override del metodo hashCode per generare un hash basato sull'id dell'autore.
    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
