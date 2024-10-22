package com.prudhviraj.ecommerce.api_gateway.service;


public interface JwtService {
    public Long getUserIdFromToken(String token);
    public String getUserRoleFromToken(String token);
}
