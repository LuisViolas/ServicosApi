package com.springApi.service;

import com.springApi.Model.Cliente;
import com.springApi.Model.Comentario;
import com.springApi.Model.OrdemServico;
import com.springApi.Model.StatusOrdemServico;
import com.springApi.Repository.ClienteRepository;
import com.springApi.Repository.ComentarioRepository;
import com.springApi.Repository.OrdemServicoRepository;
import com.springApi.exceptionhandler.EntidadeNaoEncontradaException;
import com.springApi.exceptionhandler.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class GestaoOrdemServicoService {
    @Autowired
    private ComentarioRepository comentarioRepository;


    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public OrdemServico criar (OrdemServico ordemServico)
    {
        Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
                .orElseThrow(()-> new NegocioException("Cliente nao encontrado"));

        ordemServico.setCliente(cliente);
        ordemServico.setStatus(StatusOrdemServico.ABERTO);
        ordemServico.setDataAbertura(OffsetDateTime.now());
      return ordemServicoRepository.save(ordemServico);
    }
    public Comentario adicionarComentario(Long ordemServicoId, String descricao)
    {
        OrdemServico ordemServico = buscarOrdemServico(ordemServicoId);

        Comentario comentario = new Comentario();
        comentario.setDataEnvio(OffsetDateTime.now());
        comentario.setDescricao(descricao);
        comentario.setOrdemServico(ordemServico);

        return comentarioRepository.save(comentario);
    }

    public void finalizar(Long ordemServicoId){
        OrdemServico ordemServico = buscarOrdemServico(ordemServicoId);

        ordemServico.finalizar();

        ordemServicoRepository.save(ordemServico);
    }

    private OrdemServico buscarOrdemServico(Long ordemServicoId) {
        return ordemServicoRepository.findById(ordemServicoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de servico nao encontrada"));
    }
}
