package org.example.service;

import org.example.dto.UserDTO;
import org.example.dto.UserWithOrderDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<UserDTO> get();

    UserDTO update(UserDTO userDTO);

    UserDTO create(UserDTO userDTO);

    UserWithOrderDTO getById(UUID uuid);

    void delete(UUID uuid);
}
