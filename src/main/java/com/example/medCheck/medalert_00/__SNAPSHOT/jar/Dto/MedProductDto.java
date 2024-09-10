package com.example.medCheck.medalert_00.__SNAPSHOT.jar.Dto;

import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;

public class MedProductDto {

   @NotBlank
    private String id;
    @NotBlank(message = "nomeMed do medicamento é obrigatório")
    private String nomeMed;
    @NotBlank(message = "Posologia é obrigatória")
    private String posologia;
    @NotNull(message = "Quantidade comprada é obrigatória")
    @Min(value = 1, message = "Quantidade comprada deve ser pelo menos 1")
    private Integer quantidadeComprada;
    private boolean receberAvisoCompra = false;
    private Integer diasAntesAvisoCompra;
    private boolean receberAvisoDiario = false;
    private String horarioAviso;

    public MedProductDto() {

    }

    public MedProductDto( String nomeMed, String posologia, Integer quantidadeComprada, boolean receberAvisoCompra, Integer diasAntesAvisoCompra, boolean receberAvisoDiario, String horarioAviso) {
        this.nomeMed = nomeMed;
        this.posologia = posologia;
        this.quantidadeComprada = quantidadeComprada;
        this.receberAvisoCompra = receberAvisoCompra;
        this.diasAntesAvisoCompra = diasAntesAvisoCompra;
        this.receberAvisoDiario = receberAvisoDiario;
        this.horarioAviso = horarioAviso;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomeMed() {
        return nomeMed;
    }

    public void setNomeMed(String nomeMed) {
        this.nomeMed = nomeMed;
    }

    public String getPosologia() {
        return posologia;
    }

    public void setPosologia(String posologia) {
        this.posologia = posologia;
    }

    public Integer getQuantidadeComprada() {
        return quantidadeComprada;
    }

    public void setQuantidadeComprada(Integer quantidadeComprada) {
        this.quantidadeComprada = quantidadeComprada;
    }

    public boolean isReceberAvisoCompra() {
        return receberAvisoCompra;
    }

    public void setReceberAvisoCompra(boolean receberAvisoCompra) {
        this.receberAvisoCompra = receberAvisoCompra;
    }

    public Integer getDiasAntesAvisoCompra() {
        return diasAntesAvisoCompra;
    }

    public void setDiasAntesAvisoCompra(Integer diasAntesAvisoCompra) {
        this.diasAntesAvisoCompra = diasAntesAvisoCompra;
    }

    public boolean isReceberAvisoDiario() {
        return receberAvisoDiario;
    }

    public void setReceberAvisoDiario(boolean receberAvisoDiario) {
        this.receberAvisoDiario = receberAvisoDiario;
    }

    public String getHorarioAviso() {
        return horarioAviso;
    }

    public void setHorarioAviso(String horarioAviso) {
        this.horarioAviso = horarioAviso;
    }
}
