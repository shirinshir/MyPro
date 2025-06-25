// PersonController.java
package com.example.demo.controller;

import com.example.demo.dto.PersonResponse;
import com.example.demo.model.Person;
import com.example.demo.model.PersonRole;
import com.example.demo.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @GetMapping
    public ResponseEntity<List<PersonResponse>> getAllPersons() {
        return ResponseEntity.ok(personService.getAllPersons());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonResponse> getPersonById(@PathVariable Long id) {
        return ResponseEntity.ok(personService.getPersonById(id));
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<PersonResponse>> getPersonsByRole(@PathVariable PersonRole role) {
        return ResponseEntity.ok(personService.getPersonsByRole(role));
    }

    @PostMapping
    public ResponseEntity<PersonResponse> createPerson(@RequestBody Person person) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(personService.createPerson(person));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonResponse> updatePerson(
            @PathVariable Long id, @RequestBody Person person) {
        return ResponseEntity.ok(personService.updatePerson(id, person));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }
}