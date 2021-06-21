package com.lamdbsys.microservices.serviceprices.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto implements Serializable {

    private String name;
    private String surname;

}
