package br.com.jacto.api_agendamento.equipamento.dto.request;

public class EquipamentoUsadoRequestDto {

    private Integer idEquipamento;
    private String nomeEquipamento;
    private Integer quantidade;

    public EquipamentoUsadoRequestDto() {}

    public EquipamentoUsadoRequestDto(Integer idEquipamento, String nomeEquipamento, Integer quantidade) {
        this.idEquipamento = idEquipamento;
        this.nomeEquipamento = nomeEquipamento;
        this.quantidade = quantidade;
    }

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