package com.lamdbsys.microservices.serviceprices.feign;

import com.lamdbsys.microservices.serviceprices.dtos.BookDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("service-books")
public interface ServiceBooks {

    @GetMapping("/api/v1/books/{bookId}")
    BookDto getBookById(@PathVariable("bookId") Long bookId);

}
