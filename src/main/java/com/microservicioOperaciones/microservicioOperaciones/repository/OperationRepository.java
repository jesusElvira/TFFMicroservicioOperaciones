package com.microservicioOperaciones.microservicioOperaciones.repository;

import com.microservicioOperaciones.microservicioOperaciones.model.Operation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OperationRepository extends MongoRepository<Operation, String> {}