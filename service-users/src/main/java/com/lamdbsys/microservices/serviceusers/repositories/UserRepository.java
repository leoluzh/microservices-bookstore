package com.lamdbsys.microservices.serviceusers.repositories;

import com.lamdbsys.microservices.serviceusers.entities.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User,Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

}
