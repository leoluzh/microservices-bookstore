package com.lamdbsys.microservices.servicebooks.services;

import com.lamdbsys.microservices.servicebooks.dtos.AuthorDto;
import com.lamdbsys.microservices.servicebooks.entities.Author;
import com.lamdbsys.microservices.servicebooks.exceptions.ApplicationException;
import com.lamdbsys.microservices.servicebooks.mappers.AuthorMapper;
import com.lamdbsys.microservices.servicebooks.repositories.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthorService implements Serializable {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorDto create(AuthorDto authorDto) {
        var author = authorMapper.toEntity(authorDto);
        return authorMapper.toDto(this.authorRepository.save(author));
    }

    public AuthorDto update(Long id,AuthorDto authorDto) {
        final var author = verifyIfExists(id);
        authorMapper.merge(authorDto, author);
        return authorMapper.toDto(this.authorRepository.save(author));
    }

    public void delete(Long id) {
        final var author = verifyIfExists(id);
        this.authorRepository.delete(author);
    }

    public AuthorDto findById(final Long id) {
        final var author = verifyIfExists(id);
        return this.authorMapper.toDto(author);
    }

    public Page<AuthorDto> findAll(final Pageable pageable) {
        return this.authorRepository.findAll(pageable).map(authorMapper::toDto);
    }

    public Author verifyIfExists(Long id) {
        return this.authorRepository.findById(id).orElseThrow(() -> {
            throw new ApplicationException(
                    String.format("Book with id %s not found.", id),
                    HttpStatus.NOT_FOUND);
        });
    }

}
