// PersonResponse.java
package com.example.demo.dto;

import com.example.demo.model.PersonRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonResponse {
    private Long id;
    private String name;
    private LocalDate birthdate;
    private String email;
    private String phone;
    private String address;
    private PersonRole role;
}