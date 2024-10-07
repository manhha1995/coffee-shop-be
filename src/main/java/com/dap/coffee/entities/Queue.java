package com.dap.coffee.entities;

import com.dap.coffee.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "queue")
public class Queue extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -4677281933363613383L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "queue")
    @JsonManagedReference(value = "queue")
    private Order order;

    @Column(name = "queue_status")
    private String status;
}
