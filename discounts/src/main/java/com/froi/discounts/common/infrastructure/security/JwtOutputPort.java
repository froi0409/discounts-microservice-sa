package com.froi.discounts.common.infrastructure.security;

public interface JwtOutputPort {

    String getUsername(String token);

    String getRole(String token);

    boolean isValid(String token);
}
