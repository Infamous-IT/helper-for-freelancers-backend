package org.example.service;

import org.example.dao.UserDAOImpl;
import org.example.dto.UserDTO;
import org.example.dto.UserWithOrderDTO;
import org.example.mapper.UserMapper;
import org.example.model.User;
import org.example.model.enumerated.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserDAOImpl userDAO;

    @Autowired
    private UserMapper userMapper;

    @Test
    void getAll() {
        List<User> userList = new ArrayList<>();
        User user1 = new User("Test", "Testovich", Role.CUSTOMER);
        User user2 = new User("John", "Hollywood", Role.EMPLOYEE);
        User user3 = new User("Leroy", "Boy", Role.EMPLOYEE);

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        when(userDAO.getAll()).thenReturn(userList);

        List<UserDTO> users = userService.get();

        assertEquals(3, users.size());
        verify(userDAO, times(1)).getAll();
    }

    @Test
    void create() {
        User user = new User("Ivan", "Ivanenko", Role.EMPLOYEE);
        userService.create(userMapper.userToUserDTO(user));

        assertEquals("Ivan", user.getFirstName());
        assertEquals("Ivanenko", user.getLastName());
        assertEquals("EMPLOYEE", user.getRole().toString());

        verify(userDAO, times(1)).create(user);
    }

    @Test
    void update() {
        User user = new User("Ivan", "Testovich", Role.CUSTOMER);
        userService.update(userMapper.userToUserDTO(user));

        assertEquals("Ivan", user.getFirstName());
        assertEquals("Testovich", user.getLastName());
        assertEquals("CUSTOMER", user.getRole().toString());

        verify(userDAO, times(1)).update(user);
    }

    @Test
    void getById() {
        when(userDAO.getById(UUID.fromString("65fe885a-35ef-4105-9d06-2373759b2c13")))
                .thenReturn(new User("Nazar", "Hlukhaniuk", Role.EMPLOYEE));

//        UserDTO userDTO = userService.getById(UUID.fromString("65fe885a-35ef-4105-9d06-2373759b2c13"));
//
//        assertEquals("Nazar", userDTO.getFirstName());
//        assertEquals("Hlukhaniuk", userDTO.getLastName());
//        assertEquals("EMPLOYEE", userDTO.getRole().toString());
    }

    @Test
    void testDeleteById() {
        UUID id = UUID.fromString("65fe885a-35ef-4105-9d06-2373759b2c15");

        willDoNothing().given(userDAO).delete(id);

        userService.delete(id);

        verify(userDAO, times(1)).delete(id);
    }
}