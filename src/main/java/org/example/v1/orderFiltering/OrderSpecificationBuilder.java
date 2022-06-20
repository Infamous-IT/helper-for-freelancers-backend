package org.example.v1.orderFiltering;

import org.example.model.Order;
import org.example.model.enumerated.Category;
import org.example.v1.userFiltering.EntityFilterSpecificationsBuilder;
import org.example.v1.userFiltering.FilterableProperty;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class OrderSpecificationBuilder implements EntityFilterSpecificationsBuilder<Order> {

    public static final List< FilterableProperty < Order > > FILTERABLE_PROPERTIES = Arrays.asList(
            new FilterableProperty< Order >("user", String.class, OwnerSpecificationBuilder.SUPPORTED_OPERATORS, new OwnerSpecificationBuilder()),
            new FilterableProperty< Order >("text", String.class, TextSpecificationBuilder.SUPPORTED_OPERATORS, new TextSpecificationBuilder()),
            new FilterableProperty< Order >("categories", Category.class, CategoriesSpecificationBuilder.SUPPORTED_OPERATORS, new CategoriesSpecificationBuilder())
    );

    @Override
    public List<FilterableProperty< Order >> getFilterableProperties() {
        return FILTERABLE_PROPERTIES;
    }
}