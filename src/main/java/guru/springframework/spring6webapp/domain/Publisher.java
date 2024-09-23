package guru.springframework.spring6webapp.domain;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Classe `Publisher` che rappresenta un editore nel sistema.
 * È una entità JPA mappata a una tabella nel database.
 */
@Entity
public class Publisher {

    // ID generato automaticamente, è la chiave primaria della tabella Publisher.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Nome dell'editore
    private String publisherName;

    // Indirizzo, città, stato e CAP dell'editore.
    private String address;
    private String city;
    private String state;
    private String zip;

    // Relazione OneToMany con la classe `Book`. Un editore può pubblicare molti libri.
    // La proprietà `mappedBy = "publisher"` indica che la relazione è gestita dalla proprietà `publisher` nella classe `Book`.
    @OneToMany(mappedBy = "publisher")
    private Set<Book> books = new HashSet<>();  // Inizializza `books` per evitare problemi di null pointer.

    // Getter e setter per la collezione di libri associati all'editore.
    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    // Getter e setter per il CAP dell'editore.
    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    // Getter e setter per lo stato dell'editore.
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    // Getter e setter per la città dell'editore.
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    // Getter e setter per l'indirizzo dell'editore.
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Getter e setter per il nome dell'editore.
    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    // Getter e setter per l'ID dell'editore.
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Override del metodo `toString` per ottenere una rappresentazione stringa dell'oggetto Publisher.
    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", publisherName='" + publisherName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }

    // Override del metodo `equals` per confrontare due oggetti Publisher.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;  // Verifica se si tratta dello stesso oggetto in memoria.
        if (o == null || getClass() != o.getClass()) return false;  // Verifica che `o` non sia null e sia della classe `Publisher`.

        Publisher publisher = (Publisher) o;
        // Confronta gli ID degli editori per determinare l'uguaglianza.
        return Objects.equals(getId(), publisher.getId());
    }

    // Override del metodo `hashCode` per generare un hash basato sull'ID dell'editore.
    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
