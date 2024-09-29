package com.dap.coffee.entities;

import com.dap.coffee.common.BaseEntity;
import com.dap.coffee.entities.Role;
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
@Table(name = "m_user")
public class User extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -4677281933363613383L;
    @Id
    @UuidGenerator
    private String id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne
    @JsonBackReference(value = "role_id")
    @JoinColumn(name = "role_id")
    private Role role;
}
