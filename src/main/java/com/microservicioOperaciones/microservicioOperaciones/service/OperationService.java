package com.microservicioOperaciones.microservicioOperaciones.service;

import com.microservicioOperaciones.microservicioOperaciones.repository.OperationRepository;
import com.microservicioOperaciones.microservicioOperaciones.model.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;


@Service
class OperationService {

    @Autowired
    private OperationRepository operationRepository;

    public Operation createOperation(Operation operation) {
        return operationRepository.save(operation);
    }

    public List<Operation> getAllOperations() {
        return operationRepository.findAll();
    }

    public Optional<Operation> updateOperation(String id, Operation operationDetails) {
        return operationRepository.findById(id)
                .map(operation -> {
                    operation.setName(operationDetails.getName());
                    operation.setDate(operationDetails.getDate());
                    operation.setDescription(operationDetails.getDescription());
                    return operationRepository.save(operation);
                });
    }

    public void deleteOperation(String id) {
        operationRepository.deleteById(id);
    }
}
