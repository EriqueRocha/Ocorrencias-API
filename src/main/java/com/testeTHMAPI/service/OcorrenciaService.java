package com.testeTHMAPI.service;

import com.testeTHMAPI.dto.NovaOcorrencia;
import com.testeTHMAPI.model.Imagens;
import com.testeTHMAPI.model.Ocorrencia;
import com.testeTHMAPI.repository.OcorrenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;


@Service//anotação que mostra onde se encontra as regras de negócio
public class OcorrenciaService{

    @Autowired
    private OcorrenciaRepository repository;


//    private String caminhoImagens;
//
//    public OcorrenciaService(String caminhoImagens) {
//        this.caminhoImagens = caminhoImagens;
//    }

    //método de salvamento de uma ocorrencia

    public void save(NovaOcorrencia novaOcorrencia) {

        //public void save(NovaOcorrencia novaOcorrencia, MultipartFile imagem1, MultipartFile imagem2) {

        Ocorrencia ocorrencia = new Ocorrencia();

        ocorrencia.setUserId(novaOcorrencia.getUserId());
        ocorrencia.setData(LocalDateTime.now());
        ocorrencia.setLocal(novaOcorrencia.getLocal());
        ocorrencia.setIntensidade(novaOcorrencia.getIntensidade());
        ocorrencia.setClassificacao(novaOcorrencia.getClassificacao());

        Imagens imagens = new Imagens();

//        try {
//            if (!imagem1.isEmpty()) {
//                byte[] bytes1 = imagem1.getBytes();
//                String nomeImagem1 = novaOcorrencia.getUserId() + "_" + imagem1.getOriginalFilename();
//                Path caminho1 = Paths.get(caminhoImagens + nomeImagem1);
//                Files.write(caminho1, bytes1);
//                imagens.setNomeImagem1(nomeImagem1);
//            }
//            if (!imagem2.isEmpty()) {
//                byte[] bytes2 = imagem2.getBytes();
//                String nomeImagem2 = novaOcorrencia.getUserId() + "_" + imagem2.getOriginalFilename();
//                Path caminho2 = Paths.get(caminhoImagens + nomeImagem2);
//                Files.write(caminho2, bytes2);
//                imagens.setNomeImagem2(nomeImagem2);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        ocorrencia.setImagem(imagens);

        repository.save(ocorrencia);
    }
}



