package br.com.sigteam.sigbolsista.controllers;

import br.com.sigteam.sigbolsista.models.Sector;
import br.com.sigteam.sigbolsista.models.User;
import br.com.sigteam.sigbolsista.services.SectorService;
import br.com.sigteam.sigbolsista.services.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
public class SectorController {
    @Autowired
    private SectorService sectorService;

    @Autowired
    private UnitService unitService;
    
    private static final String DASHBOARD = "redirect:/dashboard";

    @RequestMapping(value = "/sector", method = RequestMethod.GET)
    public String list(Model model, @AuthenticationPrincipal User user) {
    	if(sectorService.checkRole(user))
    		return DASHBOARD;
    	
        model.addAttribute("sectors", sectorService.all());
        return "sectors/sector-list";
    }

    @RequestMapping(value = "/sector/add", method = RequestMethod.GET)
    public String emptyForm(Model model, @AuthenticationPrincipal User user) {
    	if(sectorService.checkRole(user))
    		return DASHBOARD;
    	
        model.addAttribute("units", unitService.all());
        model.addAttribute("sector", new Sector());
        return "sectors/sector-form";
    }

    @RequestMapping(value = "/sector/add", method = RequestMethod.POST)
    public String create(Sector sector, BindingResult bindingResult, @AuthenticationPrincipal User user) {
    	if(sectorService.checkRole(user))
    		return DASHBOARD;
    	
        if (bindingResult.hasErrors()) {
            return "sectors/sector-form";
        }

        sectorService.save(sector);
        return "redirect:/sector";
    }

    @RequestMapping(value = "/sector/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal User user) {
    	if(sectorService.checkRole(user))
    		return DASHBOARD;
    	
        Optional<Sector> sector = sectorService.getById(id);
        if (!sector.isPresent()) {
            return "redirect:/sector";
        }

        model.addAttribute("sector", sector.get());
        return "sectors/sector-detail";
    }

    @RequestMapping(value = "/sector/{id}/change", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal User user) {
    	if(sectorService.checkRole(user))
    		return DASHBOARD;
    	
        Optional<Sector> sector = sectorService.getById(id);
        if (!sector.isPresent()) {
            return "redirect:/sector";
        }

        model.addAttribute("units", unitService.all());
        model.addAttribute("sector", sector.get());
        return "sectors/sector-update";
    }

    @RequestMapping(value = "/sector/{id}/change", method = RequestMethod.POST)
    public String update(@PathVariable("id") Long id, Sector sector, BindingResult bindingResult,
    					 @AuthenticationPrincipal User user) {
    	if(sectorService.checkRole(user))
    		return DASHBOARD;
    	
        Optional<Sector> sectorOptional = sectorService.getById(id);
        if (!sectorOptional.isPresent()) {
            return "redirect:/sector";
        }

        if (bindingResult.hasErrors()) {
            return "sectors/sector-update";
        }

        sectorService.save(sector);
        return "redirect:/sector";
    }

    @GetMapping("/sector/{id}/delete")
    public String delete(@PathVariable("id") Long id, @AuthenticationPrincipal User user) {
    	if(sectorService.checkRole(user))
    		return DASHBOARD;
    	
        Optional<Sector> sector = sectorService.getById(id);
        if (!sector.isPresent()) {
            return "redirect:/sector";
        }

        sectorService.delete(sector.get());
        return "redirect:/sector";
    }
}
