package dev.eltoncosta.hostel_service.cadastro_hospede.repository;

import dev.eltoncosta.hostel_service.cadastro_hospede.entity.Convenio;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConvenioRepository extends MongoRepository<Convenio, String> {
}
