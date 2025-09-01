package br.com.jacto.api_agendamento.agendamento.dto.request;

import java.time.LocalDateTime;

public class AgendamentoNotificacaoDto {

    private Integer idAgendamento;
    private LocalDateTime dataAgendamento;
    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
