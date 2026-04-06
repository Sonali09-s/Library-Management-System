package search;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpecificationUtils {

	private SpecificationUtils() {
	}

	public static <T> GenericSpecification<T> buildSpecificationFromSearch(String search) {
		SpecificationBuilder<T> builder = new SpecificationBuilder<>();

		if (search != null && !search.isEmpty()) {
			String[] searchCriteria = search.split(";");
			for (String criteria : searchCriteria) {
				String[] parts = criteria.split(":");
				if (parts.length == 3) {
					String key = parts[0];
					SearchOperation operation = getSearchOperation(parts[1]);
					String value = parts[2];
					String joinEntity = null;

					if (key.contains(".")) {
						String[] keyParts = key.split("\\.");
						joinEntity = keyParts[0];
						key = keyParts[1];
					}

					// Convert date strings to Date objects if the operation is BETWEEN
					if (operation == SearchOperation.BETWEEN) {
						String[] values = value.split(",");
						if (values.length != 2) {
							throw new IllegalArgumentException("BETWEEN operation requires exactly two values");
						}

						if ("createdAt".equalsIgnoreCase(key)) {
							Timestamp[] timestampRange = getTimestampRangeForDate(values[0], values[1]);
							builder.with(key, operation, timestampRange, joinEntity);
						} else {
							// Handle date format
							Date[] dateValues = new Date[2];
							dateValues[0] = parseDate(values[0]);
							dateValues[1] = parseDate(values[1]);
							builder.with(key, operation, dateValues, joinEntity);
						}
					} else {
						builder.with(key, operation, value, joinEntity);
					}
				}
			}
		}

		return builder.build();
	}

	public static Pageable createPageable(RequestDTO request) {
		return PageRequest.of(request.getPage(), request.getSize(),
				request.getSortDirection().equalsIgnoreCase("asc") ? Sort.by(request.getSortBy()).ascending()
						: Sort.by(request.getSortBy()).descending());
	}

	public static SearchOperation getSearchOperation(String operation) {
		switch (operation.toLowerCase()) {
		case "eq":
			return SearchOperation.EQUAL_TO;
		case "ne":
			return SearchOperation.NOT_EQUAL;
		case "gt":
			return SearchOperation.GREATER_THAN;
		case "lt":
			return SearchOperation.LESS_THAN;
		case "gte":
			return SearchOperation.GREATER_THAN_EQUAL;
		case "lte":
			return SearchOperation.LESS_THAN_EQUAL;
		case "in":
			return SearchOperation.IN;
		case "like":
			return SearchOperation.LIKE;
		case "sw":
			return SearchOperation.STARTS_WITH;
		case "ew":
			return SearchOperation.ENDS_WITH;
		case "null":
			return SearchOperation.IS_NULL;
		case "notnull":
			return SearchOperation.NOT_NULL;
		case "btw":
			return SearchOperation.BETWEEN;
		case "unique":
			return SearchOperation.UNIQUE;
		case "groupby":
			return SearchOperation.GROUP_BY;
		default:
			throw new IllegalArgumentException("Invalid search operation: " + operation);
		}
	}

	private static Timestamp[] getTimestampRangeForDate(String startDateStr, String endDateStr) {
		try {
			// Start of the day (00:00:00)
			Timestamp startTimestamp = Timestamp.valueOf(startDateStr + " 00:00:00");

			// End of the day (23:59:59)
			Timestamp endTimestamp = Timestamp.valueOf(endDateStr + " 23:59:59");

			return new Timestamp[] { startTimestamp, endTimestamp };
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid date format. Expected format: yyyy-MM-dd", e);
		}
	}

	// Method to parse date from String to Date
	private static Date parseDate(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false); // Strict parsing
		try {
			return new Date(sdf.parse(dateStr).getTime());
		} catch (ParseException e) {
			throw new IllegalArgumentException("Invalid date format: " + dateStr + ". Expected format: yyyy-MM-dd", e);
		}
	}

	// Method to parse timestamp from String to Timestamp
	private static Timestamp parseTimestamp(String timestampStr) {
		// Replace '#' with ':' to restore the timestamp format
		String formattedStr = timestampStr.replace("#", ":");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setLenient(false); // Strict parsing
		try {
			return new Timestamp(sdf.parse(formattedStr).getTime());
		} catch (ParseException e) {
			throw new IllegalArgumentException(
					"Invalid timestamp format: " + timestampStr + ". Expected format: yyyy-MM-dd HH#mm#ss", e);
		}
	}
	
	public static Long getFilterValueAsLong(RequestDTO requestDto, String key) {
	    if (requestDto == null || requestDto.getSearch() == null) {
	        return null;
	    }

	    String regex = key + ":[a-zA-Z]+:(\\d+)";
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(requestDto.getSearch());

	    if (matcher.find()) {
	        Long value = Long.parseLong(matcher.group(1));
	        removeFilterFromSearch(requestDto, key);
	        return value;
	    }
	    return null;
	}

	public static String getFilterValueAsString(RequestDTO requestDto, String key) {
	    if (requestDto == null || requestDto.getSearch() == null) {
	        return null;
	    }

	    String regex = key + ":[a-zA-Z]+:([^;]+)";
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(requestDto.getSearch());

	    if (matcher.find()) {
	        String value = matcher.group(1);
	        removeFilterFromSearch(requestDto, key);
	        return value;
	    }
	    return null;
	}

	/**
	 * Removes the extracted filter from the search string.
	 */
	private static void removeFilterFromSearch(RequestDTO requestDto, String key) {
	    String search = requestDto.getSearch();
	    String regex = key + ":[a-zA-Z]+:[^;]+;?";
	    requestDto.setSearch(search.replaceAll(regex, "").replaceAll(";;", ";").replaceAll("^;|;$", ""));
	}
}