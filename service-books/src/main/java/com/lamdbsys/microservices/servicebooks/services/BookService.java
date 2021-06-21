package com.lamdbsys.microservices.servicebooks.services;

import com.lamdbsys.microservices.servicebooks.dtos.AuthorDto;
import com.lamdbsys.microservices.servicebooks.dtos.BookDto;
import com.lamdbsys.microservices.servicebooks.entities.Book;
import com.lamdbsys.microservices.servicebooks.exceptions.ApplicationException;
import com.lamdbsys.microservices.servicebooks.mappers.BookMapper;
import com.lamdbsys.microservices.servicebooks.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookService implements Serializable {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookDto create(BookDto bookDto) {
        final var book = bookMapper.toEntity(bookDto);
        return bookMapper.toDto(this.bookRepository.save(book));
    }

    public BookDto update(Long id,BookDto bookDto) {
        final var book = verifyIfExists(id);
        bookMapper.merge(bookDto, book);
        return bookMapper.toDto(this.bookRepository.save(book));
    }

    public void delete(Long id) {
        final var book = verifyIfExists(id);
        this.bookRepository.delete(book);
    }

    public BookDto findById(Long bookId){
        final var book = verifyIfExists(bookId);
        return this.bookMapper.toDto(book);
    }

    public Page<BookDto> findAll(final Pageable pageable) {
        return this.bookRepository.findAll(pageable).map(bookMapper::toDto);
    }

    public Book verifyIfExists(Long id){
        return this.bookRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(
                        String.format("Book not found with id %s",id),
                        HttpStatus.NOT_FOUND));
    }

}
