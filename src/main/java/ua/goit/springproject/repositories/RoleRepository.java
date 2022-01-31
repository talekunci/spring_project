package ua.goit.springproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.goit.springproject.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role getByName(String name);

}
