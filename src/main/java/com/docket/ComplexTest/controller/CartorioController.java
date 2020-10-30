package com.docket.ComplexTest.controller;

import com.docket.ComplexTest.model.Cartorio;
import com.docket.ComplexTest.model.Certidoes;
import com.docket.ComplexTest.repository.CartorioRepository;
import com.docket.ComplexTest.repository.CertidoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class CartorioController {

    @Autowired
    private CartorioRepository repository;
    private CertidoesRepository certidoesRepository;

    //Página principal
    @GetMapping("cartorios/edit")
    public String mostraCartorio(Cartorio cartorio) {
        return "cartorio";
    }

    @PostMapping("cartorios/adicionar")
    public String adicionarCartorio(Cartorio cartorio, BindingResult result) {
        repository.save(cartorio);
        return "redirect:/";
    }

    @GetMapping("/")
    public String showUpdateForm(Model model) {
        model.addAttribute("cartorios", repository.findAll());
        return "index";
    }

    @GetMapping("cartorios/emitirCertidao/{id}")
    public String certidoesEmitir(@PathVariable("id") long id, Model model) {
        Certidoes certidoes = new Certidoes();
        model.addAttribute("certidoes", certidoes);
        return "emitirCertidao";
    }

    @PostMapping("certidoes/adicionar/{id}")
    public String adicionarCertidao(@PathVariable("id") long id, Certidoes certidoes, BindingResult result) {
        Cartorio cartorio = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cartorio de id invalido:" + id));

        certidoes.setCartorio(cartorio);
        certidoesRepository.save(certidoes);
        return "pedidos";
    }

    @GetMapping("pedidos")
    public String showPedidos(Model model) {
        return "pedidos";
    }
}