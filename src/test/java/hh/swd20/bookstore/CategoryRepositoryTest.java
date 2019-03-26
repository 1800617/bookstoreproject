package hh.swd20.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.swd20.bookstore.domain.Category;
import hh.swd20.bookstore.domain.CategoryRepository;

@RunWith(SpringRunner.class)
@DataJpaTest

public class CategoryRepositoryTest {
	@Autowired
	private CategoryRepository repository;
	
	@Test
	public void createNewCategory() {
		Category category = new Category("Tietokirjat");
		repository.save(category);
		assertThat(category.getCategoryId()).isNotNull();
	}
	
	@Test
	public void deleteCategory() {
		Category category = new Category();
		repository.save(category);
		Long categoryId = category.getCategoryId();
		repository.deleteById(categoryId);
		assertThat(repository.findById(categoryId).equals(null));
	}
	
	@Test
	public void findByCategoryName() {
		List<Category> categories = repository.findByName("Keittokirja");
		assertThat(categories).hasSize(1);
		assertThat(categories.get(0).getName()).isEqualTo("Keittokirja");
	}
}
