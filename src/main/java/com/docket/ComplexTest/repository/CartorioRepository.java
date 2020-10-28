package com.docket.ComplexTest.repository;

import com.docket.ComplexTest.model.Cartorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartorioRepository extends JpaRepository<Cartorio, Long> {

    List<Cartorio> findByNome(String nome);
}