package br.com.sigteam.sigbolsista.repositories;

import br.com.sigteam.sigbolsista.models.Justification;
import br.com.sigteam.sigbolsista.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface JustificationRepository extends CrudRepository<Justification, Long> {
    List<Justification> findByUser(User user);

    Optional<Justification> findByIdAndUser(Long id, User user);
}
