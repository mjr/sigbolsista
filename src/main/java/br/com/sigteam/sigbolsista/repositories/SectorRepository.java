package br.com.sigteam.sigbolsista.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.sigteam.sigbolsista.models.Sector;

public interface SectorRepository extends CrudRepository<Sector, Long>{
	List<Sector> findByName(String name);
}
