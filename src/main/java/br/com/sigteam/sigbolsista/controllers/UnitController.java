package br.com.sigteam.sigbolsista.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.sigteam.sigbolsista.models.Unit;
import br.com.sigteam.sigbolsista.models.User;
import br.com.sigteam.sigbolsista.services.UnitService;

@Controller
public class UnitController {
	
	private static final String REDIRECT    = "redirect:/"; 
	private static final String UNIT_LIST  = "units/unit-list";
	private static final String UNIT_FORM  = "units/unit-form";
	
	
	@Autowired
	private UnitService unitService;
	
	@RequestMapping(value = "/unit", method = RequestMethod.GET)
	public String getAllUnits(Model model, @AuthenticationPrincipal User user) {
		model.addAttribute("units", unitService.findAll());
		return UNIT_LIST;
	}
	
	
	@RequestMapping(value = "unit/add", method = RequestMethod.POST)
	public String create(Unit unit, BindingResult bindingResult,
						 @AuthenticationPrincipal User user) {
		if(bindingResult.hasErrors()) {
			return UNIT_FORM;
		}
		
		unitService.save(unit);
		return REDIRECT + UNIT_FORM;
	}
	
	
	@GetMapping("/unit/{id}/delete")
	public String delete(@PathVariable("id") Long id, @AuthenticationPrincipal User user){
		Optional<Unit> unit = unitService.findById(id);
		if(!unit.isPresent())
			return REDIRECT + UNIT_LIST;
		
		unitService.delete(unit.get());
		return REDIRECT + UNIT_LIST;
	}
	
}
