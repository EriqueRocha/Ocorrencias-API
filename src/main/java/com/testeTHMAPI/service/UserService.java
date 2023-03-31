package com.testeTHMAPI.service;

import com.testeTHMAPI.dto.AtualizarUser;
import com.testeTHMAPI.dto.NovoUser;
import com.testeTHMAPI.exception.SenhaInvalidaException;
import com.testeTHMAPI.model.Ocorrencia;
import com.testeTHMAPI.model.User;
import com.testeTHMAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    //"UserDetailsService" serve para carregar informações de uma base de dados


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public UserDetails autenticar(User user){

        UserDetails usuario = loadUserByUsername(user.getLogin());
        boolean senhasCoincidem = encoder.matches(user.getSenha(), usuario.getPassword());
        if(senhasCoincidem){
            return usuario;
        }

        throw new SenhaInvalidaException();

    }

    @Override//8
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = repository.findByLogin(login)
                .orElseThrow(()-> new UsernameNotFoundException("Usuário não encontrado"));
        //8.1
        String[] roles = user.isAdmin() ? new String[] {"ADMIN", "USER"} : new String[]{"USER"};
        //8.2
        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getLogin())
                .password(user.getSenha())
                .roles(roles)
                .build();

    }

    //método de salvamento de user
    public void save(NovoUser novoUser) {

        //verifica se o email usado não existe no banco
        if (repository.findByLogin(novoUser.getLogin()).isPresent()) {
            throw new RuntimeException("O email já está sendo utilizado.");
        }

        User user = new User();

        user.setNome(novoUser.getNome());
        user.setLogin(novoUser.getLogin());

        //encripta o imput da senha e atribui a variável de senha da classe
        String senhaEncriptada = passwordEncoder.encode(novoUser.getSenha());
        novoUser.setSenha(senhaEncriptada);

        user.setSenha(senhaEncriptada);// atribui a senha já encriptada ao user
        user.setAdmin(novoUser.isAdmin());

        repository.save(user); // salva no banco
    }


    //retona a lista de usuários pelo DTO já que a classe user trás a lista de ocorrencias
    public List<User> findAllWithNumOcorrencias() {
        List<User> users = repository.findAll();
        List<User> dtos = new ArrayList<>();

        for (User user : users) {
            User dto = new User();
            dto.setId(user.getId());
            dto.setNome(user.getNome());
            dto.setLogin(user.getLogin());
            dto.setAdmin(user.isAdmin());

            List<Ocorrencia> ocorrencias = user.getOcorrencias();
            int numOcorrencias = ocorrencias.size();

            dto.setNumOcorrencias(numOcorrencias);
            dtos.add(dto);
        }

        return dtos;
    }


    //método que edita os dados do user
    public void update(AtualizarUser atualizarUser) {
        User user = new User();
        user.setId(Math.toIntExact(atualizarUser.getId()));
        user.setNome(atualizarUser.getNome());
        user.setLogin(atualizarUser.getLogin());
        user.setSenha(atualizarUser.getSenha());

        repository.save(user);
    }
}



