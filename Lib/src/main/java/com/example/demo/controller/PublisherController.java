// PublisherController.java
package com.example.demo.controller;

import com.example.demo.dto.PublisherResponse;
import com.example.demo.model.Publisher;
import com.example.demo.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publishers")
@RequiredArgsConstructor
public class PublisherController {
    private final PublisherService publisherService;

    @GetMapping
    public ResponseEntity<List<PublisherResponse>> getAllPublishers() {
        return ResponseEntity.ok(publisherService.getAllPublishers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherResponse> getPublisherById(@PathVariable Long id) {
        return ResponseEntity.ok(publisherService.getPublisherById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<PublisherResponse>> searchPublishers(@RequestParam String query) {
        return ResponseEntity.ok(publisherService.searchPublishers(query));
    }

    @GetMapping("/active")
    public ResponseEntity<List<PublisherResponse>> getActivePublishers() {
        return ResponseEntity.ok(publisherService.getActivePublishers());
    }

    @PostMapping
    public ResponseEntity<PublisherResponse> createPublisher(@RequestBody Publisher publisher) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(publisherService.createPublisher(publisher));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublisherResponse> updatePublisher(
            @PathVariable Long id, @RequestBody Publisher publisher) {
        return ResponseEntity.ok(publisherService.updatePublisher(id, publisher));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable Long id) {
        publisherService.deletePublisher(id);
        return ResponseEntity.noContent().build();
    }
}