package br.com.sigteam.sigbolsista.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.sigteam.sigbolsista.models.Goal;
import br.com.sigteam.sigbolsista.models.Sector;
import br.com.sigteam.sigbolsista.models.User;

@Repository
public interface GoalRepository extends CrudRepository<Goal, Long>{
	List<Goal> findByUser(User user);
	
	@Query(value=
			"SELECT * "
			+ "FROM author a where 1=1", 
			nativeQuery=true)
    List<Goal> findByUserAndSector(User user, Sector sector);
}
