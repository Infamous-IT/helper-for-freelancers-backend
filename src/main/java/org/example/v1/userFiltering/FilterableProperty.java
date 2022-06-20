package org.example.v1.userFiltering;



import org.example.v1.userFiltering.predicate.SpecificationBuilder;

import java.util.List;

public class FilterableProperty<T> {
    private String propertyName;
    private Class<?> expectedType;
    private List<FilteringOperation> operators;
    private SpecificationBuilder<T> specificationBuilder;

    public FilterableProperty(String propertyName, Class<?> expectedType, List<FilteringOperation> operators, SpecificationBuilder<T> specificationBuilder) {
        this.propertyName = propertyName;
        this.expectedType = expectedType;
        this.operators = operators;
        this.specificationBuilder = specificationBuilder;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public Class<?> getExpectedType() {
        return expectedType;
    }

    public List<FilteringOperation> getOperators() {
        return operators;
    }

    public SpecificationBuilder<T> getSpecificationBuilder() {
        return specificationBuilder;
    }
}
