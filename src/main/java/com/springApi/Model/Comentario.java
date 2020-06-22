package com.springApi.Model;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
public class Comentario {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    @ManyToOne
    private OrdemServico ordemServico;

    private OffsetDateTime dataEnvio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public OrdemServico getOrdemServico() {
        return ordemServico;
    }

    public void setOrdemServico(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
    }

    public OffsetDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(OffsetDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comentario that = (Comentario) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(descricao, that.descricao) &&
                Objects.equals(ordemServico, that.ordemServico) &&
                Objects.equals(dataEnvio, that.dataEnvio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descricao, ordemServico, dataEnvio);
    }
}
