package com.example.medCheck.medalert_00.__SNAPSHOT.jar.Services;

import com.example.medCheck.medalert_00.__SNAPSHOT.jar.Dto.MedProductDto;
import com.example.medCheck.medalert_00.__SNAPSHOT.jar.Dto.MedUserDto;
import com.example.medCheck.medalert_00.__SNAPSHOT.jar.Repository.MedUsersRepository;
import com.example.medCheck.medalert_00.__SNAPSHOT.jar.model.MedUsers;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MedUsersService {

    @Autowired
    private MedUsersRepository medUsersRepository;


    public Optional<MedUsers> buscarUserPorCpf(String cpf) {
        return medUsersRepository.findByCpf(cpf);
    }





    public MedUsers CriarUsuario(MedUserDto medUserDto) {
        // Verificar se o CPF já está cadastrado
        Optional<MedUsers> clienteExistente = medUsersRepository.findByCpf(medUserDto.getCpf());
        if (clienteExistente.isPresent()) {
            throw new IllegalArgumentException("Cliente com CPF já cadastrado");
        }
            MedUsers medUsers = new MedUsers(medUserDto.getCpf(),medUserDto.getEmail(), medUserDto.getMedProductDto()
                .stream()
                .map(this::mapMedProduct)
                .collect(Collectors.toList()));

        return medUsersRepository.save(medUsers);
    }

    public MedUsers atualizarEmail(String cpf, String novoEmail) {
        MedUsers medUsers = medUsersRepository.findByCpf(cpf)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
        medUsers.setEmail(novoEmail);
        return medUsersRepository.save(medUsers);
    }

    public MedUsers atualizarMedProductDtos(String cpf, List<MedProductDto> medProductDtos) {
        MedUsers medUsers = medUsersRepository.findByCpf(cpf)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
        // Inicializa a lista de produtos médicos se estiver nula
        List<MedProductDto> medProducts = medUsers.getMedProductDto();
        if (medProducts == null) {
            medProducts = new ArrayList<>();
        }
        for (MedProductDto medProductDto : medProductDtos) {
            if (medProductDto.getId() == null) {
                medProductDto.setId(UUID.randomUUID().toString());
                medProducts.add(medProductDto);
            } else {
                boolean found = false;
                for (int i = 0; i < medProducts.size(); i++) {
                    if (medProducts.get(i).getId().equals(medProductDto.getId())) {
                        medProducts.set(i, medProductDto);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    medProducts.add(medProductDto);
                }
            }
        }
        medUsers.setMedProductDto(medProducts);
        return medUsersRepository.save(medUsers);
    }
    private MedProductDto mapMedProduct(MedProductDto medProductDto) {
        return new MedProductDto(
                medProductDto.getNomeMed(),
                medProductDto.getPosologia(),
                medProductDto.getQuantidadeComprada(),
                medProductDto.isReceberAvisoCompra(),
                medProductDto.getDiasAntesAvisoCompra(),
                medProductDto.isReceberAvisoDiario(),
                medProductDto.getHorarioAviso()
        );
    }

    public void deleteMedProductById(String cpf, String medProductId) {
        Optional<MedUsers> userOptional = medUsersRepository.findById(cpf);
        if (userOptional.isPresent()) {
            MedUsers medUsers = userOptional.get();
            List<MedProductDto> medProducts = medUsers.getMedProductDto();
            medProducts.removeIf(medProduct -> medProduct.getId().equals(medProductId));
            medUsers.setMedProductDto(medProducts);
            medUsersRepository.save(medUsers);
        } else {
            throw new EntityNotFoundException("Usuário com CPF " + cpf + " não encontrado.");
        }
    }

}


