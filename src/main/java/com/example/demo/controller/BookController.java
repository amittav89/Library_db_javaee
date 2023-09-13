package com.example.demo.controller;


import com.example.demo.dto.BookDTO;
import com.example.demo.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController
{

    @Autowired
    private BookService bookService;
    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody @Valid BookDTO bookDTO) throws Exception {
        BookDTO savedBookDTO = bookService.createBook(bookDTO);
        return new ResponseEntity<>(savedBookDTO, HttpStatus.CREATED);
    }

    // Read All
    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
// write here your code
        return null;
    }

    // Read
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
// write here your code
        return null;
    }
    // Update

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody @Valid BookDTO bookDTO) throws Exception
    {
// write here your code
        return null;
    }
    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) throws Exception {
// write here your code
        return null;
    }

}
