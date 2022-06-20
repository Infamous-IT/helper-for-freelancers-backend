package org.example.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "title")
    @Length(min = 1, max = 100, message = "Title must be min 1 and max 100 symbols")
    @NotNull
    private String title;

    @Column(name = "description")
    @Length(min = 25, message = "Description must be min 25 symbols")
    @NotNull
    private String description;

    @ManyToOne
    @JoinColumn(name = "orders_id")
    private Order order;

    public Note(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Note(String title, String description, Order order) {
        this.title = title;
        this.description = description;
        this.order = order;
    }

    public Note() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Note)) {
            return false;
        } else if (this == obj) {
            return true;
        } else {
            Note otherObject = (Note) obj;
            return new EqualsBuilder()
                    .append(this.id, otherObject.id)
                    .append(this.title, otherObject.title)
                    .append(this.description, otherObject.description)
                    .append(this.order, otherObject.order)
                    .isEquals();
        }
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hashCodeBuilder = new HashCodeBuilder();
        hashCodeBuilder.append(this.getId());
        hashCodeBuilder.append(this.getTitle());
        hashCodeBuilder.append(this.getDescription());
        hashCodeBuilder.append(this.getOrder());
        return hashCodeBuilder.hashCode();
    }

    @Override
    public String toString() {
        return "NotesEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
