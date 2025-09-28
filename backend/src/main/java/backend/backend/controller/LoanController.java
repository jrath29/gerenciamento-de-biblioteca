package backend.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import backend.backend.model.Loan;
import backend.backend.repository.LoanRepository;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/loans")
@AllArgsConstructor
public class LoanController {
    private final LoanRepository loanRepository;

    @GetMapping
    public List<Loan> list(){
        return loanRepository.findAll();
    }

    @GetMapping("/{user_id}")
    public List<Loan> getByUserId(@PathVariable long user_id){
        return loanRepository.findByUserId(user_id);
    }

    @PostMapping("/create")
    public ResponseEntity<Loan> createLoan(@RequestBody Loan loan){
        Loan savedLoan = loanRepository.save(loan);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLoan);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable long id){
        if(loanRepository.existsById(id)){
            loanRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
