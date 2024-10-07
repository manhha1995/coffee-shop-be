package com.dap.coffee.entities;


import com.dap.coffee.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
@EqualsAndHashCode(callSuper = false)
public class Order extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -4677281933363613383L;

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    @Column(name = "order_id")
    private String id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "shop_id")
    private String shopId;

    @Column(name = "order_status")
    private String status;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    @JsonManagedReference(value = "order")
    private List<OrderItem> orderItems;

    @OneToOne
    @JsonBackReference(value = "queue_id")
    @JoinColumn(name = "queue_id")
    private Queue queue;

}
