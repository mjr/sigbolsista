package br.com.sigteam.sigbolsista.repositories;

import br.com.sigteam.sigbolsista.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
    User findByUsername(String username);
}
