// BookRepository.java
package com.example.demo.repository;

import com.example.demo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByNameContaining(String name);
    List<Book> findByAuthorNameContaining(String authorName);
    List<Book> findByReaderIsNull();
    List<Book> findByReaderId(Long readerId);
}