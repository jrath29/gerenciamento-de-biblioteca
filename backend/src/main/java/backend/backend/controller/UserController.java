package backend.backend.controller;

import backend.backend.model.User;
import backend.backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping
    public List<User> list() {
        return userRepository.findAll();
    }

    @GetMapping("/{name}")
    public List<User> getByName(@PathVariable String name) {
        return userRepository.findByName(name);
    }

    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody User user){
        User savedUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails){
        Optional<User> selectedUser = userRepository.findById(id);
        if(selectedUser.isPresent()){
            User user = selectedUser.get();
            user.setName(userDetails.getName());
            user.setBirthDate(userDetails.getBirthDate());
            user.setEmail(userDetails.getEmail());
            user.setPhone(userDetails.getPhone());
            User updatedUser = userRepository.save(user);
            return ResponseEntity.ok(updatedUser);
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else{
            return ResponseEntity.notFound().build();
        }
    }
}
