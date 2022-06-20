package org.example.v1.userFiltering.predicate;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.example.model.User;
import org.example.v1.userFiltering.FilteringOperation;
import org.example.v1.searching.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class FirstNameOrLastNameSpecification implements Specification<User> {

    private static final long serialVersionUID = 7860218411885895782L;
    private SearchCriteria searchCriteria;

    public FirstNameOrLastNameSpecification(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        if (searchCriteria.getValue() != null) {
            List<Predicate> predicates = new ArrayList<>();
            if (FilteringOperation.EQUAL == searchCriteria.getOperation()) {
                predicates.add(cb.equal(root.get("lastName"), searchCriteria.getValue()));
                predicates.add(cb.equal(root.get("firstName"), searchCriteria.getValue()));
            }
            if (FilteringOperation.CONTAIN == searchCriteria.getOperation()) {
                String searchValueLowerCase = StringUtils.lowerCase((String) searchCriteria.getValue());
                predicates.add(cb.like(cb.lower(root.get("lastName")), "%" + searchValueLowerCase + "%"));
                predicates.add(cb.like(cb.lower(root.get("firstName")), "%" + searchValueLowerCase + "%"));
            }
            return cb.or(predicates.toArray(new Predicate[predicates.size()]));
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof FirstNameOrLastNameSpecification)) {
            return false;
        }
        if (this == o) {
            return true;
        }
        final FirstNameOrLastNameSpecification otherObject = (FirstNameOrLastNameSpecification) o;

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
