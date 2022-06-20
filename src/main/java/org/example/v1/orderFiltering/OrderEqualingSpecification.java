package org.example.v1.orderFiltering;

import org.example.model.Order;
import org.example.v1.searching.SearchCriteria;
import org.example.v1.userFiltering.FilteringOperation;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class OrderEqualingSpecification implements Specification<Order> {

    private static final long serialVersionUID = -5803891540465642051L;
    private SearchCriteria searchCriteria;

    public OrderEqualingSpecification(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root< Order > root, CriteriaQuery< ? > query, CriteriaBuilder cb) {
        if (FilteringOperation.EQUAL == searchCriteria.getOperation()) {
            return cb.equal(root.get(searchCriteria.getKey()), searchCriteria.getValue());
        } else if (FilteringOperation.NOT_EQUAL == searchCriteria.getOperation()) {
            return cb.notEqual(root.get(searchCriteria.getKey()), searchCriteria.getValue());
        } else {
            return null;
        }
    }
}