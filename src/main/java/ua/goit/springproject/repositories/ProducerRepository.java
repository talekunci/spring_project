package ua.goit.springproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.goit.springproject.model.Producer;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long> {
}
