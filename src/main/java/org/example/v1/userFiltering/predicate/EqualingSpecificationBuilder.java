package org.example.v1.userFiltering.predicate;

import org.example.v1.userFiltering.FilteringOperation;
import org.example.v1.searching.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;
import java.util.List;

public class EqualingSpecificationBuilder<EntityType> implements SpecificationBuilder<EntityType> {

    public static final List<FilteringOperation> SUPPORTED_OPERATORS = Arrays.asList(
            FilteringOperation.EQUAL, FilteringOperation.NOT_EQUAL
    );

    @Override
    public Specification<EntityType> buildSpecification(SearchCriteria searchCriteria) {
        return new EqualingSpecification<>(searchCriteria);
    }
}