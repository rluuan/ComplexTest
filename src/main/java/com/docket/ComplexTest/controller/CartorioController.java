package com.docket.ComplexTest.controller;

import com.docket.ComplexTest.model.Cartorio;
import com.docket.ComplexTest.repository.CartorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cartorios/")
public class CartorioController {

    @Autowired
    private CartorioRepository repository;

    //Página principal
    @GetMapping("home")
    public String mostrarCartorio(Cartorio cartorio) {
        return "cartorio";
    }

    @PostMapping("adicionar")
    public String adicionarCartorio(Cartorio cartorio, BindingResult result) {
        repository.save(cartorio);
        return "redirect:list";
    }

    @GetMapping("list")
    public String showUpdateForm(Model model) {
        model.addAttribute("cartorios", repository.findAll());
        return "index";
    }
}