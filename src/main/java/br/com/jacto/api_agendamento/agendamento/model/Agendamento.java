package br.com.jacto.api_agendamento.agendamento.model;

import br.com.jacto.api_agendamento.agendamento.enums.PrioridadeEnum;
import br.com.jacto.api_agendamento.agendamento.enums.TipoAgendamentoEnum;
import br.com.jacto.api_agendamento.avaliacao.model.Avaliacao;
import br.com.jacto.api_agendamento.equipamento.model.EquipamentoUsado;
import br.com.jacto.api_agendamento.peca.model.PecaUsada;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "agendamento")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAgendamento;

    @Column(name = "data_agendamento", nullable = false)
    private LocalDateTime dataAgendamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_agendamento", length = 50, nullable = false)
    private TipoAgendamentoEnum tipoAgendamento;

    @Column(name = "observacoes", columnDefinition = "TEXT")
    private String observacoes;

    @Column(name = "agendamento_finalizado")
    private Boolean agendamentoFinalizado = false;

    @OneToMany(mappedBy = "agendamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Avaliacao> avaliacoes = new HashSet<>();

    @OneToMany(mappedBy = "agendamento")
    private Set<EquipamentoUsado> equipamentosUsados = new HashSet<>();

    @OneToMany(mappedBy = "agendamento")
    private Set<PecaUsada> pecasUsadas = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "prioridade")
    private PrioridadeEnum prioridade;

    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;

    @Column(name = "id_fazenda", nullable = false)
    private Integer idFazenda;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Agendamento)) return false;
        return idAgendamento != null && idAgendamento.equals(((Agendamento) o).idAgendamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAgendamento);
    }

    public Integer getIdAgendamento() { return idAgendamento; }
    public void setIdAgendamento(Integer idAgendamento) { this.idAgendamento = idAgendamento; }
    public LocalDateTime getDataAgendamento() { return dataAgendamento; }
    public void setDataAgendamento(LocalDateTime dataAgendamento) { this.dataAgendamento = dataAgendamento; }
    public TipoAgendamentoEnum getTipoAgendamento() { return tipoAgendamento; }
    public void setTipoAgendamento(TipoAgendamentoEnum tipoAgendamento) { this.tipoAgendamento = tipoAgendamento; }
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
    public Boolean getAgendamentoFinalizado() { return agendamentoFinalizado; }
    public void setAgendamentoFinalizado(Boolean agendamentoFinalizado) { this.agendamentoFinalizado = agendamentoFinalizado; }
    public Set<Avaliacao> getAvaliacoes() { return avaliacoes; }
    public void setAvaliacoes(Set<Avaliacao> avaliacoes) { this.avaliacoes = avaliacoes; }
    public Set<EquipamentoUsado> getEquipamentosUsados() { return equipamentosUsados; }
    public void setEquipamentosUsados(Set<EquipamentoUsado> equipamentosUsados) { this.equipamentosUsados = equipamentosUsados; }
    public Set<PecaUsada> getPecasUsadas() { return pecasUsadas; }
    public void setPecasUsadas(Set<PecaUsada> pecasUsadas) { this.pecasUsadas = pecasUsadas; }
    public PrioridadeEnum getPrioridade() { return prioridade; }
    public void setPrioridade(PrioridadeEnum prioridade) { this.prioridade = prioridade; }
    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }
    public Integer getIdFazenda() { return idFazenda; }
    public void setIdFazenda(Integer idFazenda) { this.idFazenda = idFazenda; }
}
