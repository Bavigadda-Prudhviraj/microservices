package com.prudhviraj.ecommerce.kafka.user_svc.service;

import com.prudhviraj.ecommerce.kafka.user_svc.dto.UserDto;

public interface UserService {
    public String createUser(UserDto userDto);
}
