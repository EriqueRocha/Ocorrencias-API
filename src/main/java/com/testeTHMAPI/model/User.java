package com.testeTHMAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "tab_usuários") //atribui nome a tabela
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @NotEmpty//não pode estar vazio
    private String nome;

    @NotEmpty
    @JsonIgnore//anotação que faz ignorar ao dar um get
    private String senha;

    @NotEmpty
    private String login;

    @JsonIgnore
    @OneToMany(mappedBy = "userId", cascade = CascadeType.REMOVE)//7
    private List<Ocorrencia> ocorrencias;

    private int numOcorrencias;

    private boolean admin;

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getNumOcorrencias() {
        return numOcorrencias;
    }

    public void setNumOcorrencias(int numOcorrencias) {
        this.numOcorrencias = numOcorrencias;
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

    public List<Ocorrencia> getOcorrencias() {
        return ocorrencias;
    }

    public void setOcorrencias(List<Ocorrencia> ocorrencias) {
        this.ocorrencias = ocorrencias;
    }

    public User() {}

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private User user;

        private UserBuilder() {
            user = new User();
        }

        public UserBuilder id(Integer id) {
            user.id = id;
            return this;
        }

        public UserBuilder senha(String senha) {
            user.senha = senha;
            return this;
        }

        public UserBuilder nome(String nome) {
            user.nome = nome;
            return this;
        }

        public UserBuilder login(String login) {
            user.login = login;
            return this;
        }

        public UserBuilder numOcorrencias(int numOcorrencias) {
            user.numOcorrencias = numOcorrencias;
            return this;
        }

        public UserBuilder admin(boolean admin) {
            user.admin = admin;
            return this;
        }

        public User build() {
            return user;
        }



    }

}



