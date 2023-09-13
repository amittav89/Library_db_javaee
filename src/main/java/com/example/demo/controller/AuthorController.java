package com.example.demo.controller;

import com.example.demo.Entity.AuthorDTO;
import com.example.demo.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {
    @Autowired
    private AuthorService authorService;

// if successful, it will return the authors to the user
    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAuthors());
    }
    // Create
// CREATE, use @Valid to validate the inputs, if the inputs are invalid, it will throw an exception to the user
// @RequestBody is used to get the data from the user, and convert it to an AuthorDTO object, and pass it to the createAuthor() method
// if successful, it will return the created author to the user, else it will throw an exception to the user
    @PostMapping
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody @Valid AuthorDTO authorDTO) throws Exception {
// write here your code
    }
// Read

    @GetMapping("/{id}")
// READ ONE, returns the author with the given id to the user,
// if the author does not exist, it will throw an exception to the user
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Long id) throws Exception {
// example code:
// ResponseEntity.ok() returns the status code 200 to the user, and the author with the given id
// it is streamed to the user as JSON
        return ResponseEntity.ok(authorService.getAuthorByID(id));
    }

// UPDATE, use @Valid to validate the inputs, if the inputs are invalid, it will throw an exception to the user,
// @RequestBody is used to get the data from the user,
// and convert it to an AuthorDTO object, and pass it to the updateAuthor() method, it will return the updated
// author to the user, else it will throw an exception to the user
    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable Long id, @RequestBody @Valid AuthorDTO authorDTO) throws Exception
    {
// write here your code
        return null;
    }
// Delete
// DELETE, deletes the author with the given id,
// ResponseEntity<Void> is used to return a message to the user,
// if successful, it will return the message "deleted!",
// if not successful, it will return the message "not deleted"

    @DeleteMapping("/{id}")
// DELETE, deletes the author with the given id,
// ResponseEntity<Void> is used to return a message to the user,
// if successful, it will return the message "deleted!",
// if not successful, it will return the message "not deleted"
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id) {
        try {
            authorService.deleteAuthor(id);
            return new ResponseEntity<>("deleted!", HttpStatus.OK);
        } catch (Exception e) {
            // Assuming the exception indicates a failure to delete.
            // You can add more specific exception handling if needed.
            return new ResponseEntity<>("not deleted", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}