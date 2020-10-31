package com.docket.ComplexTest.repository;

import com.docket.ComplexTest.model.Certidoes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CertidoesRepository extends JpaRepository<Certidoes, Long> {

    List<Certidoes> findByNome(String nome);
    List<Certidoes> findByCartorioId(long id);
}
