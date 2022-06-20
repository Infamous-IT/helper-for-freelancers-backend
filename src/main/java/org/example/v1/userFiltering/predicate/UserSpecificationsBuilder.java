package org.example.v1.userFiltering.predicate;

import org.example.model.User;
import org.example.v1.userFiltering.EntityFilterSpecificationsBuilder;
import org.example.v1.userFiltering.FilterableProperty;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class UserSpecificationsBuilder implements EntityFilterSpecificationsBuilder<User> {

    public static final List<FilterableProperty<User>> FILTERABLE_PROPERTIES = Arrays.asList(
            new FilterableProperty<>("lastName", String.class, FirstNameOrLastNameSpecificationBuilder.SUPPORTED_OPERATORS, new FirstNameOrLastNameSpecificationBuilder()),
            new FilterableProperty<>("firstName", String.class, FirstNameOrLastNameSpecificationBuilder.SUPPORTED_OPERATORS, new FirstNameOrLastNameSpecificationBuilder())
    );

    @Override
    public List<FilterableProperty<User>> getFilterableProperties() {
        return FILTERABLE_PROPERTIES;
    }
}