package com.example.medCheck.medalert_00.__SNAPSHOT.jar.Repository;

import com.example.medCheck.medalert_00.__SNAPSHOT.jar.model.MedUsers;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface MedUsersRepository extends MongoRepository<MedUsers, String> {

    Optional<MedUsers>findByCpf(String cpf);

}
