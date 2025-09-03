package br.com.jacto.api_agendamento.agendamento.dto.response;

import br.com.jacto.api_agendamento.agendamento.enums.PrioridadeEnum;

import java.time.LocalDateTime;
import java.util.List;

public class AgendamentoResponseDto {

    private Integer idAgendamento;
    private LocalDateTime dataAgendamento;
    private String tipoAgendamento;
    private String observacoes;
    private PrioridadeEnum prioridade;
    private Boolean agendamentoFinalizado = false;
    private String avaliacaoCliente = "Sem avaliação";
    private Integer idUsuario;
    private Integer idFazenda;
    private List<EquipamentoUsadoResponseDto> equipamentosUsados;
    private List<PecaUsadaResponseDto> pecasUsadas;

    public AgendamentoResponseDto() {}

    public AgendamentoResponseDto(Integer idAgendamento, LocalDateTime dataAgendamento, String tipoAgendamento, String observacoes, PrioridadeEnum prioridade, Boolean agendamentoFinalizado, String avaliacaoCliente, Integer idUsuario, Integer idFazenda, List<EquipamentoUsadoResponseDto> equipamentosUsados, List<PecaUsadaResponseDto> pecasUsadas) {
        this.idAgendamento = idAgendamento;
        this.dataAgendamento = dataAgendamento;
        this.tipoAgendamento = tipoAgendamento;
        this.observacoes = observacoes;
        this.prioridade = prioridade;
        this.agendamentoFinalizado = agendamentoFinalizado;
        this.avaliacaoCliente = avaliacaoCliente;
        this.idUsuario = idUsuario;
        this.idFazenda = idFazenda;
        this.equipamentosUsados = equipamentosUsados;
        this.pecasUsadas = pecasUsadas;
    }

    public Integer getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(Integer idAgendamento) {
        this.idAgendamento = idAgendamento;
    }

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

    public PrioridadeEnum getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(PrioridadeEnum prioridade) {
        this.prioridade = prioridade;
    }

    public Boolean getAgendamentoFinalizado() {
        return agendamentoFinalizado;
    }

    public void setAgendamentoFinalizado(Boolean agendamentoFinalizado) {
        this.agendamentoFinalizado = agendamentoFinalizado;
    }

    public String getAvaliacaoCliente() {
        return avaliacaoCliente;
    }

    public void setAvaliacaoCliente(String avaliacaoCliente) {
        this.avaliacaoCliente = avaliacaoCliente;
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

    public List<EquipamentoUsadoResponseDto> getEquipamentosUsados() {
        return equipamentosUsados;
    }

    public void setEquipamentosUsados(List<EquipamentoUsadoResponseDto> equipamentosUsados) {
        this.equipamentosUsados = equipamentosUsados;
    }

    public List<PecaUsadaResponseDto> getPecasUsadas() {
        return pecasUsadas;
    }

    public void setPecasUsadas(List<PecaUsadaResponseDto> pecasUsadas) {
        this.pecasUsadas = pecasUsadas;
    }

    public static class PecaUsadaResponseDto {
        private Integer idPeca;
        private String nomePeca;
        private Integer quantidade;

        public PecaUsadaResponseDto(Integer idPeca, String nomePeca, Integer quantidade) {
            this.idPeca = idPeca;
            this.nomePeca = nomePeca;
            this.quantidade = quantidade;
        }

        public PecaUsadaResponseDto() {}

        public Integer getIdPeca() {
            return idPeca;
        }

        public void setIdPeca(Integer idPeca) {
            this.idPeca = idPeca;
        }


        public String getNomePeca() {
            return nomePeca;
        }

        public void setNomePeca(String nomePeca) {
            this.nomePeca = nomePeca;
        }

        public Integer getQuantidade() {
            return quantidade;
        }

        public void setQuantidade(Integer quantidade) {
            this.quantidade = quantidade;
        }
    }


    public static class EquipamentoUsadoResponseDto {
        private Integer idEquipamento;
        private String nomeEquipamento;
        private Integer quantidade;

        public EquipamentoUsadoResponseDto() {}

        public Integer getIdEquipamento() {
            return idEquipamento;
        }

        public void setIdEquipamento(Integer idEquipamento) {
            this.idEquipamento = idEquipamento;
        }

        public Integer getQuantidade() {
            return quantidade;
        }

        public void setQuantidade(Integer quantidade) {
            this.quantidade = quantidade;
        }

        public String getNomeEquipamento() {
            return nomeEquipamento;
        }

        public void setNomeEquipamento(String nomeEquipamento) {
            this.nomeEquipamento = nomeEquipamento;
        }
    }
}