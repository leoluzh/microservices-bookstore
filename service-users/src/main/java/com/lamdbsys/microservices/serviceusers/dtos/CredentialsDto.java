package com.lamdbsys.microservices.serviceusers.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CredentialsDto implements Serializable {

    @NotBlank
    private String username;
    @NotBlank
    private String password;

}
