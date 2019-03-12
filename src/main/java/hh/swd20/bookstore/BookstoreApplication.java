package hh.swd20.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.swd20.bookstore.domain.Book;
import hh.swd20.bookstore.domain.BookRepository;
import hh.swd20.bookstore.domain.Category;
import hh.swd20.bookstore.domain.CategoryRepository;
import hh.swd20.bookstore.domain.User;
import hh.swd20.bookstore.domain.UserRepository;

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
	public CommandLineRunner bookDemo(BookRepository bookRepository, CategoryRepository categoryRepository,
			UserRepository urepository) {
		return (args) -> {
			log.info("save categories");
			categoryRepository.save(new Category("Keittokirja"));
			categoryRepository.save(new Category("Muu kuin keittokirja"));

			log.info("save books");
			bookRepository.save(new Book("Cooking by the book", "Lazytown", 1996, "404123-23", 42,
					categoryRepository.findByName("Keittokirja").get(0)));
			bookRepository.save(new Book("How to bake a pretty cake", "Grandma", 1974, "123123-23", 15,
					categoryRepository.findByName("Keittokirja").get(0)));
			bookRepository.save(new Book("How to come up with stuff and things", "That guy", 2009, "14767-242", 65,
					categoryRepository.findByName("Muu kuin keittokirja").get(0)));

			// Create users: admin/admin user/user
			User user1 = new User("user", "$2a$04$d9XYSly5G3NAyy848j6O4Ou8q39qo3rixvLjnbV.igbzIdSlumIIu", "user@user.com", "USER");
			User user2 = new User("admin", "$2a$04$RpRU0Ur7S0hRo5GI5kri2OaFB.BiqocH0gykCtA8dAgqSLTHHYWA6", "admin@admin.com", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);

			log.info("fetch all books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}
		};
	}

}
