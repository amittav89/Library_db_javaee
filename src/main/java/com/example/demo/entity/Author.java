package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.awt.print.Book;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Author extends BaseEntity
{
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
    // OneToMany is used to define the relationship between Author and Book
    @OneToMany(mappedBy = "author")
    // OnDelete is used to delete all books of an author when the author is deleted
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Book> books;

}
