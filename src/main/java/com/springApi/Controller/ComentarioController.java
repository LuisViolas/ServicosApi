package com.springApi.Controller;

import com.springApi.Model.Comentario;
import com.springApi.Model.ComentarioInput;
import com.springApi.Model.ComentarioModel;
import com.springApi.Model.OrdemServico;
import com.springApi.Repository.OrdemServicoRepository;
import com.springApi.exceptionhandler.EntidadeNaoEncontradaException;
import com.springApi.service.GestaoOrdemServicoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ordens-servico/{ordemServicoId}/comentarios")
public class ComentarioController {

    @Autowired
    private GestaoOrdemServicoService gestaoOrdemServicoService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @GetMapping
    public List<ComentarioModel> listar(@PathVariable Long ordemServicoId){
        OrdemServico ordemServico= ordemServicoRepository.findById(ordemServicoId)
                .orElseThrow(()->new EntidadeNaoEncontradaException("Ordem nao encontrada"));

        return toCollection(ordemServico.getComentarioList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ComentarioModel adicionar(@PathVariable Long ordemServicoId,@Valid @RequestBody ComentarioInput comentarioInput){

        Comentario comentario = gestaoOrdemServicoService.adicionarComentario(ordemServicoId,comentarioInput.getDescricao());

        return toModel(comentario);
    }

    private ComentarioModel toModel(Comentario comentario){
        return modelMapper.map(comentario,ComentarioModel.class);
    }

    private List<ComentarioModel> toCollection(List<Comentario> comentarios)
    {
        return comentarios.stream().map(comentario -> toModel(comentario))
                .collect(Collectors.toList());
    }
}
