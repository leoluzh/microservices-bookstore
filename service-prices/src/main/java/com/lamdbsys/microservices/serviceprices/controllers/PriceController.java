package com.lamdbsys.microservices.serviceprices.controllers;

import com.lamdbsys.microservices.serviceprices.dtos.PriceDto;
import com.lamdbsys.microservices.serviceprices.services.PriceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
@RequestMapping("/api/v1/prices")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class PriceController implements Serializable {

    private final PriceService priceService;

    @GetMapping("/books/{bookId}")
    public ResponseEntity<PriceDto> getPrice(@PathVariable("bookId") Long bookId){
        log.info("Requesting price for book {}",bookId);
        return ResponseEntity.ok(this.priceService.getPrice(bookId));
    }

}
