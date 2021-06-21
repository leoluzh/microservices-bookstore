package com.lamdbsys.microservices.servicebooks.dtos;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthorDto implements Serializable {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

}
