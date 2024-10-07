package com.dap.coffee.entities;

import com.dap.coffee.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "order_item")
@EqualsAndHashCode(callSuper = false)
public class OrderItem extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -4677281933363613383L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "order_quantity")
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "order_id")
    @JoinColumn(name = "order_id")
    private Order order;
}
