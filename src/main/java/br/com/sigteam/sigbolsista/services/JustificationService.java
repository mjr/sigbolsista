package br.com.sigteam.sigbolsista.services;

import br.com.sigteam.sigbolsista.models.Justification;
import br.com.sigteam.sigbolsista.models.User;
import br.com.sigteam.sigbolsista.repositories.JustificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JustificationService {
    @Autowired
    private JustificationRepository justificationRepository;

    public Iterable<Justification> all() {
        return justificationRepository.findAll();
    }

    public Optional<Justification> getById(Long id) {
        return justificationRepository.findById(id);
    }

    public Optional<Justification> getByIdAndUser(Long id, User user) {
        return justificationRepository.findByIdAndUser(id, user);
    }

    public Iterable<Justification> filterByUser(User user) {
        return justificationRepository.findByUser(user);
    }

    public void save(Justification justification) {
        justificationRepository.save(justification);
    }

    public void delete(Justification justification) {
        justificationRepository.delete(justification);
    }
}
