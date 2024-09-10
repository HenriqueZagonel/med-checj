package com.example.medCheck.medalert_00.__SNAPSHOT.jar.model;


import com.example.medCheck.medalert_00.__SNAPSHOT.jar.Dto.MedProductDto;
import com.example.medCheck.medalert_00.__SNAPSHOT.jar.Dto.MedUserDto;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document(collection  = "MED_USERS")
public class MedUsers {



    @Id
    @CPF
    private String cpf;
    private String email;
    @Valid
    private List<MedProductDto> medProductDto = new ArrayList<>();

    public MedUsers() {
    }

    public MedUsers(String cpf, String email, List<MedProductDto> medProductDto) {
        this.cpf = cpf;
        this.email = email;
        this.medProductDto = medProductDto;
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
    public void addMedProductDto(MedProductDto medProduct){
        if (medProduct.getId()==null){
            medProduct.setId(UUID.randomUUID().toString());
        }this.medProductDto.add(medProduct);
    }
    public  void removeMedProductDto(String id){
        this.medProductDto.removeIf(product-> product.getId().equals(id));
    }
}
