package com.loserico.orm.jpa.predicate;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class DatePredicate extends AbstractDatePredicate {
	
	private Date begin;
	private Date end;

	private DateMatchMode matchMode = DateMatchMode.BETWEEN;

	public DatePredicate(String propertyName, Date begin, Date end) {
		setPropertyName(propertyName);
		this.begin = begin;
		this.end = end;
		addCandidateMatchMode(DateMatchMode.BETWEEN);
		checkDateMatchMode(matchMode);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Predicate toPredicate(CriteriaBuilder criteriaBuilder, Root root) {
		return criteriaBuilder.between(root.get(getPropertyName()), begin, end);
	}

}
