package com.prudhviraj.ecommerce.kafka.user_svc.service.impl;

import com.prudhviraj.ecommerce.kafka.event.UserCreatedEvent;
import com.prudhviraj.ecommerce.kafka.user_svc.dto.UserDto;
import com.prudhviraj.ecommerce.kafka.user_svc.entity.User;
import com.prudhviraj.ecommerce.kafka.user_svc.repository.UserRepository;
import com.prudhviraj.ecommerce.kafka.user_svc.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    private final KafkaTemplate<Long, UserCreatedEvent> kafkaTemplate;

    @Value("${kafka.topic.user-created-topic}")
    private String KAFKA_USER_CREATED_TOPIC;

    /**
     * Creates a new user in the database.
     *
     * @param userDto the data transfer object containing user details.
     * @return a message indicating the result of the operation.
     */
    @Override
    public String createUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        log.debug("Attempting to save user: {}", user);

        User savedUser = userRepository.save(user);
        if (savedUser == null) {
            log.error("Failed to save user: {} into the database. Possible issues could include a database error or a validation failure.", userDto);
            return "User not saved into DB";
        } else {
            log.info("User successfully saved into the database: {}", savedUser);
            UserCreatedEvent userCreatedEvent = modelMapper.map(savedUser,UserCreatedEvent.class);
            kafkaTemplate.send(KAFKA_USER_CREATED_TOPIC,userCreatedEvent.getId(),userCreatedEvent);
            return "User saved into DB successfully : -"+userCreatedEvent.toString();
        }
    }
}
