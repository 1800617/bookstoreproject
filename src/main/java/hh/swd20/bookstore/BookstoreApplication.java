package hh.swd20.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.swd20.bookstore.domain.Book;
import hh.swd20.bookstore.domain.BookRepository;
import hh.swd20.bookstore.domain.Category;
import hh.swd20.bookstore.domain.CategoryRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	// testidatan luonti H2-testitietokantaan aina sovelluksen käynnistyessä
	@Bean
	public CommandLineRunner bookDemo(BookRepository bookRepository, CategoryRepository categoryRepository) {
		return (args) -> {
			log.info("save categories");
			categoryRepository.save(new Category("Keittokirja"));
			categoryRepository.save(new Category("Muu kuin keittokirja"));
			
			log.info("save books");
			bookRepository.save(new Book("Cooking by the book", "Lazytown", 1996, "404123-23", 42, categoryRepository.findByName("Keittokirja").get(0)));
			bookRepository.save(new Book("How to bake a pretty cake", "Grandma", 1974, "123123-23", 15, categoryRepository.findByName("Keittokirja").get(0)));
			bookRepository.save(new Book("How to come up with stuff and things", "That guy", 2009, "14767-242", 65, categoryRepository.findByName("Muu kuin keittokirja").get(0)));

			log.info("fetch all books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}
		};
	}

}
