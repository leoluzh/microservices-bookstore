package com.lamdbsys.microservices.servicebooks.controllers;

import com.lamdbsys.microservices.servicebooks.dtos.AuthorDto;
import com.lamdbsys.microservices.servicebooks.dtos.BookDto;
import com.lamdbsys.microservices.servicebooks.services.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class BookController implements Serializable {

    private final BookService bookService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BookDto> create(@RequestBody @Valid BookDto bookDto){
        log.info("Creating book {}", bookDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.bookService.create(bookDto));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BookDto> update(@PathVariable("id") Long id , @RequestBody @Valid BookDto bookDto){
        log.info("Updating book with id {}",id);
        return ResponseEntity.status(HttpStatus.OK).body(this.bookService.update(id,bookDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable("id") Long id){
        log.info("Deleting book with id {}",id);
        this.bookService.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> findById(@PathVariable("id") Long id) {
        log.info("Finding book with id {}",id);
        return ResponseEntity.ok(this.bookService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<BookDto>> findAll(final Pageable pageable) {
        log.info("Finding books with {}",pageable);
        return ResponseEntity.ok(this.bookService.findAll(pageable));
    }

}
