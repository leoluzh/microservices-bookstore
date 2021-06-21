package com.lamdbsys.microservices.servicebooks.controllers;

import com.lamdbsys.microservices.servicebooks.dtos.AuthorDto;
import com.lamdbsys.microservices.servicebooks.services.AuthorService;
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
@RequestMapping("/api/v1/authors")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class AuthorController implements Serializable {

    private final AuthorService authorService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AuthorDto> create(@RequestBody @Valid AuthorDto authorDto){
        log.info("Creating author {}", authorDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.authorService.create(authorDto));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AuthorDto> update(@PathVariable("id") Long id , @RequestBody @Valid AuthorDto authorDto){
        log.info("Updating author with id {}",id);
        return ResponseEntity.status(HttpStatus.OK).body(this.authorService.update(id,authorDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable("id") Long id){
        log.info("Deleting author with id {}",id);
        this.authorService.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> findById(@PathVariable("id") Long id) {
        log.info("Finding author with id {}",id);
        return ResponseEntity.ok(this.authorService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<AuthorDto>> findAll(final Pageable pageable) {
        log.info("Finding authors with {}",pageable);
        return ResponseEntity.ok(this.authorService.findAll(pageable));
    }

}
