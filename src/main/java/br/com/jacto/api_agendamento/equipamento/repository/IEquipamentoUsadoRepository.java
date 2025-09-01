package br.com.jacto.api_agendamento.equipamento.repository;

import br.com.jacto.api_agendamento.equipamento.model.EquipamentoUsado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEquipamentoUsadoRepository extends JpaRepository<EquipamentoUsado, Integer> {
}
