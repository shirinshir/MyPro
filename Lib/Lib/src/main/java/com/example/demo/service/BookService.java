// BookService.java
package com.example.demo.service;

import com.example.demo.dto.BookRequest;
import com.example.demo.dto.BookResponse;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Book;
import com.example.demo.model.Person;
import com.example.demo.model.Publisher;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final PersonRepository personRepository;
    private final PublisherRepository publisherRepository;

    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(this::mapToBookResponse)
                .collect(Collectors.toList());
    }

    public BookResponse getBookById(Long id) {
        return bookRepository.findById(id)
                .map(this::mapToBookResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
    }

    public List<BookResponse> searchBooks(String query) {
        return bookRepository.findByNameContaining(query).stream()
                .map(this::mapToBookResponse)
                .collect(Collectors.toList());
    }

    public BookResponse createBook(BookRequest bookRequest) {
        Book book = new Book();
        book.setName(bookRequest.getName());

        if (bookRequest.getAuthorId() != null) {
            Person author = personRepository.findById(bookRequest.getAuthorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Author not found"));
            book.setAuthor(author);
        }

        if (bookRequest.getPublisherId() != null) {
            Publisher publisher = publisherRepository.findById(bookRequest.getPublisherId())
                    .orElseThrow(() -> new ResourceNotFoundException("Publisher not found"));
            book.setPublisher(publisher);
        }

        if (bookRequest.getReaderId() != null) {
            Person reader = personRepository.findById(bookRequest.getReaderId())
                    .orElseThrow(() -> new ResourceNotFoundException("Reader not found"));
            book.setReader(reader);
        }

        Book savedBook = bookRepository.save(book);
        return mapToBookResponse(savedBook);
    }

    public BookResponse updateBook(Long id, BookRequest bookRequest) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        book.setName(bookRequest.getName());

        if (bookRequest.getAuthorId() != null) {
            Person author = personRepository.findById(bookRequest.getAuthorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Author not found"));
            book.setAuthor(author);
        } else {
            book.setAuthor(null);
        }

        if (bookRequest.getPublisherId() != null) {
            Publisher publisher = publisherRepository.findById(bookRequest.getPublisherId())
                    .orElseThrow(() -> new ResourceNotFoundException("Publisher not found"));
            book.setPublisher(publisher);
        } else {
            book.setPublisher(null);
        }

        if (bookRequest.getReaderId() != null) {
            Person reader = personRepository.findById(bookRequest.getReaderId())
                    .orElseThrow(() -> new ResourceNotFoundException("Reader not found"));
            book.setReader(reader);
        } else {
            book.setReader(null);
        }

        Book updatedBook = bookRepository.save(book);
        return mapToBookResponse(updatedBook);
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book not found");
        }
        bookRepository.deleteById(id);
    }

    public List<BookResponse> getAvailableBooks() {
        return bookRepository.findByReaderIsNull().stream()
                .map(this::mapToBookResponse)
                .collect(Collectors.toList());
    }

    public List<BookResponse> getBooksBorrowedByReader(Long readerId) {
        return bookRepository.findByReaderId(readerId).stream()
                .map(this::mapToBookResponse)
                .collect(Collectors.toList());
    }

    private BookResponse mapToBookResponse(Book book) {
        BookResponse response = new BookResponse();
        response.setId(book.getId());
        response.setName(book.getName());

        if (book.getAuthor() != null) {
            response.setAuthorId(book.getAuthor().getId());
            response.setAuthorName(book.getAuthor().getName());
        }

        if (book.getPublisher() != null) {
            response.setPublisherId(book.getPublisher().getId());
            response.setPublisherName(book.getPublisher().getName());
        }

        if (book.getReader() != null) {
            response.setReaderId(book.getReader().getId());
            response.setReaderName(book.getReader().getName());
        }

        return response;
    }
}