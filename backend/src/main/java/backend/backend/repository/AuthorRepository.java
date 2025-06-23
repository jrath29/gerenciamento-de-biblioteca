package backend.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.backend.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>{

}
