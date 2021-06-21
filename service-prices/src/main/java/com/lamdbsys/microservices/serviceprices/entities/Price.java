package com.lamdbsys.microservices.serviceprices.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "service_prices", name = "price")

/**
 @SequenceGenerator( name = "sequence_price_generator",
 sequenceName = "service_prices.seq_price",
 schema = "service_prices",
 initialValue = 100,
 allocationSize = 1)
 **/

@GenericGenerator(
        name = "sequence_price_generator",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @Parameter(name = "sequence_name", value = "service_prices.seq_price"),
                @Parameter(name = "initial_value", value = "100"),
                @Parameter(name = "increment_size", value = "1")
        }
)
public class Price implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_price_generator")
    private Long id;

    @Column(name = "book_id", nullable = false)
    private Long bookId;

    @Column(nullable = false, precision = 10, scale = 4)
    private BigDecimal price;

}
