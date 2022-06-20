package org.example.v1.userFiltering.predicate;

import org.example.model.User;
import org.example.v1.userFiltering.FilteringOperation;
import org.example.v1.searching.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;
import java.util.List;

public class FirstNameOrLastNameSpecificationBuilder implements SpecificationBuilder<User> {

    public static final List<FilteringOperation> SUPPORTED_OPERATORS = Arrays.asList(
            FilteringOperation.EQUAL, FilteringOperation.CONTAIN);

    @Override
    public Specification<User> buildSpecification(SearchCriteria searchCriteria) {
        return new FirstNameOrLastNameSpecification(searchCriteria);
    }
}