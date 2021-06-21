package com.lamdbsys.microservices.serviceprices.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceDto implements Serializable {

    private BigDecimal price;
    private BookDto book;

}
