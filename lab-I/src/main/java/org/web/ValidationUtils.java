package org.web;

import java.util.Optional;

public class ValidationUtils {
	static boolean validateString(String value) {
		return Optional.ofNullable(value).filter(x -> !x.isEmpty()).isPresent();
	}

	static boolean validateInt(String value) {
		try {
			Integer.valueOf(value);
		} catch (NumberFormatException ignored) {
			return false;
		}
		return true;
	}

	static boolean validateFloat(String value) {
		try {
			Float.valueOf(value);
		} catch (NumberFormatException ignored) {
			return false;
		}
		return true;
	}
}
