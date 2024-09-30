package com.dap.coffee.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "order_item")
public class OrderItem implements Serializable {
    @Serial
    private static final long serialVersionUID = -4677281933363613383L;

    @Id
    @UuidGenerator
    private String id;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "order_quantity")
    private Integer quantity;

    @ManyToOne
    @JsonBackReference(value = "order_id")
    private Order order;
}
