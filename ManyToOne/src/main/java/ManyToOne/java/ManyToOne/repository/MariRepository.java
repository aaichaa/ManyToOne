package ManyToOne.java.ManyToOne.repository;

import ManyToOne.java.ManyToOne.model.Mari;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MariRepository extends JpaRepository<Mari, Integer> {
}
