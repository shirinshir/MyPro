// PersonService.java
package com.example.demo.service;

import com.example.demo.dto.PersonResponse;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Person;
import com.example.demo.model.PersonRole;
import com.example.demo.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    public List<PersonResponse> getAllPersons() {
        return personRepository.findAll().stream()
                .map(this::mapToPersonResponse)
                .collect(Collectors.toList());
    }

    public PersonResponse getPersonById(Long id) {
        return personRepository.findById(id)
                .map(this::mapToPersonResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found"));
    }

    public List<PersonResponse> getPersonsByRole(PersonRole role) {
        return personRepository.findByRole(role).stream()
                .map(this::mapToPersonResponse)
                .collect(Collectors.toList());
    }

    public PersonResponse createPerson(Person person) {
        Person savedPerson = personRepository.save(person);
        return mapToPersonResponse(savedPerson);
    }

    public PersonResponse updatePerson(Long id, Person personDetails) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found"));

        person.setName(personDetails.getName());
        person.setBirthdate(personDetails.getBirthdate());
        person.setEmail(personDetails.getEmail());
        person.setPhone(personDetails.getPhone());
        person.setAddress(personDetails.getAddress());
        person.setRole(personDetails.getRole());

        Person updatedPerson = personRepository.save(person);
        return mapToPersonResponse(updatedPerson);
    }

    public void deletePerson(Long id) {
        if (!personRepository.existsById(id)) {
            throw new ResourceNotFoundException("Person not found");
        }
        personRepository.deleteById(id);
    }

    private PersonResponse mapToPersonResponse(Person person) {
        PersonResponse response = new PersonResponse();
        response.setId(person.getId());
        response.setName(person.getName());
        response.setBirthdate(person.getBirthdate());
        response.setEmail(person.getEmail());
        response.setPhone(person.getPhone());
        response.setAddress(person.getAddress());
        response.setRole(person.getRole());
        return response;
    }
}