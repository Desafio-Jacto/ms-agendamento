package br.com.jacto.api_agendamento.avaliacao.dto.request;

import br.com.jacto.api_agendamento.agendamento.enums.AvaliacaoClienteEnum;

public class AvaliacaoRequestDto {
    private AvaliacaoClienteEnum notaAvaliacao;
    private String comentario;

    // Getters e Setters
    public AvaliacaoClienteEnum getNotaAvaliacao() {
        return notaAvaliacao;
    }

    public void setNotaAvaliacao(AvaliacaoClienteEnum notaAvaliacao) {
        this.notaAvaliacao = notaAvaliacao;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
