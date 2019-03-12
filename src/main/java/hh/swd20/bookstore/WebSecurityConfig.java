package hh.swd20.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import hh.swd20.bookstore.web.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/login")
				.defaultSuccessUrl("/booklist").permitAll().and().logout().permitAll();
	}

	/*
	 * @Bean
	 * 
	 * @Override public UserDetailsService userDetailsService() { List<UserDetails>
	 * users = new ArrayList(); UserDetails user = User.withDefaultPasswordEncoder()
	 * .username("user") .password("password") .roles("USER") .build();
	 * users.add(user);
	 * 
	 * user = User.withDefaultPasswordEncoder() .username("admin")
	 * .password("password") .roles("USER", "ADMIN") .build(); users.add(user);
	 * 
	 * return new InMemoryUserDetailsManager(users); }
	 */

	@Autowired
	private UserDetailServiceImpl userDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

}
