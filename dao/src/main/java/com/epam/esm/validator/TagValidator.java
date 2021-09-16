package com.epam.esm.validator;

import static com.epam.esm.exception.ErrorCode.*;
import static com.epam.esm.exception.ErrorMessageKey.*;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import com.epam.esm.exception.InvalidDataException;

public class TagValidator {
	public static final int MIN_ID = 1;
	public static final int MAX_LENGTH_NAME = 45;

	public static void validateId(long id) {
		if (id < MIN_ID) {
			throw new InvalidDataException(Arrays.asList(INCORRECT_ID.getErrorMessageKey()), 
					String.valueOf(id),
					GIFT_CERTIFICATE_INCORRECT_ID.getErrorCode());
		}
	}

	public static void validateName(String name) {
		if (StringUtils.isBlank(name) || name.length() > MAX_LENGTH_NAME) {
			throw new InvalidDataException(Arrays.asList(INCORRECT_TAG_NAME.getErrorMessageKey()), 
					name,
					TAG_INCORRECT_DATA.getErrorCode());
		}
	}
}
