package com.rails.railway.repository.specifications;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class GenericSpecification<T> implements Specification<T> {

    private List<SearchCriteria> list;

    public GenericSpecification() {
        this.list = new ArrayList<>();
    }

    public void add(SearchCriteria criteria) {
        list.add(criteria);
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        for (SearchCriteria criteria : list) {
            if (criteria.getOperation().equals(SearchOperation.GREATER_THAN)) {
                predicates.add(criteriaBuilder.greaterThan(
                        root.get(criteria.getKey()), criteria.getValue().toString()));
            } else if (criteria.getOperation().equals(SearchOperation.LESS_THAN)) {
                predicates.add(criteriaBuilder.lessThan(
                        root.get(criteria.getKey()), criteria.getValue().toString()));
            } else if (criteria.getOperation().equals(SearchOperation.GREATER_THAN_EQUAL)) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                        root.get(criteria.getKey()), criteria.getValue().toString()));
            } else if (criteria.getOperation().equals(SearchOperation.LESS_THAN_EQUAL)) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(
                        root.get(criteria.getKey()), criteria.getValue().toString()));
            } else if (criteria.getOperation().equals(SearchOperation.NOT_EQUAL)) {
                predicates.add(criteriaBuilder.notEqual(
                        root.get(criteria.getKey()), criteria.getValue()));
            } else if (criteria.getOperation().equals(SearchOperation.EQUAL)) {
                predicates.add(criteriaBuilder.equal(
                        root.get(criteria.getKey()), criteria.getValue()));
            } else if (criteria.getOperation().equals(SearchOperation.MATCH)) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get(criteria.getKey())),
                        "%" + criteria.getValue().toString().toLowerCase() + "%"));
            } else if (criteria.getOperation().equals(SearchOperation.MATCH_END)) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get(criteria.getKey())),
                        criteria.getValue().toString().toLowerCase() + "%"));
            }
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

    }
}
