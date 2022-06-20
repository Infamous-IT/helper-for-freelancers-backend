package org.example.dto;

import org.example.model.enumerated.Role;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

public class UserDTO {

    private UUID id;

    @NotNull
    @Size(message = "First name must be min 1 and max 100 symbols ", min = 2, max = 100)
    private String firstName;

    @NotNull
    @Size(message = "Last name must be min 1 and max 100 symbols ", min = 2, max = 100)
    private String lastName;

    @NotNull
    private Role role;

    public UserDTO() {
    }

    public UserDTO(String firstName, String lastName, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}