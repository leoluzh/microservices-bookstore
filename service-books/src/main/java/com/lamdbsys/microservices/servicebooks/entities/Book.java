package com.lamdbsys.microservices.servicebooks.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(schema = "service_books", name = "book")
/**
@SequenceGenerator(
        name = "sequence_book_generator",
        schema = "service_books.seq_book",
        sequenceName = "service_books.seq_book",
        initialValue = 100,
        allocationSize = 1
)
**/
@GenericGenerator(
        name = "sequence_book_generator",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @org.hibernate.annotations.Parameter(name = "sequence_name", value = "service_books.seq_book"),
                @org.hibernate.annotations.Parameter(name = "initial_value", value = "100"),
                @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
        }
)
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_book_generator")
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

}
