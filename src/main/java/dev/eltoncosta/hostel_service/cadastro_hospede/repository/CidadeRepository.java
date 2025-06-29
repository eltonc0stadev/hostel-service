package dev.eltoncosta.hostel_service.cadastro_hospede.repository;

import dev.eltoncosta.hostel_service.cadastro_hospede.entity.Cidade;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CidadeRepository extends MongoRepository<Cidade, String> {
    boolean existsByCidade(String cidade);
    Optional<Cidade> findByName(String name);
}
