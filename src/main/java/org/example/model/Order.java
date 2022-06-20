package org.example.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.example.model.enumerated.Category;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "title")
    @Length(min = 1, max = 100, message = "Title must be min 1 and max 100 symbols")
    @NotNull
    private String title;

    @Column(name = "description")
    @Length(min = 5, message = "Description must be min 50 symbols")
    @NotNull
    private String description;

    @Column(name = "price")
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal price;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "categories")
    @NotNull
    @Enumerated(EnumType.STRING)
    private Category categories;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE)
    private List<Note> note;

    public Order(String title, String description,
                 BigDecimal price, LocalDateTime startDate,
                 LocalDateTime endDate, Category categories) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.categories = categories;
    }

    public Order(String title, String description, BigDecimal price,
                 LocalDateTime startDate, LocalDateTime endDate,
                 Category categories, User user) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.categories = categories;
        this.user = user;
    }

    public Order() {}

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Category getCategories() {
        return categories;
    }

    public void setCategories(Category categories) {
        this.categories = categories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Note> getNote() {
        return note;
    }

    public void setNote(List<Note> note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Order)) {
            return false;
        } else if (this == obj) {
            return true;
        } else {
            Order otherObject = (Order) obj;
            return new EqualsBuilder()
                    .append(this.id, otherObject.id)
                    .append(this.title, otherObject.title)
                    .append(this.description, otherObject.description)
                    .append(this.price, otherObject.price)
                    .append(this.startDate, otherObject.startDate)
                    .append(this.endDate, otherObject.endDate)
                    .append(this.categories, otherObject.categories)
                    .append(this.user, otherObject.user)
                    .append(this.note, otherObject.note)
                    .isEquals();
        }
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hashCodeBuilder = new HashCodeBuilder();
        hashCodeBuilder.append(this.getId());
        hashCodeBuilder.append(this.getTitle());
        hashCodeBuilder.append(this.getDescription());
        hashCodeBuilder.append(this.getPrice());
        hashCodeBuilder.append(this.getStartDate());
        hashCodeBuilder.append(this.getEndDate());
        hashCodeBuilder.append(this.getCategories());
        hashCodeBuilder.append(this.getUser());
        hashCodeBuilder.append(this.getNote());
        return hashCodeBuilder.hashCode();
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", categories=" + categories +
                '}';
    }
}
