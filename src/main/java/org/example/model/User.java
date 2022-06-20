package org.example.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.example.model.enumerated.Role;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "first_name")
    @NotNull(message = "First name must be not empty")
    @Length(message = "First name must be min 1 and max 100 symbols ", min = 2, max = 100)
    private String firstName;

    @Column(name = "last_name")
    @NotNull(message = "Last name must be not empty")
    @Length(message = "Last name must be min 1 and max 100 symbols ", min = 2, max = 100)
    private String lastName;

    @Column(name = "role")
    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Order> order;

    public User(String firstName, String lastName, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public User(String firstName, String lastName, Role role, List<Order> order) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.order = order;
    }

    public User() {
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

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        } else if (this == obj) {
            return true;
        } else {
            User otherObject = (User) obj;
            return new EqualsBuilder()
                    .append(this.id, otherObject.id)
                    .append(this.firstName, otherObject.firstName)
                    .append(this.lastName, otherObject.lastName)
                    .append(this.role, otherObject.role)
                    .isEquals();
        }
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hashCodeBuilder = new HashCodeBuilder();
        hashCodeBuilder.append(this.getId());
        hashCodeBuilder.append(this.getFirstName());
        hashCodeBuilder.append(this.getLastName());
        hashCodeBuilder.append(this.getRole());
        return hashCodeBuilder.hashCode();
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", roles=" + role +
                '}';
    }
}
