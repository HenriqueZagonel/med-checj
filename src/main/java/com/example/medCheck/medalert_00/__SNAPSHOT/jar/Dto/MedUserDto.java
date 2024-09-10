package com.example.medCheck.medalert_00.__SNAPSHOT.jar.Dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class MedUserDto {


    @NotBlank(message = "CPF é obrigatório")
    @CPF
    @Id
    @Valid
    private String cpf;
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email invalido")
    private String email;

    private List<MedProductDto> medProductDto=new ArrayList<>();

    public MedUserDto() {
    }

    public MedUserDto( String cpf, String email, List<MedProductDto> medProductDto){
        this.cpf = cpf;
        this.email = email;
        this.medProductDto = new ArrayList<>();
    }




    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<MedProductDto> getMedProductDto() {
        return medProductDto;
    }

    public void setMedProductDto(List<MedProductDto> medProductDto) {
        this.medProductDto = medProductDto;
    }
}
