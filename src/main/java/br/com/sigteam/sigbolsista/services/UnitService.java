package br.com.sigteam.sigbolsista.services;

import br.com.sigteam.sigbolsista.models.Role;
import br.com.sigteam.sigbolsista.models.Unit;
import br.com.sigteam.sigbolsista.models.User;
import br.com.sigteam.sigbolsista.repositories.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class UnitService {
    @Autowired
    private UnitRepository unitRepository;

    public Iterable<Unit> all() {
        return unitRepository.findAll();
    }

    public Optional<Unit> getById(Long id) {
        return unitRepository.findById(id);
    }

    public void save(Unit unit) {
        unitRepository.save(unit);
    }

    public void delete(Unit unit) {
        unitRepository.delete(unit);
    }

	public boolean checkRole(User user) {
		List<Integer> roles =  Arrays.asList(Role.ADMIN);
		return !roles.contains(user.getRole());
	}
}
