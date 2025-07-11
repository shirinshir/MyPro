// PublisherRepository.java
package com.example.demo.repository;

import com.example.demo.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    List<Publisher> findByNameContainingIgnoreCase(String name);
    List<Publisher> findByActive(boolean active);  // <-- метод с active
}
