// PersonRepository.java
package com.example.demo.repository;

import com.example.demo.model.Person;
import com.example.demo.model.PersonRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByEmail(String email);
    List<Person> findByRole(PersonRole role);
}