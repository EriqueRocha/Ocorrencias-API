package com.testeTHMAPI.dto;

public class AdicionarImagens {

    private Integer ocorrenciaId;

    private String nomeImagem1;

    private String nomeImagem2;

    public String getNomeImagem1() {
        return nomeImagem1;
    }

    public Integer getOcorrenciaId() {
        return ocorrenciaId;
    }

    public void setOcorrenciaId(Integer ocorrenciaId) {
        this.ocorrenciaId = ocorrenciaId;
    }

    public void setNomeImagem1(String nomeImagem1) {
        this.nomeImagem1 = nomeImagem1;
    }

    public String getNomeImagem2() {
        return nomeImagem2;
    }

    public void setNomeImagem2(String nomeImagem2) {
        this.nomeImagem2 = nomeImagem2;
    }
}
