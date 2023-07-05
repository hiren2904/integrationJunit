package com.example.practise.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.example.practise.dto.RequestDto;
import com.example.practise.dto.RequestDto.GlobalOperator;
import com.example.practise.dto.SearchRequestDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class FilterSpecification<T> {

	public Specification<T> getSearchSpecification(SearchRequestDto searchrequestdto) {

		return new Specification<>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				// TODO Auto-generated method stub
				return criteriaBuilder.equal(root.get(searchrequestdto.getColumn()), searchrequestdto.getValue());
			}

		};
	}

	public Specification<T> getSearchSpecification(List<SearchRequestDto> searchrequestdto,
			GlobalOperator globaloperator) {

		return (root, query, criteriaBuilder) -> {

			List<Predicate> predicates = new ArrayList<>();

			for (SearchRequestDto reqDto : searchrequestdto) {

				switch (reqDto.getOperation()) {

				case EQUAL:
					Predicate equal = criteriaBuilder.equal(root.get(reqDto.getColumn()), reqDto.getValue());
					predicates.add(equal);
					break;

				case LIKE:
					Predicate like = criteriaBuilder.like(root.get(reqDto.getColumn()), "%" + reqDto.getValue() + "%");
					predicates.add(like);
					break;
					
				case IN :
					String[] split = reqDto.getValue().split(",");
					Predicate in = root.get(reqDto.getColumn()).in(Arrays.asList(split));
					predicates.add(in);
					break;
				
				case LESS_THAN :
					Predicate lessthan = criteriaBuilder.lessThan(root.get(reqDto.getColumn()), reqDto.getValue());
					predicates.add(lessthan);
					break;
					
				case GREATER_THAN :
					Predicate greaterthan = criteriaBuilder.greaterThan(root.get(reqDto.getColumn()),  reqDto.getValue());
					predicates.add(greaterthan);
					break;
					
				default:
					throw new IllegalArgumentException("Unexpected value: " + "");
				}
			}

			if (globaloperator.equals(RequestDto.GlobalOperator.AND)) {
				return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			} else {
				return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
			}

		};
	}

}
