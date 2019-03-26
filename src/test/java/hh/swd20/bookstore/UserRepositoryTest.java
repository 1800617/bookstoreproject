package hh.swd20.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.swd20.bookstore.domain.User;
import hh.swd20.bookstore.domain.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest

public class UserRepositoryTest {
	@Autowired
	private UserRepository repository;

	@Test
	public void createNewUser() {
		User user = new User("pinkitsukat", "$2a$04$d9XYSly5G3NAyy848j6O4Ou8q39qo3rixvLjnbV.igbzIdSlumIIu", "pinkit@sukat.com", "USER");
		repository.save(user);
		assertThat(user.getId()).isNotNull();
	}

	@Test
	public void deleteUser() {
		User user = new User("pinkitsukat", "$2a$04$d9XYSly5G3NAyy848j6O4Ou8q39qo3rixvLjnbV.igbzIdSlumIIu", "pinkit@sukat.com", "USER");
		repository.save(user);
		Long userId = user.getId();
		repository.deleteById(userId);
		assertThat(repository.findById(userId).equals(null));
	}

	@Test
	public void findByUsername() {

		User user = repository.findByUsername("user");
		assertThat(user.getUsername()).isEqualTo("user");
	}
}
