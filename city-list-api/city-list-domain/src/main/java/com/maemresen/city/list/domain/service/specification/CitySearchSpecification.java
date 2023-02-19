package com.maemresen.city.list.domain.service.specification;

import com.maemresen.city.list.domain.entity.City;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.commons.collections4.MapUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class CitySearchSpecification implements Specification<City> {

	private static final String NAME_FILTER = "name";

	private final Map<String, String> filters;

	public CitySearchSpecification(Map<String, String> filters) {
		this.filters = MapUtils.emptyIfNull(filters);
	}

	@Override
	public Predicate toPredicate(Root<City> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

		List<Predicate> predicates = new ArrayList<>();
		String nameFilter = filters.computeIfAbsent(NAME_FILTER, map -> "");
		if (nameFilter.length() > 2) {
			String nameFilterLowerCase = nameFilter.toLowerCase(Locale.ENGLISH);
			String nameFilterLowerCaseWildcard = "%" + nameFilterLowerCase + "%";

			Path<String> cityName = root.get("name");
			Expression<String> cityNameLowerCase = criteriaBuilder.lower(cityName);

			Predicate nameLike = criteriaBuilder.like(cityNameLowerCase, nameFilterLowerCaseWildcard);
			predicates.add(nameLike);
		}

		return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	}
}
