package com.testeTHMAPI.controller;

import com.testeTHMAPI.config.CredenciaisDTO;
import com.testeTHMAPI.config.JwtService;
import com.testeTHMAPI.config.TokenDTO;
import com.testeTHMAPI.dto.AtualizarUser;
import com.testeTHMAPI.dto.NovoUser;
import com.testeTHMAPI.dto.OcorrenciaDTO;
import com.testeTHMAPI.dto.UserDTO;
import com.testeTHMAPI.exception.SenhaInvalidaException;
import com.testeTHMAPI.model.Ocorrencia;
import com.testeTHMAPI.model.User;
import com.testeTHMAPI.repository.UserRepository;
import com.testeTHMAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserService service;

    private final JwtService jwtService;

    public UserController(UserRepository repository, UserService service, JwtService jwtService) {
        this.repository = repository;
        this.service = service;
        this.jwtService = jwtService;
    }

    @GetMapping("/list")
    public List<User> findAll(){
        return service.findAllWithNumOcorrencias();

    }

    //este seria um get normal, exibindo todas as informaçõede um user, incluindo a senha
//    @GetMapping("/{id}")
//    public User findById(@PathVariable Integer id) {
//        User user = repository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
//        List<Ocorrencia> ocorrencias = user.getOcorrencias();
//        ocorrencias.forEach(o -> o.setUserId(id));
//        return user;
//    }


    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciaisDTO){
        try{
            User user = User.builder()
                    .login(credenciaisDTO.getLogin())
                    .senha(credenciaisDTO.getSenha())
                    .build();
            UserDetails userAutenticado = service.autenticar(user);
            String token = jwtService.gerarToken(user);

            return new TokenDTO(user.getLogin(), token);

        }catch (UsernameNotFoundException | SenhaInvalidaException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }


    @GetMapping("/{id}") //6
    public UserDTO findById(@PathVariable Integer id) {
        //testa se existe um usuário com o Id informado
        User user = repository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setNome(user.getNome());
        userDTO.setLogin(user.getLogin());
        userDTO.setAdmin(user.isAdmin());

        List<OcorrenciaDTO> ocorrenciasDTO = new ArrayList<>();
        List<Ocorrencia> ocorrencias = user.getOcorrencias();
        for (Ocorrencia ocorrencia : ocorrencias) {
            OcorrenciaDTO ocorrenciaDTO = new OcorrenciaDTO();
            ocorrenciaDTO.setId(ocorrencia.getId());
            ocorrenciaDTO.setData(ocorrencia.getData());
            ocorrenciaDTO.setLocal(ocorrencia.getLocal());
            ocorrenciaDTO.setIntensidade(ocorrencia.getIntensidade());
            ocorrenciaDTO.setClassificacao(ocorrencia.getClassificacao());
            ocorrenciasDTO.add(ocorrenciaDTO);
        }
        userDTO.setOcorrencias(ocorrenciasDTO);

        return userDTO;
    }

    @PostMapping("/cadastro")
    public void save(@RequestBody NovoUser novoUser){
        service.save(novoUser);

    }

    @PutMapping("/alterar/{id}") //chama o método de edição das informações salvas no banco passando o DTO do necessário para se ter um novo user
    public void update(@RequestBody AtualizarUser atualizarUser){
        service.update(atualizarUser);
    }

    @DeleteMapping("/deletar/{id}")//chama a função para deletar
    public void delete(Integer id){
        if(repository.existsById(id)){//verifica se o Id existe
            repository.deleteById(id);//deleta o id
        }
    }
}



