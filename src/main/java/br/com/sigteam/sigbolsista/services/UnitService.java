package br.com.sigteam.sigbolsista.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.sigteam.sigbolsista.models.Unit;
import br.com.sigteam.sigbolsista.repositories.UnitRepository;

@Repository
public class UnitService {
	
	@Autowired
	private UnitRepository unitRepository;

	
	public List<Unit> findByName(String name) {
		return unitRepository.findByName(name);
	}

	public void save(Unit unit) {
		unitRepository.save(unit);
	}
	
	@Transactional
	public List<Unit> saveAll(List<Unit> units) {
		List<Unit> response = (List<Unit>) unitRepository.saveAll(units);
		return response;
	}

	public Optional<Unit> findById(Long id) {
		return unitRepository.findById(id);
	}

	public boolean existsById(Long id) {
		return unitRepository.existsById(id);
	}

	public Iterable<Unit> findAll() {
		return unitRepository.findAll();
	}

	public Iterable<Unit> findAllById(Iterable<Long> ids) {
		return unitRepository.findAllById(ids);
	}

	public long count() {
		return unitRepository.count();
	}

	public void deleteById(Long id) {
		unitRepository.deleteById(id);
	}

	public void delete(Unit entity) {
		unitRepository.delete(entity);
	}

	public void deleteAllById(List<Long> ids) {
		unitRepository.deleteAllById(ids);
	}

	public void deleteAll(List<Unit> entities) {
		unitRepository.deleteAll(entities);
	}

	public void deleteAll() {
		unitRepository.deleteAll();
	}
	
	

}
