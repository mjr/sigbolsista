package br.com.sigteam.sigbolsista.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sigteam.sigbolsista.models.Goal;
import br.com.sigteam.sigbolsista.models.Role;
import br.com.sigteam.sigbolsista.models.Sector;
import br.com.sigteam.sigbolsista.models.Unit;
import br.com.sigteam.sigbolsista.models.User;
import br.com.sigteam.sigbolsista.repositories.GoalRepository;

@Service
public class GoalService {
	
	@Autowired
	private GoalRepository goalRepository;
	
	
	public List<Goal> getAll() {
		return (List<Goal>) goalRepository.findAll();
	}
	
	public void save(Goal goal) {
		goalRepository.save(goal);
	}
	
	public void delete(Goal goal) {
		goalRepository.delete(goal);
	}
	
	public Optional<Goal> getById(Long id) {
		return goalRepository.findById(id);
	}
	
	public List<Goal> findByUser(User user){
		return goalRepository.findByUser(user);
	}
	
	public Boolean validateInputs(Goal goal) {
		
		if(goal.getDateBegin() == null)
			return false;
		if(goal.getDateEnd() == null)
			return false;
		if(goal.getDescription() == null || goal.getDescription().length() <= 1)
			return false;
		if(goal.getDateBegin().isAfter(goal.getDateEnd()))
			return false;
		
		return true;
	}
	
	public boolean checkRole(User user) {
		List<Integer> roles =  Arrays.asList(Role.ADMIN, Role.SECTOR_MANAGER, Role.UNIT_MANAGER, Role.COLLEGER);
		return !roles.contains(user.getRole());
	}



	public List<Goal> findByUnit(Unit unit) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object findBySector(Sector sector) {
		// TODO Auto-generated method stub
		return null;
	}
}
