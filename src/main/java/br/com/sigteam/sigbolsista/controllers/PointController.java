package br.com.sigteam.sigbolsista.controllers;

import br.com.sigteam.sigbolsista.models.Point;
import br.com.sigteam.sigbolsista.models.User;
import br.com.sigteam.sigbolsista.services.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
public class PointController {
    @Autowired
    private PointService pointService;

    @RequestMapping(value = "/point", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("points", pointService.all());
        return "points/point-list";
    }

    @RequestMapping(value = "/point/add", method = RequestMethod.GET)
    public String emptyForm(Model model) {
        model.addAttribute("point", new Point());
        return "points/point-form";
    }

    @RequestMapping(value = "/point/add", method = RequestMethod.POST)
    public String create(Point point, BindingResult bindingResult, @AuthenticationPrincipal User user) {
        if (bindingResult.hasErrors()) {
            return "points/point-form";
        }

        point.setUser(user);
        pointService.save(point);
        return "redirect:/point";
    }

    @RequestMapping(value = "/point/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable("id") Long id, Model model) {
        Optional<Point> point = pointService.getById(id);
        if (!point.isPresent()) {
            return "redirect:/point";
        }

        model.addAttribute("point", point.get());
        return "points/point-detail";
    }

    @RequestMapping(value = "/point/{id}/change", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, Model model) {
        Optional<Point> point = pointService.getById(id);
        if (!point.isPresent()) {
            return "redirect:/point";
        }

        model.addAttribute("point", point.get());
        return "points/point-update";
    }

    @RequestMapping(value = "/point/{id}/change", method = RequestMethod.POST)
    public String update(@PathVariable("id") Long id, Point point, BindingResult bindingResult, @AuthenticationPrincipal User user) {
        Optional<Point> pointOptional = pointService.getById(id);
        if (!pointOptional.isPresent()) {
            return "redirect:/point";
        }

        if (bindingResult.hasErrors()) {
            return "points/point-update";
        }

        point.setUser(user);
        pointService.save(point);
        return "redirect:/point";
    }

    @GetMapping("/point/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        Optional<Point> point = pointService.getById(id);
        if (!point.isPresent()) {
            return "redirect:/point";
        }

        pointService.delete(point.get());
        return "redirect:/point";
    }
}
