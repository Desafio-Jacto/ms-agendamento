package br.com.jacto.api_agendamento.peca.model;

import br.com.jacto.api_agendamento.agendamento.model.Agendamento;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "pecas_usadas")
public class PecaUsada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_agendamento", nullable = false)
    private Agendamento agendamento;

    @Column(name = "id_peca", nullable = false)
    private Integer idPeca;

    @Column(name = "nome_peca", nullable = false)
    private String nomePeca;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PecaUsada)) return false;
        return id != null && id.equals(((PecaUsada) o).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Agendamento getAgendamento() { return agendamento; }
    public void setAgendamento(Agendamento agendamento) { this.agendamento = agendamento; }
    public Integer getIdPeca() { return idPeca; }
    public void setIdPeca(Integer idPeca) { this.idPeca = idPeca; }
    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public String getNomePeca() {
        return nomePeca;
    }

    public void setNomePeca(String nomePeca) {
        this.nomePeca = nomePeca;
    }
}
