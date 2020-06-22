package com.springApi.Model;

import com.springApi.exceptionhandler.NegocioException;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cliente cliente;

    @NotNull
    private String descricao;
    @NotNull
    private BigDecimal preco;

    @Enumerated(EnumType.STRING) //para armazenar a string
    private StatusOrdemServico status;

    private OffsetDateTime dataAbertura;

    private OffsetDateTime dataFinalizacao;

    @OneToMany(mappedBy = "ordemServico")
    private List<Comentario> comentarioList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public StatusOrdemServico getStatus() {
        return status;
    }

    public void setStatus(StatusOrdemServico status) {
        this.status = status;
    }

    public OffsetDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(OffsetDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public OffsetDateTime getDataFinalizacao() {
        return dataFinalizacao;
    }

    public void setDataFinalizacao(OffsetDateTime dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
    }

    public List<Comentario> getComentarioList() {
        return comentarioList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdemServico that = (OrdemServico) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(cliente, that.cliente) &&
                Objects.equals(descricao, that.descricao) &&
                Objects.equals(preco, that.preco) &&
                status == that.status &&
                Objects.equals(dataAbertura, that.dataAbertura) &&
                Objects.equals(dataFinalizacao, that.dataFinalizacao) &&
                Objects.equals(comentarioList, that.comentarioList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cliente, descricao, preco, status, dataAbertura, dataFinalizacao, comentarioList);
    }

    public void setComentarioList(List<Comentario> comentarioList) {
        this.comentarioList = comentarioList;
    }

    public boolean podeSerFinalizada(){
        return StatusOrdemServico.ABERTO.equals(getStatus());
    }

    public boolean naoPodeSerFinalizada(){
        return !podeSerFinalizada();
    }

    public void finalizar(){
        if(naoPodeSerFinalizada()){
            throw new NegocioException("Ordem de serviço nao pode ser finalizada");
        }

        setStatus(StatusOrdemServico.FINALIZADA);
        setDataFinalizacao(OffsetDateTime.now());
    }
}
