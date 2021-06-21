package com.lamdbsys.microservices.servicebooks.dtos;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookDto implements Serializable {

    private String title;
    private AuthorDto author;
    private BigDecimal price;

}
