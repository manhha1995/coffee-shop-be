package com.dap.coffee.auth.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "role")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "role_code")
    @Enumerated(EnumType.ORDINAL)
    private ERole roleCode;

    @OneToMany(mappedBy = "role",fetch = FetchType.LAZY)
    @JsonManagedReference(value = "role")
    private List<User> users;
}
