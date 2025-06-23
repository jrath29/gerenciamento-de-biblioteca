package backend.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.backend.model.Book;
import backend.backend.repository.BookRepository;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {

    private BookRepository bookRepository;

    @GetMapping
    public List<Book> list(){
        return bookRepository.findAll();
    }
}
