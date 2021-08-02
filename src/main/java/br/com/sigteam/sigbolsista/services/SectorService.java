package br.com.sigteam.sigbolsista.services;

import br.com.sigteam.sigbolsista.models.Role;
import br.com.sigteam.sigbolsista.models.Sector;
import br.com.sigteam.sigbolsista.models.User;
import br.com.sigteam.sigbolsista.repositories.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class SectorService {
    @Autowired
    private SectorRepository sectorRepository;

    public Iterable<Sector> all() {
        return sectorRepository.findAll();
    }

    public Optional<Sector> getById(Long id) {
        return sectorRepository.findById(id);
    }

    public void save(Sector sector) {
        sectorRepository.save(sector);
    }

    public void delete(Sector sector) {
        sectorRepository.delete(sector);
    }
    
	public boolean checkRole(User user) {
		List<Integer> roles =  Arrays.asList(Role.ADMIN, Role.UNIT_MANAGER);
		return !roles.contains(user.getRole());
	}
}
