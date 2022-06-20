package org.example.v1.userFiltering;

import org.example.v1.searching.SearchCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface EntityFilterSpecificationsBuilder<T> {

    Logger log = LoggerFactory.getLogger(EntityFilterSpecificationsBuilder.class);

    List<FilterableProperty<T>> getFilterableProperties();

    default Specification<T> buildSpecification(List<SearchCriteria> searchCriterias) {
        Specification<T> specifications = null;
        for (SearchCriteria searchCriteria : searchCriterias) {
            Optional<FilterableProperty<T>> filterableProperty = getFilterableProperties().stream().filter(
                    property -> property.getPropertyName().equals(searchCriteria.getKey())
            ).findFirst();

            if (filterableProperty.isPresent()) {
                FilterableProperty<T> filterablePropertyObject = filterableProperty.get();
                if (specifications == null) {
                    specifications = Specification.where(filterablePropertyObject.getSpecificationBuilder().buildSpecification(searchCriteria));
                } else {
                    specifications = specifications.and(filterablePropertyObject.getSpecificationBuilder().buildSpecification(searchCriteria));
                }
            } else {
                log.warn("Filtering on property '{}' has been skipped because it's absent in filterableProperties", searchCriteria.getKey());
            }
        }
        return specifications;
    }
}