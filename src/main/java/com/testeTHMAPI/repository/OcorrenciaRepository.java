package com.testeTHMAPI.repository;

import com.testeTHMAPI.model.Ocorrencia;
import org.springframework.data.jpa.repository.JpaRepository;

//repositório, se torna o local de referência para quando se precisar apontar para o banco de dados
public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Integer> {
}
