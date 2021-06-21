package com.lamdbsys.microservices.serviceprices.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDto implements Serializable  {

    private String title;
    private AuthorDto author;

}
