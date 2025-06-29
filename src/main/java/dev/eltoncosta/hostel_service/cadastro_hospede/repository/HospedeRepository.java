package dev.eltoncosta.hostel_service.cadastro_hospede.repository;

import dev.eltoncosta.hostel_service.cadastro_hospede.entity.Hospede;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface HospedeRepository extends MongoRepository<Hospede, String> {
    Optional<Hospede> findByCpf(String cpf);
}
