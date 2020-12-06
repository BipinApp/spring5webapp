package guru.springframework.spring5webapp;

import guru.springframework.spring5webapp.Repositories.AuthorRepository;
import guru.springframework.spring5webapp.Repositories.BookRepository;
import guru.springframework.spring5webapp.Repositories.PublisherRepository;
import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    @Autowired
    public Bootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher publisher = new Publisher("Bipin", "400 Sairs", "Long Branch", "NJ", "07740");
        publisherRepository.save(publisher);

        Author eric  =  new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "1232321");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);
        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(publisher);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book (" J2EE Development without EJB", "3439989384");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        publisher.getBooks().add(noEJB);
        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(publisher);




        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books " + bookRepository.count());
        System.out.println("Number of Publisher " + publisherRepository.count());
    }
}
