package com.lamdbsys.microservices.servicebooks.repositories;

import com.lamdbsys.microservices.servicebooks.entities.Book;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book,Long> {

}
