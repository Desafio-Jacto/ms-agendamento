package br.com.jacto.api_agendamento.equipamento.model;

import br.com.jacto.api_agendamento.agendamento.model.Agendamento;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "equipamentos_usados")
public class EquipamentoUsado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_agendamento", nullable = false)
    private Agendamento agendamento;

    @Column(name = "id_equipamento", nullable = false)
    private Integer idEquipamento;

    @Column(name = "nome_equipamento", nullable = false)
    private String nomeEquipamento;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EquipamentoUsado)) return false;
        return id != null && id.equals(((EquipamentoUsado) o).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Agendamento getAgendamento() { return agendamento; }
    public void setAgendamento(Agendamento agendamento) { this.agendamento = agendamento; }
    public Integer getIdEquipamento() { return idEquipamento; }
    public void setIdEquipamento(Integer idEquipamento) { this.idEquipamento = idEquipamento; }
    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public String getNomeEquipamento() {
        return nomeEquipamento;
    }

    public void setNomeEquipamento(String nomeEquipamento) {
        this.nomeEquipamento = nomeEquipamento;
    }
}
