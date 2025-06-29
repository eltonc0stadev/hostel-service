package dev.eltoncosta.hostel_service.cadastro_hospede.repository;

import dev.eltoncosta.hostel_service.cadastro_hospede.entity.Cadastro;
import dev.eltoncosta.hostel_service.entity.Funcionario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CadastroRepository extends MongoRepository<Cadastro, String> {

    List<Cadastro> findByFuncionario(Funcionario funcionario);

}
