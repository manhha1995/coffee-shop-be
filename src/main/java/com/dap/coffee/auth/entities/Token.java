package com.dap.coffee.auth.entities;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Token implements Serializable {
    private String accessToken;
}
