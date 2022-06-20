package org.example.controller;

import org.example.dto.UserDTO;
import org.example.dto.UserWithOrderDTO;
import org.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = UserController.BASE_URL)
public class UserController {

    public static final String BASE_URL = ServiceAPIUrl.VERSION_PATH + "/user";

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> get() {
        return userService.get();
    }

    @GetMapping(value = "/{id}")
    public UserWithOrderDTO getById(@PathVariable UUID id) {
        return userService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO create(@Valid @RequestBody UserDTO userDTO) {
        userService.create(userDTO);
        return userDTO;
    }

    @PutMapping(value = "/{id}")
    public UserDTO update(@Valid @RequestBody UserDTO userDTO) {
        userService.update(userDTO);
        return userDTO;
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable UUID id) {
        userService.delete(id);
    }
}
