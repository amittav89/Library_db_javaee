package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@EqualsAndHashCode()
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Book {
    @Id
    Long id;

    @Column(unique = true)
    @NotNull(message = "title should not be null")
    @NotEmpty(message = "title should not be empty")
    @NotBlank(message = "title should not be blank")
    String title;
    // ManyToOne is used to define the relationship between Book and Author
    @ManyToOne
    // JsonIgnore is used to prevent infinite recursion
    @JsonIgnore
    // JoinColumn is used to define the foreign key column
    @JoinColumn(name = "author_id", nullable = false,
            foreignKey = @ForeignKey(name = "author_id_fk"))
    Author author;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

}
