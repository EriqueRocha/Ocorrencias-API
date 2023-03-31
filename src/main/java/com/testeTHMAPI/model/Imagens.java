package com.testeTHMAPI.model;

import javax.persistence.*;

@Entity
public class Imagens {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "ocorrenciaId")
    private Integer ocorrenciaId;

    private String nomeImagem1;

    private String nomeImagem2;



//    @OneToOne
//    @JoinColumn(name = "nomeImagem")
//    private Ocorrencia ocorrencia;
//
//    public Ocorrencia getOcorrencia() {
//        return ocorrencia;
//    }
//
//    public void setOcorrencia(Ocorrencia ocorrencia) {
//        this.ocorrencia = ocorrencia;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOcorrenciaId() {
        return ocorrenciaId;
    }

    public void setOcorrenciaId(Integer ocorrenciaId) {
        this.ocorrenciaId = ocorrenciaId;
    }



    public String getNomeImagem1() {
        return nomeImagem1;
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
