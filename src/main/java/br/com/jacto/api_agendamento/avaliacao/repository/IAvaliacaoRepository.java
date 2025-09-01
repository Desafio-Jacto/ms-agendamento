package br.com.jacto.api_agendamento.avaliacao.repository;

import br.com.jacto.api_agendamento.avaliacao.model.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAvaliacaoRepository extends JpaRepository<Avaliacao, Integer> {

    Integer countByAgendamentoIdUsuario(Integer idUsuario);
}
