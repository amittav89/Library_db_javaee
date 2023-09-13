package com.example.demo.service;


import com.example.demo.dto.AuthorDTO;
import com.example.demo.dto.BookDTO;
import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import com.example.demo.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService implements AuthorServiceInterface {
    private final AuthorRepository authorRepository;
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    @Transactional
    // Utility method to convert Author to AuthorDTO
    public AuthorDTO mapAuthorToDTOAuthor(Author author) {

        if (author != null) {
            AuthorDTO authorDTO = new AuthorDTO();
            authorDTO.setId(author.getId());
            authorDTO.setFirstName(author.getFirstName());
            authorDTO.setLastName(author.getLastName());
            authorDTO.setEmail(author.getEmail());
            // Get all books of the author, and convert them to DTOs
            authorDTO.setBookDTOS(new ArrayList<>());
            if (author.getBooks() != null) {
                for (Book book : author.getBooks()) {
                    BookDTO bookDTO = new BookDTO();
                    bookDTO.setId(book.getId());
                    bookDTO.setTitle(book.getTitle());
                    bookDTO.setAuthorId(book.getAuthor().getId());
                    authorDTO.getBookDTOS().add(bookDTO);
                }
            }
            return authorDTO;
        }
        return null;
    }
    @Transactional
    // Utility method to convert AuthorDTO to Author
    public Author mapDTOAuthorToAuthor(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setId(authorDTO.getId());
        author.setFirstName(authorDTO.getFirstName());
        author.setLastName(authorDTO.getLastName());
        author.setEmail(authorDTO.getEmail());
        return author;
    }
    // createAuthor() creates a new author with the given authorDTO
    @Override
    public AuthorDTO createAuthor(AuthorDTO authorDTO) throws Exception
    {
        Author author=mapDTOAuthorToAuthor(authorDTO);
        //check author exists
        if(author.getId()!=null)
        {
            throw new Exception("author already exists");
        }
      //if author does not exist
      authorRepository.save(author);
        return authorDTO;
    }
    // getAuthors() returns a list of all authors
    @Override
    public List<AuthorDTO> getAuthors()
    {
        List<Author> authors = authorRepository.findAll();
        List<AuthorDTO> authorsDTO = new ArrayList<>();
        for(Author author: authors)
        {
            AuthorDTO authorDTO = mapAuthorToDTOAuthor(author);
            authorsDTO.add(authorDTO);
        }

        return authorsDTO;
    }
    // returns the author with the given id
    @Override
    public AuthorDTO getAuthorByID(Long id) {
        AuthorDTO authorDTO = mapAuthorToDTOAuthor(authorRepository.findById(id).orElse(null));
        if (authorDTO != null)
            return authorDTO;
        throw new IllegalArgumentException("Author not found");
    }
    // updateAuthor() updates the author with the given id with the given authorDTO
    @Override
    public AuthorDTO updateAuthor(Long id, AuthorDTO authorDTO) {
        if (authorDTO == null || authorDTO.getEmail() == null) {
            throw new IllegalArgumentException("Invalid author data.");
        }
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Author not found"));
        author.setEmail(authorDTO.getEmail());
        // Update other fields here as needed...
        author = authorRepository.save(author);
        return mapAuthorToDTOAuthor(author);
    }
    // deleteAuthor() deletes the author with the given id, but dont delete if
    // the Author has books more than 5 books
    @Override
    public void deleteAuthor(Long id) throws Exception {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (!optionalAuthor.isPresent()) {
            throw new Exception("Author not found with ID: " + id);
        }
        Author author = optionalAuthor.get();
        if (author.getBooks().size() > 5) {
            throw new Exception("Cannot delete author with more than 5 books!");
        }
        authorRepository.delete(author);
    }
}
