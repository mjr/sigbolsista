package br.com.sigteam.sigbolsista.controllers;

import br.com.sigteam.sigbolsista.models.Point;
import br.com.sigteam.sigbolsista.models.User;
import br.com.sigteam.sigbolsista.services.PointService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Gabrielle Duarte (gabrielleduartee3@gmail.com)
 * @since 21/07/2021
 */

@RestController("/point")
public class PointController {

    private final PointService pointService;

    public PointController(PointService pointService) {
        this.pointService = pointService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String create(Point point, BindingResult bindingResult, @AuthenticationPrincipal User user) {
        if (bindingResult.hasErrors()) {
//            return "justifications/justification-form";
        }
        else{
            pointService.save(point, user);
        }
        return "redirect:/justification";
    }



}
