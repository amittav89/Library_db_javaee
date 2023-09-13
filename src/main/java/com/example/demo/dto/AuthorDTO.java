package com.example.demo.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class AuthorDTO
{
    @NotNull(message = "author id should not be null")
    private Long id;
    @NotNull(message = "first name should not be null")
    @NotBlank(message = "first name should not be blank")
    @NotEmpty(message = "first name should not be empty")
    private String firstName;
    @NotNull(message = "last name should not be null")
    @NotBlank(message = "last name should not be blank")
    @NotEmpty(message = "last name should not be empty")
    private String lastName;

    // these annotations are for user inputs, these block the user from entering invalid inputs
    // validation annotations
    @NotNull(message = "email should not be null")
    @NotBlank(message = "email should not be blank")
    @NotEmpty(message = "email should not be empty")
    @Email(message = "email should be valid")
    // these annotations are for the database, these block the database from accepting invalid inputs
    // persistence annotations
    @Column(unique = true, nullable = false, length = 70)
    private String email;
    private List<BookDTO> bookDTOS;

}
