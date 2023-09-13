package com.example.demo.service;

import com.example.demo.dto.BookDTO;

import java.util.List;
import java.util.Optional;

public interface BookServiceInterface
{
    BookDTO createBook(BookDTO bookDTO) throws Exception;
    List<BookDTO> getBooks();
    Optional<BookDTO> getBookById(Long id) throws Exception;
    BookDTO updateBook(Long id, BookDTO bookDTO) throws Exception;
    void deleteBook(Long id) throws Exception;
}
