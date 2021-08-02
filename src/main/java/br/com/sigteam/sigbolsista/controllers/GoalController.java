package br.com.sigteam.sigbolsista.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import br.com.sigteam.sigbolsista.models.Goal;
import br.com.sigteam.sigbolsista.models.User;
import br.com.sigteam.sigbolsista.services.GoalService;

@Controller
public class GoalController{

	@Autowired
	private GoalService goalService;
	
	
	private static final String GOAL_LIST  = "goal/goal-list";
	private static final String GOAL_FORM  = "goal/goal-form";
	private static final String REDIRECT   = "redirect:/goal";
	private static final String GOAL_UPDATE = "goal/goal-update";
	private static final String DASHBOARD = "redirect:/dashboard";
	 

	
	@RequestMapping(value = "/goal", method = RequestMethod.GET)
	public String list(Model model, @AuthenticationPrincipal User user) {
		if(goalService.checkRole(user))
			return DASHBOARD;
		
		return redirectToRole(user.getRole(), model, user);
	}
	
	//Refactor to improve code quality
	private String redirectToRole(int role, Model model, User user){
		if(role == 1) {
			model.addAttribute("goals", goalService.getAll());
			return GOAL_LIST;
		}
		if(role == 2) {
			model.addAttribute("goals", goalService.findByUser(user));
			return GOAL_LIST;
		}
		if(role == 3) {
			model.addAttribute("goals", goalService.findBySector(user.getSector()));
			return GOAL_LIST;
		}
		if(role == 4) {
			model.addAttribute("goals", goalService.findByUnit(user.getSector().getUnit()));
			return GOAL_LIST;
		}
		
		return DASHBOARD;
	}
	
	
	@RequestMapping(value = "/goal/add", method = RequestMethod.GET)
	public String emptyForm(Model model, @AuthenticationPrincipal User user) {
		if(goalService.checkRole(user))
			return DASHBOARD;
		
		model.addAttribute("goal", new Goal());
		return GOAL_FORM;
	}
	
	
	@RequestMapping(value = "/goal/add", method = RequestMethod.POST)
	public String create(Goal goal, BindingResult bindingResult, @AuthenticationPrincipal User user) {
		if(goalService.checkRole(user))
			return DASHBOARD;
		
		if(bindingResult.hasErrors()) {
			return GOAL_FORM;
		}
		
		if(!goalService.validateInputs(goal)) {
			return GOAL_FORM;
		}
		
		goal.setUser(user);
		goalService.save(goal);
		return REDIRECT;
	}
	
	
	@RequestMapping(value = "/goal/{id}/change", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal User user) {
		if(goalService.checkRole(user))
			return DASHBOARD;
		
		Optional<Goal> goal = goalService.getById(id);
		model.addAttribute("goal", goal.get());
		return GOAL_UPDATE;
	}
	
	@RequestMapping(value = "/goal/{id}/change", method = RequestMethod.POST)
	public String update(@PathVariable("id") Long id, Goal goal, BindingResult bindingResult, @AuthenticationPrincipal User user) {
		if(goalService.checkRole(user))
			return DASHBOARD;		
		
		if(bindingResult.hasErrors()) {
			return GOAL_UPDATE;
		}
		
		if(!goalService.validateInputs(goal)) {
			return GOAL_FORM;
		}
		
		goal.setId(id);
		goal.setUser(user);
		goalService.save(goal);
		return REDIRECT;
	}
	
	@RequestMapping(value = "/goal/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Long id, @AuthenticationPrincipal User user) {
		if(goalService.checkRole(user))
			return DASHBOARD;
		
		Optional<Goal> goal = goalService.getById(id);
		if(!goal.isPresent()) {
			return REDIRECT;
		}
		goalService.delete(goal.get());
		return REDIRECT;
	}

	
}

























