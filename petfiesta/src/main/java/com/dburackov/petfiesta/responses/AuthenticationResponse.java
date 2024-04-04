package com.dburackov.petfiesta.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponse {
    private Long id;
    private String token;
}
