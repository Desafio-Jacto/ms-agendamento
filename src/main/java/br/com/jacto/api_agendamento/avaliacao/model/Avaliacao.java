package br.com.jacto.api_agendamento.avaliacao.model;

import br.com.jacto.api_agendamento.agendamento.enums.AvaliacaoClienteEnum;
import br.com.jacto.api_agendamento.agendamento.model.Agendamento;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "avaliacao")
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_avaliacao")
    private Integer idAvaliacao;

    @ManyToOne
    @JoinColumn(name = "id_agendamento", nullable = false)
    private Agendamento agendamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "nota_avaliacao")
    private AvaliacaoClienteEnum notaAvaliacao;

    @Column(name = "comentario", columnDefinition = "TEXT")
    private String comentario;

    @Column(name = "data_avaliacao", nullable = false)
    private LocalDateTime dataAvaliacao;

    public Avaliacao() {
        this.dataAvaliacao = LocalDateTime.now();
    }

    public Integer getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(Integer idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

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

    public LocalDateTime getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(LocalDateTime dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }
}