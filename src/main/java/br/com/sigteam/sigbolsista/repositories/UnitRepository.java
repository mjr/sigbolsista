package br.com.sigteam.sigbolsista.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.sigteam.sigbolsista.models.Unit;

public interface UnitRepository extends CrudRepository<Unit, Long>{
	List<Unit> findByName(String name);
}
