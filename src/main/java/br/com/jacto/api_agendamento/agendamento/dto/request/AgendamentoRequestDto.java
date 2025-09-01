package br.com.jacto.api_agendamento.agendamento.dto.request;

import br.com.jacto.api_agendamento.equipamento.dto.request.EquipamentoUsadoRequestDto;
import br.com.jacto.api_agendamento.peca.dto.request.PecaUsadaRequestDto;

import java.time.LocalDateTime;
import java.util.List;

public class AgendamentoRequestDto {

    private LocalDateTime dataAgendamento;
    private String tipoAgendamento;
    private String observacoes;
    private String prioridade;
    private Integer idUsuario;
    private Integer idFazenda;
    private List<EquipamentoUsadoRequestDto> equipamentosUsados;
    private List<PecaUsadaRequestDto> pecasUsadas;

    public LocalDateTime getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(LocalDateTime dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public String getTipoAgendamento() {
        return tipoAgendamento;
    }

    public void setTipoAgendamento(String tipoAgendamento) {
        this.tipoAgendamento = tipoAgendamento;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdFazenda() {
        return idFazenda;
    }

    public void setIdFazenda(Integer idFazenda) {
        this.idFazenda = idFazenda;
    }

    public List<EquipamentoUsadoRequestDto> getEquipamentosUsados() {
        return equipamentosUsados;
    }

    public void setEquipamentosUsados(List<EquipamentoUsadoRequestDto> equipamentosUsados) {
        this.equipamentosUsados = equipamentosUsados;
    }

    public List<PecaUsadaRequestDto> getPecasUsadas() {
        return pecasUsadas;
    }

    public void setPecasUsadas(List<PecaUsadaRequestDto> pecasUsadas) {
        this.pecasUsadas = pecasUsadas;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }
}
