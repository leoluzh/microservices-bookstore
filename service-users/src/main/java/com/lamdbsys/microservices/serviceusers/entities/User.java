package com.lamdbsys.microservices.serviceusers.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString

@Entity
@Table(schema = "service_users" , name = "bookstore_user")
@SequenceGenerator(name = "sequence_bookstore_user_generator" , allocationSize = 1 , initialValue = 1)
public class User implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "sequence_bookstore_user_generator")
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

}
