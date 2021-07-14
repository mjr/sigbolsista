package br.com.sigteam.sigbolsista.controllers;

import br.com.sigteam.sigbolsista.models.Justification;
import br.com.sigteam.sigbolsista.models.User;
import br.com.sigteam.sigbolsista.services.JustificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
public class JustificationController {
    @Autowired
    private JustificationService justificationService;

    @RequestMapping(value = "/justification", method = RequestMethod.GET)
    public String list(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("justifications", justificationService.filterByUser(user));
        return "justifications/justification-list";
    }

    @RequestMapping(value = "/justification/add", method = RequestMethod.GET)
    public String emptyForm(Model model) {
        model.addAttribute("justification", new Justification());
        model.addAttribute("action", "/justification/add");
        return "justifications/justification-form";
    }

    @RequestMapping(value = "/justification/add", method = RequestMethod.POST)
    public String create(Justification justification, BindingResult bindingResult, @AuthenticationPrincipal User user) {
        if (bindingResult.hasErrors()) {
            return "justifications/justification-form";
        }

        justification.setUser(user);
        justificationService.save(justification);
        return "redirect:/justification";
    }

    @RequestMapping(value = "/justification/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal User user) {
        Optional<Justification> justification = justificationService.getByIdAndUser(id, user);
        if (!justification.isPresent()) {
            return "redirect:/justification";
        }

        model.addAttribute("justification", justification.get());
        return "justifications/justification-detail";
    }

    @RequestMapping(value = "/justification/{id}/change", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal User user) {
        Optional<Justification> justification = justificationService.getByIdAndUser(id, user);
        if (!justification.isPresent()) {
            return "redirect:/justification";
        }

        model.addAttribute("justification", justification.get());
        return "justifications/justification-update";
    }

    @RequestMapping(value = "/justification/{id}/change", method = RequestMethod.POST)
    public String update(@PathVariable("id") Long id, Justification justification, BindingResult bindingResult, @AuthenticationPrincipal User user) {
        Optional<Justification> justificationOptional = justificationService.getByIdAndUser(id, user);
        if (!justificationOptional.isPresent()) {
            return "redirect:/justification";
        }

        if (bindingResult.hasErrors()) {
            return "justifications/justification-update";
        }

        justification.setUser(user);
        justificationService.save(justification);
        return "redirect:/justification";
    }

    @GetMapping("/justification/{id}/delete")
    public String delete(@PathVariable("id") Long id, @AuthenticationPrincipal User user) {
        Optional<Justification> justification = justificationService.getByIdAndUser(id, user);
        if (!justification.isPresent()) {
            return "redirect:/justification";
        }

        justificationService.delete(justification.get());
        return "redirect:/justification";
    }
}
