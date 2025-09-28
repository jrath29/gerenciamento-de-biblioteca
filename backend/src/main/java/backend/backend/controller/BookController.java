package backend.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.backend.model.Book;
import backend.backend.repository.BookRepository;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {

    private final BookRepository bookRepository;

    @GetMapping
    public List<Book> list(){
        return bookRepository.findAll();
    }

    @GetMapping("/{name}")
    public List<Book> getByName(@PathVariable String name){
        return bookRepository.findByName(name);
    }

    @PostMapping("/create")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        Book savedBook = bookRepository.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book detailsBook){
        Optional<Book> selectedBook = bookRepository.findById(id);
        if(selectedBook.isPresent()){
            Book book = selectedBook.get();

            book.setName(detailsBook.getName());
            book.setAuthor(detailsBook.getAuthor());
            book.setCategory(detailsBook.getCategory());

            Book updatedBook = bookRepository.save(book);
            return ResponseEntity.ok(updatedBook);
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        if(bookRepository.existsById(id)){
            bookRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else{
            return ResponseEntity.notFound().build();
        }
    }
}
