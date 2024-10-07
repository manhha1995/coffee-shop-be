package com.dap.coffee.entities;

import com.dap.coffee.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shop")
@EqualsAndHashCode(callSuper = false)
public class Shop extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -4677281933363613383L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "shop_name")
    private String name;

    @Column(name="location")
    private String location;
}
