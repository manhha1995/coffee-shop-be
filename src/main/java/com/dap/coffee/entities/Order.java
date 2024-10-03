package com.dap.coffee.entities;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "order")
public class Order implements Serializable {

    @Serial
    private static final long serialVersionUID = -4677281933363613383L;

    @Id
    @UuidGenerator
    private String id;

    private String userId;

    private String shopId;

    private String status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @Fetch(FetchMode.JOIN)
    private List<OrderItem> orderItems;

    public Order(String id) {
        this.id = id;
    }
}
