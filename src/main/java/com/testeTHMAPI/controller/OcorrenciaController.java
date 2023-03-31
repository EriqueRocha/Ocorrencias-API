package com.testeTHMAPI.controller;

import com.testeTHMAPI.dto.NovaOcorrencia;
import com.testeTHMAPI.model.Ocorrencia;
import com.testeTHMAPI.repository.OcorrenciaRepository;
import com.testeTHMAPI.service.OcorrenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController // aponta que é uma classe que controla as requisiçõies
@RequestMapping("/ocorrencias") //todas as requisições que precisão passar por "/ocorrencias" verificam esta classe
public class OcorrenciaController {

   // private static final String caminhoImagens = "C:\\Users\\eriqu\\Documents\\projetos Spring\\docs testeTHM";

    @Autowired // cria um método construtor dessa classe
    private OcorrenciaRepository repository;

    @Autowired
    private OcorrenciaService service;

    @GetMapping("/list") // mapeamento que chama o nétodo de consulta do banco de dados
    public List<Ocorrencia> findAll(){
        return  repository.findAll();
    }

    @PostMapping("/cadastro")//mapeamento que chama o método de salvar no banco de dados
    public void save(@RequestBody NovaOcorrencia novaOcorrencia){
        service.save(novaOcorrencia);
//        @PostMapping("/cadastro")
//        public void save(@RequestBody NovaOcorrencia novaOcorrencia,
//                @RequestParam("file")MultipartFile arquivo1,
//                @RequestParam("file")MultipartFile arquivo2){

        //verifica se o caminmho para a pasta existe
//        try {
//            if (!arquivo1.isEmpty() || !arquivo2.isEmpty()){
//                byte[] bytes1 = arquivo1.getBytes();
//                Path caminho1 = Paths.get(caminhoImagens+String.valueOf(novaOcorrencia.getUserId())+arquivo1.getOriginalFilename());
//                Files.write(caminho1, bytes1);
//
//                byte[] bytes2 = arquivo2.getBytes();
//                Path caminho2 = Paths.get(caminhoImagens+String.valueOf(novaOcorrencia.getUserId())+arquivo2.getOriginalFilename());
//                Files.write(caminho2, bytes2);
//
//            }
//
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//
//        service.save(novaOcorrencia, arquivo1, arquivo2);


    }

    @DeleteMapping("/deletar/{id}") //mapeamento que chama o método de deletar e envia o Id da ocorrência
    public void delete(Integer id){
        if(repository.existsById(id)){
            repository.deleteById(id);
        }
    }
}



