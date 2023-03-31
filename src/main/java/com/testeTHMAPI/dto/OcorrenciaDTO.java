package com.testeTHMAPI.dto;

import com.testeTHMAPI.model.Enum.Classificacao;
import com.testeTHMAPI.model.Enum.Intensidade;

import java.time.LocalDateTime;

//DTO diferente de ocorrencias para ser usado pelo User DTO
public class OcorrenciaDTO {

    private Integer id;

    private LocalDateTime data;

    private String local;

    private Intensidade intensidade;

    private Classificacao classificacao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
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
