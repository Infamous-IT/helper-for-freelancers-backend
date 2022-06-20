package org.example.v1.userFiltering.predicate;

import org.example.v1.searching.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationBuilder<T> {

     Specification <T> buildSpecification(SearchCriteria searchCriteria);

}