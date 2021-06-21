package com.lamdbsys.microservices.serviceprices.repositories;

import com.lamdbsys.microservices.serviceprices.entities.Price;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PriceRepository extends PagingAndSortingRepository<Price,Long> {

    Optional<Price> findByBookId(Long bookId);

}
