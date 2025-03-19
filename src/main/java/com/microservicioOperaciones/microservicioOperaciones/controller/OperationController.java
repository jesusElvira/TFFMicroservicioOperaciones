package com.microservicioOperaciones.microservicioOperaciones.controller;

import com.microservicioOperaciones.microservicioOperaciones.model.Operation;
import com.microservicioOperaciones.microservicioOperaciones.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/operations")
class OperationController {

    @Autowired
    private OperationRepository operationRepository;

    @CrossOrigin(origins = "*")
    @PostMapping
    public ResponseEntity<Operation> createOperation(@RequestBody Operation operation) {
        Operation savedOperation = operationRepository.save(operation);
        return new ResponseEntity<>(savedOperation, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public List<Operation> getAllOperations() {
        return operationRepository.findAll();
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/{id}")
    public ResponseEntity<Operation> updateOperation(@PathVariable String id, @RequestBody Operation operationDetails) {
        return operationRepository.findById(id)
                .map(operation -> {
                    operation.setName(operationDetails.getName());
                    operation.setDate(operationDetails.getDate());
                    operation.setDescription(operationDetails.getDescription());
                    Operation updatedOperation = operationRepository.save(operation);
                    return new ResponseEntity<>(updatedOperation, HttpStatus.OK);
                }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOperation(@PathVariable String id) {
        return operationRepository.findById(id)
                .map(operation -> {
                    operationRepository.delete(operation);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}