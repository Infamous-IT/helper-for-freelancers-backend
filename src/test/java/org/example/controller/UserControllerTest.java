package org.example.controller;

import org.example.configuration.Config;
import org.example.configuration.MainWebInitializer;
import org.example.configuration.WebConfigure;
import org.example.dto.UserWithOrderDTO;
import org.example.model.enumerated.Role;
import org.example.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {WebConfigure.class, Config.class, MainWebInitializer.class})
@WebAppConfiguration
@DirtiesContext
class UserControllerTest {

    @MockBean
    UserServiceImpl userService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.openMocks(this);
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
//                .build();
//    }

    final UUID USER_ID = UUID.fromString("65fe885a-35ef-4105-9d92-2373759b2c13");
    final UserWithOrderDTO USER_1 = new UserWithOrderDTO("Test", "Testovich", Role.CUSTOMER);
    final UserWithOrderDTO USER_2 = new UserWithOrderDTO("John", "Hollywood", Role.EMPLOYEE);
    final UserWithOrderDTO USER_3 = new UserWithOrderDTO("Leroy", "Boy", Role.EMPLOYEE);

    @Test
    void getAll() throws Exception {
        List<UserWithOrderDTO> userList = new ArrayList<>(List.of(USER_1, USER_2, USER_3));

//        when(userService.getAll()).thenReturn(userList);

        List<UserWithOrderDTO> expectedUsersResponse = List.of(
                new UserWithOrderDTO("Test", "Testovich", Role.CUSTOMER),
                new UserWithOrderDTO("John", "Hollywood", Role.EMPLOYEE),
                new UserWithOrderDTO("Leroy", "Boy", Role.EMPLOYEE)
        );

//        when(userService.getAll()).thenReturn(expectedUsersResponse);

        mockMvc.perform(get(UserController.BASE_URL)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testGetUserByIdFail404NotFound() throws Exception {
        when(userService.getById(USER_ID))
                .thenReturn(null);
        mockMvc.perform(get(UserController.BASE_URL + "/{id}", USER_ID)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }
}