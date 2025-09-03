package br.com.jacto.api_agendamento.agendamento.mapper;

import br.com.jacto.api_agendamento.agendamento.dto.request.AgendamentoRequestDto;
import br.com.jacto.api_agendamento.agendamento.dto.response.AgendamentoResponseDto;
import br.com.jacto.api_agendamento.agendamento.model.Agendamento;
import br.com.jacto.api_agendamento.equipamento.mapper.EquipamentoUsadoMapper;
import br.com.jacto.api_agendamento.equipamento.model.EquipamentoUsado;
import br.com.jacto.api_agendamento.peca.mapper.PecaUsadaMapper;
import br.com.jacto.api_agendamento.peca.model.PecaUsada;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = { PecaUsadaMapper.class, EquipamentoUsadoMapper.class })
public interface AgendamentoMapper {

    Agendamento toEntity(AgendamentoRequestDto dto);

    @Mapping(target = "pecasUsadas", source = "pecasUsadas", qualifiedByName = "mapPecaUsada")
    @Mapping(target = "equipamentosUsados", source = "equipamentosUsados", qualifiedByName = "mapEquipamentoUsado")
    AgendamentoResponseDto toDto(Agendamento entity);

    @Named("mapPecaUsada")
    @Mapping(target = "idPeca", source = "idPeca")
    @Mapping(target = "quantidade", source = "quantidade")
    AgendamentoResponseDto.PecaUsadaResponseDto toDto(PecaUsada entity);

    @Named("mapEquipamentoUsado")
    @Mapping(target = "idEquipamento", source = "idEquipamento")
    @Mapping(target = "nomeEquipamento", source = "nomeEquipamento")
    @Mapping(target = "quantidade", source = "quantidade")
    AgendamentoResponseDto.EquipamentoUsadoResponseDto toDto(EquipamentoUsado entity);

}
