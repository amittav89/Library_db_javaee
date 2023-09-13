package com.example.demo.service;

import com.example.demo.Entity.AuthorDTO;
import com.example.demo.Entity.BookDTO;
import com.example.demo.entity.Author;
import com.example.demo.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class BookService implements BookServiceInterface {
    private final BookRepository bookRepository;
    private final AuthorService authorService;

    @Transactional

// Utility method to convert Book to BookDTO
    public BookDTO mapBookToDTOBook(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getBookId());
        bookDTO.setTitle(book.getTitle());
        // get author id
        Long authorId = book.getAuthor().getId();

        bookDTO.setAuthorId(authorId);
        return bookDTO;
    }

    // Utility method to convert BookDTO to Book
    @Transactional
    public Book mapDTOBookToBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        // get the author with the given authorId from the database
        AuthorDTO authorDTO = authorService.getAuthorByID(bookDTO.getAuthorId());
        Author author = authorService.mapDTOAuthorToAuthor(authorDTO);
        book.setAuthor(author);
        return book;
    }

    // getBookById() returns the book with the given id
    @Override
    public Optional<BookDTO> getBookById(Long id) {
        // Check if a book exists
        if (!bookRepository.existsById(id)) {
            throw new IllegalArgumentException("Book not found");
        }
        // Get the book DTO, and return it, using the mapBookToDTOBook() method with map() method of Optional
        return bookRepository.findById(id).map(this::mapBookToDTOBook);

    }

    // updateBook() updates the book with the given id with the given bookDTO
    @Override
    public BookDTO updateBook(Long id, BookDTO bookDTO) throws Exception {
        // Check if a book exists
        if (!bookRepository.existsById(id)) {
            throw new IllegalArgumentException("Book not found");
        }
        // create a new book with the given bookDTO
        Book book = mapDTOBookToBook(bookDTO);
        // Check if the author exists
        if (book.getAuthor() == null) {
            throw new Exception("Invalid author id.");
        }
        bookRepository.save(book);
        return bookDTO;
    }
    // createBook() creates a new book with the given bookDTO
    public BookDTO createBook(BookDTO bookDTO) throws Exception {
        Book book = mapDTOBookToBook(bookDTO);
        // Check if the author exists
        if (book.getAuthor() == null) {
            throw new Exception("Invalid author id.");
        }
        bookRepository.save(book);
        return bookDTO;
    }

    // getBooks() returns a list of all books
    @Override
    public List<BookDTO> getBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookDTO> bookDTOs = new ArrayList<>();
        for (Book book : books) {
            BookDTO bookDTO = mapBookToDTOBook(book);
            bookDTOs.add(bookDTO);
        }
        return bookDTOs;
    }
    // deleteBook() deletes the book with the given id
    @Override
    public void deleteBook(Long id) {
        // Check if a book exists
        if (!bookRepository.existsById(id)) {
            throw new IllegalArgumentException("Book not found");
        }
        bookRepository.deleteById(id);
    }
}
