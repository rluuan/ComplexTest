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

import java.util.List;

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
        if (cartorio.getNome() == "") {
            return "redirect:/";
        }
        repository.save(cartorio);
        return "redirect:/";
    }
    @GetMapping("cartorios/excluir/{id}")
    public String excluirCartorio(@PathVariable("id") long id, Model model) {
        Cartorio cartorio = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cartorio não encontrado"));
        List<Certidoes> certidoes = certidoesRepository.findByCartorioId(id);
        certidoesRepository.deleteAll(certidoes);
        repository.delete(cartorio);
        model.addAttribute("cartorios", repository.findAll());
        return "index";
    }
    @GetMapping("cartorios/edit/{id}")
    public String editarCartorio(@PathVariable("id") long id, Model model) {
        Cartorio cartorio = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cartorio não encontrado"));
        model.addAttribute("cartorio", cartorio);
        return "editarCartorio";
    }
    @PostMapping("cartorios/updateData/{id}")
    public String updateCartorio(@PathVariable("id") long id,Cartorio cartorio, BindingResult result, Model model) {
        if (result.hasErrors()) {
            cartorio.setId(id);
            return "editarCartorio";
        }
        cartorio.setId(id);
        repository.save(cartorio);
        model.addAttribute("cartorios", repository.findAll());
        return "/index";
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
        if (certidoes.getNome() == "") {
            return "redirect:/emitirCertidao";
        }
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