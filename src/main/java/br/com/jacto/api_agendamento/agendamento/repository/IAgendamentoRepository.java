package br.com.jacto.api_agendamento.agendamento.repository;

import br.com.jacto.api_agendamento.agendamento.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAgendamentoRepository extends JpaRepository<Agendamento, Integer> {

    Integer countByIdUsuario(Integer idUsuario);
    Integer countByIdUsuarioAndAgendamentoFinalizadoTrue(Integer idUsuario);
}
