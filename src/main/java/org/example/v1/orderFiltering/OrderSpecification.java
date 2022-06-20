package org.example.v1.orderFiltering;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.example.model.Order;
import org.example.v1.searching.SearchCriteria;
import org.example.v1.userFiltering.FilteringOperation;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class OrderSpecification implements Specification<Order> {

    private static final long serialVersionUID = 7860218411885895782L;
    private SearchCriteria searchCriteria;

    public OrderSpecification(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root< Order > root, CriteriaQuery< ? > criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (searchCriteria.getValue() != null) {
            List<Predicate> predicates = new ArrayList<>();
            if (FilteringOperation.EQUAL == searchCriteria.getOperation()) {
                predicates.add(criteriaBuilder.equal(root.get("user").get("id"), searchCriteria.getValue()));
            }
            return criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]));
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof OrderSpecification)) {
            return false;
        }
        if (this == o) {
            return true;
        }
        final OrderSpecification otherObject = (OrderSpecification) o;

        return new EqualsBuilder()
                .append(this.searchCriteria, otherObject.searchCriteria)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(searchCriteria)
                .hashCode();
    }
}