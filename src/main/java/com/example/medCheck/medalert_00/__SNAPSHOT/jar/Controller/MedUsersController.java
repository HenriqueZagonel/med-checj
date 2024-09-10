package com.example.medCheck.medalert_00.__SNAPSHOT.jar.Controller;

import com.example.medCheck.medalert_00.__SNAPSHOT.jar.Dto.MedProductDto;
import com.example.medCheck.medalert_00.__SNAPSHOT.jar.Dto.MedUserDto;
import com.example.medCheck.medalert_00.__SNAPSHOT.jar.Services.MedUsersService;
import com.example.medCheck.medalert_00.__SNAPSHOT.jar.model.MedUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Users")
public class MedUsersController {
    @Autowired
    private MedUsersService medUsersService;
    //criar o usuario
    @PostMapping
    public ResponseEntity<MedUsers> criarCliente(@RequestBody MedUserDto medUserDto) {
        MedUsers medUsers = medUsersService.CriarUsuario(medUserDto);
        return ResponseEntity.ok(medUsers);
    }

    // Endpoint para atualizar o email do cliente
    @PutMapping("/{id}/email")
    public ResponseEntity<MedUsers> atualizarEmail(@PathVariable String cpf, @RequestBody String novoEmail) {
        MedUsers medUsers = medUsersService.atualizarEmail(cpf, novoEmail);
        return ResponseEntity.ok(medUsers);
    }

    // Endpoint para atualizar a lista de medicamentos do cliente
    @PutMapping("/{cpf}/medicamentos")
    public ResponseEntity<MedUsers> atualizarMedicamentos(@PathVariable String cpf, @RequestBody List<MedProductDto> medProductDto) {
        MedUsers medUsers = medUsersService.atualizarMedProductDtos(cpf, medProductDto);
        return ResponseEntity.ok(medUsers);
    }

    // Endpoint para buscar cliente por ID
    @GetMapping("/{cpf}")
    public ResponseEntity<MedUsers> buscarClientePorCpf(@PathVariable String cpf) {
        return ResponseEntity.of(medUsersService.buscarUserPorCpf(cpf));
    }

    @DeleteMapping("/{cpf}/medProducts/{medProductId}")
    public ResponseEntity<Void> deleteMedProduct(@PathVariable String cpf, @PathVariable String medProductId) {
        medUsersService.deleteMedProductById(cpf, medProductId);
        return ResponseEntity.noContent().build();
    }

}
