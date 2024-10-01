package com.dap.coffee.entities;

import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "queue")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Queue {

    @Column(name = "queue_id")
    @UuidGenerator
    private String id;

    private Order order;

    @Column(name = "queue_status")
    private String status;
}
