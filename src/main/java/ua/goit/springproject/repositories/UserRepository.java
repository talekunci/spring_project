package ua.goit.springproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.goit.springproject.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String name);

}
