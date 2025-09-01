package br.com.jacto.api_agendamento.peca.mapper;

import br.com.jacto.api_agendamento.agendamento.dto.response.AgendamentoResponseDto;
import br.com.jacto.api_agendamento.peca.model.PecaUsada;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PecaUsadaMapper {

    AgendamentoResponseDto.PecaUsadaResponseDto toDto(PecaUsada pecaUsada);
}
