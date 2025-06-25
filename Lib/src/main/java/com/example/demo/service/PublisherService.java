// PublisherService.java
package com.example.demo.service;

import com.example.demo.dto.PublisherResponse;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Publisher;
import com.example.demo.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublisherService {
    private final PublisherRepository publisherRepository;

    public List<PublisherResponse> getAllPublishers() {
        return publisherRepository.findAll().stream()
                .map(this::mapToPublisherResponse)
                .collect(Collectors.toList());
    }

    public PublisherResponse getPublisherById(Long id) {
        return publisherRepository.findById(id)
                .map(this::mapToPublisherResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Publisher not found"));
    }

    public List<PublisherResponse> searchPublishers(String query) {
        return publisherRepository.findByNameContaining(query).stream()
                .map(this::mapToPublisherResponse)
                .collect(Collectors.toList());
    }

    public PublisherResponse createPublisher(Publisher publisher) {
        Publisher savedPublisher = publisherRepository.save(publisher);
        return mapToPublisherResponse(savedPublisher);
    }

    public PublisherResponse updatePublisher(Long id, Publisher publisherDetails) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publisher not found"));

        publisher.setName(publisherDetails.getName());
        publisher.setActive(publisherDetails.isActive());

        Publisher updatedPublisher = publisherRepository.save(publisher);
        return mapToPublisherResponse(updatedPublisher);
    }

    public void deletePublisher(Long id) {
        if (!publisherRepository.existsById(id)) {
            throw new ResourceNotFoundException("Publisher not found");
        }
        publisherRepository.deleteById(id);
    }

    public List<PublisherResponse> getActivePublishers() {
        return publisherRepository.findByIsActive(true).stream()
                .map(this::mapToPublisherResponse)
                .collect(Collectors.toList());
    }

    private PublisherResponse mapToPublisherResponse(Publisher publisher) {
        PublisherResponse response = new PublisherResponse();
        response.setId(publisher.getId());
        response.setName(publisher.getName());
        response.setActive(publisher.isActive());
        return response;
    }
}