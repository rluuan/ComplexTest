package com.docket.ComplexTest.controller;

import com.docket.ComplexTest.model.Cartorio;
import com.docket.ComplexTest.model.Certidoes;
import com.docket.ComplexTest.repository.CartorioRepository;
import com.docket.ComplexTest.repository.CertidoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class CartorioController {

    @Autowired
    private CartorioRepository repository;

    @Autowired
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
        model.addAttribute("cartorioId", id);
        return "emitirCertidao";
    }

    @PostMapping("certidoes/adicionar")
    public String adicionarCertidao(Certidoes certidoes, BindingResult result) {
        Cartorio cartorio = this.repository.findById(certidoes.getExternalCartorioId()).get();
        certidoes.setCartorio(cartorio);
        this.certidoesRepository.save(certidoes);
        return "redirect:/pedidos";
    }

    @GetMapping("pedidos")
    public String showPedidos(Model model) {
        model.addAttribute("certidoes", certidoesRepository.findAll());
        return "pedidos";
    }
}