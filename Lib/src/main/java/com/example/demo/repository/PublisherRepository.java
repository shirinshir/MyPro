// PublisherRepository.java
package com.example.demo.repository;

import com.example.demo.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    List<Publisher> findByNameContaining(String name);
    List<Publisher> findByIsActive(boolean isActive);
}