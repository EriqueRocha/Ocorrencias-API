package com.testeTHMAPI.model;

import com.testeTHMAPI.model.Enum.Classificacao;
import com.testeTHMAPI.model.Enum.Intensidade;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDateTime;


@Entity//cria uma entidade
public class Ocorrencia {

    @Id//marca como primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)//gera os Id automáticamente
    private Integer id;

    @JoinColumn(name = "user_id")//atribui nome a coluna
    private Integer userId;

    @DateTimeFormat(pattern="dd/MM/yyyy")//muda o formado da data
    private LocalDateTime data;

    private String local;

    @Enumerated(EnumType.STRING)//atribui o valor literal da variável
    private Intensidade intensidade;

    @Enumerated(EnumType.STRING)
    private Classificacao classificacao;

//    @OneToOne(cascade = CascadeType.REMOVE)
//    @JoinColumn(name = "ocorrenciaId")
//    private Imagens imagem;
//
//
//    public Imagens getImagem() {
//        return imagem;
//    }
//
//    public void setImagem(Imagens imagem) {
//        this.imagem = imagem;
//    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public Integer setUserId(int userId) {
        this.userId = userId;
        return null;
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
