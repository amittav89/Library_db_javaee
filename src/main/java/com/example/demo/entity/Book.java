package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Book extends BaseEntity
{
    @Column(unique = true)
    @NotNull(message = "title should not be null")
    @NotEmpty(message = "title should not be empty")
    @NotBlank(message = "title should not be blank")
    private String title;
    // ManyToOne is used to define the relationship between Book and Author
    @ManyToOne
    // JsonIgnore is used to prevent infinite recursion
    @JsonIgnore
    // JoinColumn is used to define the foreign key column
    @JoinColumn(name = "author_id", nullable = false,
            foreignKey = @ForeignKey(name = "author_id_fk"))
    private Author author;
    public Book(Long id, @NotNull String title, Author author) {
        this.setId(id);
        this.title = title;
        this.author = author;

    }



}
