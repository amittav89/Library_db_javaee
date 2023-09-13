package com.example.demo.Dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class BookDTO
{
    @NotNull(message = "book id should not be null")
    @Column(nullable = false)
    private Long id;
    // these annotations are for user inputs, these block the user from entering invalid inputs
    // validation annotations
    @NotEmpty(message = "title should not be empty")
    @NotNull(message = "title should not be null")
    @NotBlank(message = "title should not be blank")
    @Length(min = 2, max = 70, message = "title should be between 2 and 70 characters")
    // these annotations are for the database, these block the database from accepting invalid inputs
    // persistence annotations
    @Column(unique = true, nullable = false, length = 70)
    private String title;

    @NotNull(message = "author id should not be null")
    @Column(nullable = false)
    private Long authorId;
}
