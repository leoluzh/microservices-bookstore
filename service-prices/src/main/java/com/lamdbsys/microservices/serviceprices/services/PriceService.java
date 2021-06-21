package com.lamdbsys.microservices.serviceprices.services;

import com.lamdbsys.microservices.serviceprices.dtos.PriceDto;
import com.lamdbsys.microservices.serviceprices.exceptions.ApplicationException;
import com.lamdbsys.microservices.serviceprices.feign.ServiceBooks;
import com.lamdbsys.microservices.serviceprices.mappers.PriceMapper;
import com.lamdbsys.microservices.serviceprices.repositories.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PriceService implements Serializable {

    private final PriceRepository priceRepository;
    private final PriceMapper priceMapper;
    private final ServiceBooks serviceBooks;

    public PriceDto getPrice(final Long bookId) {

        final var price = priceRepository.findByBookId(bookId);

        final var priceDto = price.map(priceMapper::toDto).orElseThrow(() -> {
            throw new ApplicationException(
                    String.format("No price for book with id %s", bookId), HttpStatus.NOT_FOUND);
        });

        //Houston we have a problem here ... need to put a circuit break to external calling
        final var book = serviceBooks.getBookById(bookId);
        priceDto.setBook(book);
        return priceDto;

    }

}
