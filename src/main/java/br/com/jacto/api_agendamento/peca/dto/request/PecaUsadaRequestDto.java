package br.com.jacto.api_agendamento.peca.dto.request;

public class PecaUsadaRequestDto {

    private Integer idPeca;
    private Integer quantidadeUsada;

    public PecaUsadaRequestDto() {}

    public PecaUsadaRequestDto(Integer idPeca, Integer quantidadeUsada) {
        this.idPeca = idPeca;
        this.quantidadeUsada = quantidadeUsada;
    }

    public Integer getIdPeca() {
        return idPeca;
    }

    public void setIdPeca(Integer idPeca) {
        this.idPeca = idPeca;
    }

    public Integer getQuantidade() {
        return quantidadeUsada;
    }

    public void setQuantidade(Integer quantidadeUsada) {
        this.quantidadeUsada = quantidadeUsada;
    }
}
