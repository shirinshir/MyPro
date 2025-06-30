// BookResponse.java
package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {
    private Long id;
    private String name;
    private Long authorId;
    private String authorName;
    private Long publisherId;
    private String publisherName;
    private Long readerId;
    private String readerName;
}