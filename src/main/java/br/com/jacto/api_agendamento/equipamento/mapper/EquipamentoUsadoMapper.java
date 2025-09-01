package br.com.jacto.api_agendamento.equipamento.mapper;

import br.com.jacto.api_agendamento.agendamento.dto.response.AgendamentoResponseDto;
import br.com.jacto.api_agendamento.equipamento.model.EquipamentoUsado;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EquipamentoUsadoMapper {

    AgendamentoResponseDto.EquipamentoUsadoResponseDto toDto(EquipamentoUsado equipamentoUsado);

}
