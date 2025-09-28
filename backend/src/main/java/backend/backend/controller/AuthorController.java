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

import backend.backend.model.Author;
import backend.backend.repository.AuthorRepository;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/authors")
@AllArgsConstructor
public class AuthorController {

    private final AuthorRepository authorRepository;

    @GetMapping
    public List<Author> list(){
        return authorRepository.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<Author> createAuthor(@RequestBody Author author){
        Author savedAuthor = authorRepository.save(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAuthor);    
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthor(@PathVariable Long id){
        Optional<Author> author = authorRepository.findById(id);
        return author.map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author authorDetails){
        Optional<Author> selectedAuthor = authorRepository.findById(id);
        if(selectedAuthor.isPresent()){
            Author author = selectedAuthor.get();
            author.setName(authorDetails.getName());

            Author updatedAuthor = authorRepository.save(author);
            return ResponseEntity.ok(updatedAuthor);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id){
        if (authorRepository.existsById(id)){
            authorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
