package org.example.mapper;

import org.example.dto.UserDTO;
import org.example.dto.UserWithOrderDTO;
import org.example.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO userToUserDTO(User user);

    List<UserDTO> userToUserDTOList(List<User> userList);

    User userDTOToUser(UserDTO userDTO);

    UserWithOrderDTO userToUserWithOrderDTO(User user);
}
