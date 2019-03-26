package hh.swd20.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.swd20.bookstore.domain.Book;
import hh.swd20.bookstore.domain.BookRepository;

@RunWith(SpringRunner.class)
@DataJpaTest

public class BookRepositoryTest {
	@Autowired
	private BookRepository repository;
	
	@Test
	public void createNewBook() {
		Book book1 = new Book();
		repository.save(book1);
		assertThat(book1.getId()).isNotNull();
	}
	
	@Test
	public void deleteBook() {
		Book book2 = new Book();
		repository.save(book2);
		Long bookId = book2.getId();
		repository.deleteById(bookId);
		assertThat(repository.findById(bookId).equals(null));
	}
	
	@Test
	public void findByTitle() {
		List<Book> books = repository.findByTitle("Cooking by the book");
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getTitle()).isEqualTo("Cooking by the book");
	}
}
