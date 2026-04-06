package search;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;


import jakarta.persistence.criteria.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;
import java.util.function.Function;

public class GenericSpecification<T> implements Specification<T> {

	private final List<SearchCriteria> list;

	public GenericSpecification() {
		this.list = new ArrayList<>();
	}

	public void add(SearchCriteria criteria) {
		list.add(criteria);
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		List<Predicate> predicates = new ArrayList<>();
		boolean hasSpecialOperation = false;

		for (SearchCriteria criteria : list) {
			if (criteria.getOperation() == SearchOperation.UNIQUE) {
				query.distinct(true);
				query.multiselect(root.get(criteria.getKey()));
				hasSpecialOperation = true;
			} else if (criteria.getOperation() == SearchOperation.GROUP_BY) {
				applyGroupBy(query, root, builder, criteria);
				hasSpecialOperation = true;
			} else {
				Path<?> path;
				if (criteria.getJoinEntity() != null) {
					Join<Object, Object> join = root.join(criteria.getJoinEntity(), JoinType.LEFT);
					path = join.get(criteria.getKey());
				} else {
					path = root.get(criteria.getKey());
				}
				predicates.add(createPredicate(path, builder, criteria));
			}
		}

		return hasSpecialOperation ? builder.and() : builder.and(predicates.toArray(new Predicate[0]));
	}

	private void applyGroupBy(CriteriaQuery<?> query, Root<T> root, CriteriaBuilder builder, SearchCriteria criteria) {
		String[] groupFields = criteria.getKey().split(",");
		List<Expression<?>> groupByExpressions = new ArrayList<>();

		for (String field : groupFields) {
			groupByExpressions.add(root.get(field.trim()));
		}

		query.groupBy(groupByExpressions);

		if (criteria.getValue() != null) {
			String aggregateFunction = criteria.getValue().toString().toUpperCase();
			switch (aggregateFunction) {
			case "COUNT":
				query.multiselect(groupByExpressions.get(0), builder.count(root));
				break;
			case "SUM":
				query.multiselect(groupByExpressions.get(0), builder.sum(root.get(groupFields[0].trim())));
				break;
			}
		}
	}

	private Predicate createPredicate(Path<?> path, CriteriaBuilder builder, SearchCriteria criteria) {
		switch (criteria.getOperation()) {
		case EQUAL_TO:
			return builder.equal(path, criteria.getValue());
		case NOT_EQUAL:
			return builder.notEqual(path, criteria.getValue());
		case GREATER_THAN:
			return builder.greaterThan(path.as(String.class), criteria.getValue().toString());
		case LESS_THAN:
			return builder.lessThan(path.as(String.class), criteria.getValue().toString());
		case GREATER_THAN_EQUAL:
			return builder.greaterThanOrEqualTo(path.as(String.class), criteria.getValue().toString());
		case LESS_THAN_EQUAL:
			return builder.lessThanOrEqualTo(path.as(String.class), criteria.getValue().toString());
		case LIKE:
			return builder.like(path.as(String.class), "%" + criteria.getValue() + "%");
		case STARTS_WITH:
			return builder.like(path.as(String.class), criteria.getValue() + "%");
		case ENDS_WITH:
			return builder.like(path.as(String.class), "%" + criteria.getValue());
		case IN:
			return handleInOperation(path, criteria);
		case NOT_IN:
			return builder.not(path.in(extractValues(criteria)));
		case IS_NULL:
			return builder.isNull(path);
		case NOT_NULL:
			return builder.isNotNull(path);
		case BETWEEN:
			return handleBetweenOperation(path, criteria, builder);
		default:
			throw new IllegalArgumentException("Unknown operation: " + criteria.getOperation());
		}
	}

	private Predicate handleInOperation(Path<?> path, SearchCriteria criteria) {
		List<?> values = extractValues(criteria);
		Class<?> pathType = path.getJavaType();

		if (Long.class.equals(pathType)) {
			List<Long> longValues = convertToList(values, Long::valueOf);
			return path.in(longValues);
		} else if (Integer.class.equals(pathType)) {
			List<Integer> intValues = convertToList(values, Integer::valueOf);
			return path.in(intValues);
		} else {
			return path.in(values);
		}
	}

	private List<?> extractValues(SearchCriteria criteria) {
		if (criteria.getValue() instanceof String strValue) {
			return Arrays.stream(strValue.split(",")).map(String::trim).toList();
		} else if (criteria.getValue() instanceof List<?> listValue) {
			return listValue;
		} else if (criteria.getValue() instanceof Object[] arrayValue) {
			return Arrays.asList(arrayValue);
		} else {
			throw new IllegalArgumentException("Invalid type for IN operation: " + criteria.getValue());
		}
	}

	private <U> List<U> convertToList(List<?> values, Function<String, U> converter) {
		try {
			return values.stream().map(String::valueOf).map(String::trim).map(converter).toList();
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Invalid numeric format in IN operation: " + values, e);
		}
	}

	private Predicate handleBetweenOperation(Path<?> path, SearchCriteria criteria, CriteriaBuilder builder) {
		if (criteria.getValue() instanceof Date[] dateValues) {
			return builder.between(path.as(Date.class), dateValues[0], dateValues[1]);
		} else if (criteria.getValue() instanceof Timestamp[] timestampValues) {
			return builder.between(path.as(Timestamp.class), timestampValues[0], timestampValues[1]);
		} else if (criteria.getValue() instanceof Number[] numValues && numValues.length == 2) {
			Class<?> type = path.getJavaType();
			if (Integer.class.equals(type)) {
				return builder.between(path.as(Integer.class), (Integer) numValues[0], (Integer) numValues[1]);
			} else if (Long.class.equals(type)) {
				return builder.between(path.as(Long.class), (Long) numValues[0], (Long) numValues[1]);
			} else if (Double.class.equals(type)) {
				return builder.between(path.as(Double.class), (Double) numValues[0], (Double) numValues[1]);
			} else if (Float.class.equals(type)) {
				return builder.between(path.as(Float.class), (Float) numValues[0], (Float) numValues[1]);
			}
		}
		throw new IllegalArgumentException("Invalid type for BETWEEN operation: " + criteria.getValue());
	}

	public Specification<T> addFilter(String fieldName, SearchOperation operation, Object filterValues) {
		if (filterValues != null) {
			this.add(new SearchCriteria(fieldName, operation, filterValues, null));
		}
		return this;
	}

	public Specification<T> addJoinFilter(String fieldName, SearchOperation operation, Object filterValues,
			String joinEntity) {
		if (filterValues != null) {
			this.add(new SearchCriteria(fieldName, operation, filterValues, joinEntity));
		}
		return this;
	}

//	public Specification<T> addCommonFilter(PosUserDetails loginUser) {
//		addFilter("isActive", SearchOperation.EQUAL_TO, 1);
//		addFilter("isDeleted", SearchOperation.EQUAL_TO, 0);
//		return this;
//	}

	@Override
	public Specification<T> and(@Nullable Specification<T> other) {
		return (root, query, builder) -> {
			Predicate predicate1 = this.toPredicate(root, query, builder);
			Predicate predicate2 = (other != null) ? other.toPredicate(root, query, builder) : null;
			return (predicate2 != null) ? builder.and(predicate1, predicate2) : predicate1;
		};
	}

	@Override
	public Specification<T> or(@Nullable Specification<T> other) {
		return (root, query, builder) -> {
			Predicate predicate1 = this.toPredicate(root, query, builder);
			Predicate predicate2 = (other != null) ? other.toPredicate(root, query, builder) : null;
			return (predicate2 != null) ? builder.or(predicate1, predicate2) : predicate1;
		};
	}

}