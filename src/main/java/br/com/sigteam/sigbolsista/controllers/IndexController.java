package br.com.sigteam.sigbolsista.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/protected-resource")
    public String protectedResource() {
        return "protected-resource";
    }
}
