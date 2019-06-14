package org.post.web.controller.validation;

import org.post.exception.ValidationException;

import java.sql.Date;
import java.util.*;

public class Validator {

	private static List results;
	private static Iterator result;
	private static List<String> options = Arrays.asList("Win", "Each-way", "Forecast", "Tricast", "Head-to-head");

	public static void initValidation() {
		results = new LinkedList();
	}

	public static void finishValidation() {
		results = null;
		result = null;
	}

	public static void validate(String value, Validate type) {
		switch (type) {
			case NULLABLE_STRING:
				if (value.isEmpty()) {
					results.add(null);
					break;
				}
			case STRING:
				validateString(value);
				break;
			case NULLABLE_LONG:
				if (value.isEmpty()) {
					results.add(null);
					break;
				}
			case LONG:
				validateLong(value);
				break;
			case NULLABLE_FLOAT:
				if (value.isEmpty()) {
					results.add(null);
					break;
				}
			case FLOAT:
				validateFloat(value);
				break;
			case NULLABLE_DATE:
				if (value.isEmpty()) {
					results.add(null);
					break;
				}
			case DATE:
				validateDate(value);
				break;
			case OPTIONS:
				validateOptions(value);
				break;
		}
	}

	private static void validateString(String stringValue) {
		if (!Optional.ofNullable(stringValue).filter(x -> !x.isEmpty()).isPresent() &&
				stringValue.chars().allMatch(Character::isLetter))
			throw new ValidationException("Value \"" + stringValue + "\" is not a character sequence or is empty...");

		results.add(stringValue);
	}

	private static void validateLong(String longValue) {
		try {
			results.add(Long.valueOf(longValue));
		} catch (NumberFormatException ignored) {
			throw new ValidationException("Value: \"" + longValue + "\" is not an integer or is invalid...");
		}
	}

	private static void validateFloat(String floatValue) {
		try {
			results.add(Float.valueOf(floatValue));
		} catch (NumberFormatException ignored) {
			throw new ValidationException("Value: \"" + floatValue + "\" is not float or is invalid...");
		}
	}

	private static void validateDate(String dateValue) {
		try {
			results.add(Date.valueOf(dateValue));
		} catch (IllegalArgumentException ignored) {
			throw new ValidationException("Value: \"" + dateValue + "\" is not a date or is invalid...");
		}
	}

	private static void validateOptions(String optionValue) {
		if (!options.contains(optionValue))
			throw new ValidationException("Value: \"" + optionValue + "\" is out of options...");

		results.add(optionValue);
	}

	public static Long getLong() {
		if (result == null) result = results.iterator();
		return (Long) result.next();
	}

	public static Float getFloat() {
		if (result == null) result = results.iterator();
		return (Float) result.next();
	}

	public static Date getDate() {
		if (result == null) result = results.iterator();
		return (Date) result.next();
	}

	public static String getString() {
		if (result == null) result = results.iterator();
		return (String) result.next();
	}
}
