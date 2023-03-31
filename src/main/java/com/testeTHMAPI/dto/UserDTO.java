package com.testeTHMAPI.dto;

import java.util.List;


//DTO de User modificado, sem a variável de senha
public class UserDTO {

    private Integer id;

    private String nome;

    private String login;

    private boolean admin;

    private List<OcorrenciaDTO> ocorrencias;

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<OcorrenciaDTO> getOcorrencias() {
        return ocorrencias;
    }

    public void setOcorrencias(List<OcorrenciaDTO> ocorrencias) {
        this.ocorrencias = ocorrencias;
    }
}
