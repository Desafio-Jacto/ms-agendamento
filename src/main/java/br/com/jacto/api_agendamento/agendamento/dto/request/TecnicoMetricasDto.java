package br.com.jacto.api_agendamento.agendamento.dto.request;

public class TecnicoMetricasDto {

    private Integer totalAgendamentos;
    private Integer agendamentosConcluidos;
    private Integer totalAvaliados;

    public TecnicoMetricasDto(Integer totalAgendamentos, Integer agendamentosConcluidos, Integer totalAvaliados) {
        this.totalAgendamentos = totalAgendamentos;
        this.agendamentosConcluidos = agendamentosConcluidos;
        this.totalAvaliados = totalAvaliados;
    }

    public TecnicoMetricasDto() {
    }

    public Integer getTotalAgendamentos() {
        return totalAgendamentos;
    }

    public void setTotalAgendamentos(Integer totalAgendamentos) {
        this.totalAgendamentos = totalAgendamentos;
    }

    public Integer getAgendamentosConcluidos() {
        return agendamentosConcluidos;
    }

    public void setAgendamentosConcluidos(Integer agendamentosConcluidos) {
        this.agendamentosConcluidos = agendamentosConcluidos;
    }

    public Integer getTotalAvaliados() {
        return totalAvaliados;
    }

    public void setTotalAvaliados(Integer totalAvaliados) {
        this.totalAvaliados = totalAvaliados;
    }
}
