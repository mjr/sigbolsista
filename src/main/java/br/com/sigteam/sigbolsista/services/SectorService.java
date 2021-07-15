package br.com.sigteam.sigbolsista.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.sigteam.sigbolsista.models.Sector;
import br.com.sigteam.sigbolsista.repositories.SectorRepository;

@Repository
public class SectorService {

	
	private SectorRepository sectorRepository;

	public List<Sector> findByName(String name) {
		return sectorRepository.findByName(name);
	}

	public void save(Sector sector) {
		sectorRepository.save(sector);
	}

	public List<Sector> saveAll(List<Sector> sectors) {
		List<Sector> response = (List<Sector>) sectorRepository.saveAll(sectors);
		return response;
	}

	public Optional<Sector> findById(Long id) {
		return sectorRepository.findById(id);
	}

	public boolean existsById(Long id) {
		return sectorRepository.existsById(id);
	}

	public Iterable<Sector> findAll() {
		return (List<Sector>) sectorRepository.findAll();
	}

	public List<Sector> findAllById(List<Long> ids) {
		return (List<Sector>) sectorRepository.findAllById(ids);
	}

	public long count() {
		return sectorRepository.count();
	}

	public void deleteById(Long id) {
		sectorRepository.deleteById(id);
	}

	public void delete(Sector entity) {
		sectorRepository.delete(entity);
	}

	public void deleteAllById(List<Long> ids) {
		sectorRepository.deleteAllById(ids);
	}

	public void deleteAll(List<Sector> entities) {
		sectorRepository.deleteAll(entities);
	}

	public void deleteAll() {
		sectorRepository.deleteAll();
	}
	
	
}
