package br.com.jacto.api_agendamento.peca.repository;

import br.com.jacto.api_agendamento.peca.model.PecaUsada;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPecaUsadaRepository extends JpaRepository<PecaUsada, Integer> {
}
