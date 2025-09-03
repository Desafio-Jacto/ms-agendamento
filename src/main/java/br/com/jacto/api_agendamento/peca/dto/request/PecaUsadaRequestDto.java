package br.com.jacto.api_agendamento.peca.dto.request;

public class PecaUsadaRequestDto {

    private Integer idPeca;
    private String nomePeca;
    private Integer quantidade;

    public PecaUsadaRequestDto() {}

    public PecaUsadaRequestDto(Integer idPeca, String nomePeca, Integer quantidade) {
        this.idPeca = idPeca;
        this.nomePeca = nomePeca;
        this.quantidade = quantidade;
    }

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
