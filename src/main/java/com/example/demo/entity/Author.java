package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.util.List;


@EqualsAndHashCode()
@Entity
@Data
public class Author
{
    @Id
    Long id;
    // these annotations are for user inputs, these block the user from entering invalid inputs
    // data validation annotations
    @Column(nullable = false, length = 50)
    private String firstName;
    @Column(nullable = false, length = 50)
    private String lastName;
    @Column(unique = true)
    private String email;
    // JsonIgnore is used to prevent infinite recursion, it is used to ignore the books field when converting to JSON
    // this is because the book field is a list of books, and each book has an author, and each author has a list of books
    @JsonIgnore
    @OneToMany(mappedBy = "author")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Book> books;

    public Author(Long id, String firstName, String lastName, String email, List<Book> books) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.books = books;
    }

    public Author() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
