package br.com.sigteam.sigbolsista.controllers;

import br.com.sigteam.sigbolsista.models.Unit;
import br.com.sigteam.sigbolsista.models.User;
import br.com.sigteam.sigbolsista.services.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
public class UnitController {
    @Autowired
    private UnitService unitService;
    
    private static final String DASHBOARD = "redirect:/dashboard";
    

    @RequestMapping(value = "/unit", method = RequestMethod.GET)
    public String list(Model model, @AuthenticationPrincipal User user) {
    	if(unitService.checkRole(user))
    		return DASHBOARD;
    	
        model.addAttribute("units", unitService.all());
        return "units/unit-list";
    }

    @RequestMapping(value = "/unit/add", method = RequestMethod.GET)
    public String emptyForm(Model model, @AuthenticationPrincipal User user) {
    	if(unitService.checkRole(user))
    		return DASHBOARD;
    	
        model.addAttribute("unit", new Unit());
        return "units/unit-form";
    }

    @RequestMapping(value = "/unit/add", method = RequestMethod.POST)
    public String create(Unit unit, BindingResult bindingResult, @AuthenticationPrincipal User user) {
    	if(unitService.checkRole(user))
    		return DASHBOARD;
    	
        if (bindingResult.hasErrors()) {
            return "units/unit-form";
        }

        unitService.save(unit);
        return "redirect:/unit";
    }

    @RequestMapping(value = "/unit/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal User user) {
    	if(unitService.checkRole(user))
    		return DASHBOARD;
    	
        Optional<Unit> unit = unitService.getById(id);
        if (!unit.isPresent()) {
            return "redirect:/unit";
        }

        model.addAttribute("unit", unit.get());
        return "units/unit-detail";
    }

    @RequestMapping(value = "/unit/{id}/change", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal User user) {
    	if(unitService.checkRole(user))
    		return DASHBOARD;
    	
        Optional<Unit> unit = unitService.getById(id);
        if (!unit.isPresent()) {
            return "redirect:/unit";
        }

        model.addAttribute("unit", unit.get());
        return "units/unit-update";
    }

    @RequestMapping(value = "/unit/{id}/change", method = RequestMethod.POST)
    public String update(@PathVariable("id") Long id, Unit unit, BindingResult bindingResult, 
    					 @AuthenticationPrincipal User user) {
    	if(unitService.checkRole(user))
    		return DASHBOARD;
    	
        Optional<Unit> unitOptional = unitService.getById(id);
        if (!unitOptional.isPresent()) {
            return "redirect:/unit";
        }

        if (bindingResult.hasErrors()) {
            return "units/unit-update";
        }

        unitService.save(unit);
        return "redirect:/unit";
    }

    @GetMapping("/unit/{id}/delete")
    public String delete(@PathVariable("id") Long id, @AuthenticationPrincipal User user) {
    	if(unitService.checkRole(user))
    		return DASHBOARD;
    	
        Optional<Unit> unit = unitService.getById(id);
        if (!unit.isPresent()) {
            return "redirect:/unit";
        }

        unitService.delete(unit.get());
        return "redirect:/unit";
    }
    
}
