package com.lamdbsys.microservices.servicebooks.repositories;

import com.lamdbsys.microservices.servicebooks.entities.Author;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends PagingAndSortingRepository<Author, Long> {

    Optional<Author> findByBooksId(Long bookId);

}
