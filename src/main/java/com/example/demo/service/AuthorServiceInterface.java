package com.example.demo.service;

import com.example.demo.dto.AuthorDTO;

import java.util.List;

public interface AuthorServiceInterface
{
    AuthorDTO createAuthor(AuthorDTO authorDTO) throws Exception;
    List<AuthorDTO> getAuthors();
    AuthorDTO getAuthorByID(Long id) throws Exception;
    AuthorDTO updateAuthor(Long id, AuthorDTO authorDTO) throws Exception;
    void deleteAuthor(Long id) throws Exception;
}
