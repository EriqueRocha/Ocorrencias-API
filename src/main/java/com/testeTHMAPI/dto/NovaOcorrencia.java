package com.testeTHMAPI.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testeTHMAPI.model.Enum.Classificacao;
import com.testeTHMAPI.model.Enum.Intensidade;
import com.testeTHMAPI.model.Imagens;

import java.util.List;

//DTO para salvar um nova ocorrencia
public class NovaOcorrencia {

    private Integer userId;

    private String local;

    private Intensidade intensidade;

    private Classificacao classificacao;

//    @JsonIgnore
//    private Imagens nomeImagem;

//    public Imagens getNomeImagem() {
//        return nomeImagem;
//    }
//
//    public void setNomeImagem(Imagens nomeImagem) {
//        this.nomeImagem = nomeImagem;
//    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Intensidade getIntensidade() {
        return intensidade;
    }

    public void setIntensidade(Intensidade intensidade) {
        this.intensidade = intensidade;
    }

    public Classificacao getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(Classificacao classificacao) {
        this.classificacao = classificacao;
    }
}
