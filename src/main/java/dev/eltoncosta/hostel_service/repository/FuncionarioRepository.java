package dev.eltoncosta.hostel_service.repository;

import dev.eltoncosta.hostel_service.entity.Funcionario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FuncionarioRepository extends MongoRepository<Funcionario, String> {
}
