package org.example.v1.orderFiltering;

import org.example.model.Order;
import org.example.v1.searching.SearchCriteria;
import org.example.v1.userFiltering.FilteringOperation;
import org.example.v1.userFiltering.predicate.SpecificationBuilder;
import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;
import java.util.List;

public class CategoriesSpecificationBuilder implements SpecificationBuilder<Order> {

    public static final List<FilteringOperation> SUPPORTED_OPERATORS = Arrays.asList(
            FilteringOperation.EQUAL);

    @Override
    public Specification< Order > buildSpecification(SearchCriteria searchCriteria) {
        return new CategoriesSpecification(searchCriteria);
    }
}
