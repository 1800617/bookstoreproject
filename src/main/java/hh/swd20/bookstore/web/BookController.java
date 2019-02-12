package hh.swd20.bookstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.swd20.bookstore.domain.Book;
import hh.swd20.bookstore.domain.BookRepository;

@Controller
public class BookController {
	
	@Autowired
	private BookRepository bookRepository;
	
	// kirjalistaus
	@RequestMapping(value = "/booklist", method = RequestMethod.GET)
	public String getBooks(Model model) {
		List<Book> books = (List<Book>) bookRepository.findAll();
		model.addAttribute("books", books);
		return "booklist";
	}
	
	// tyhjä lomake
	@GetMapping("/addbook")
	public String addbook(Model model) {
		model.addAttribute("book", new Book()); // tyhjä kirja-olio
		return "addbook";
	}
	
	// tietojen vastaanotto ja tallennus
	@PostMapping("/addbook")
	public String savebook(@ModelAttribute Book book) {
		bookRepository.save(book);
		return "redirect:/booklist";
	}
	
	// kirjan poisto
	@GetMapping("/deletebook/{id}")
	public String deleteBook(@PathVariable("id") Long bookId) {
		bookRepository.deleteById(bookId);
		return "redirect:../booklist";
	}
	
	// kirjan tietojen muuttaminen
	@RequestMapping(value= "/editbook/{id}")
	public String editBook(@PathVariable("id") Long bookId, Model model){
		model.addAttribute("book", bookRepository.findById(bookId));
		return"editbook";
	}

}
