package com.epam.esm.validator;

import static com.epam.esm.exception.ErrorCode.*;
import static com.epam.esm.exception.ErrorMessageKey.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.InvalidDataException;

/**
 * The {@code GiftCertificateValidator} class validates gift ñertificate data
 * 
 * @author Ihar Klepcha
 */
public class GiftCertificateValidator {
	public static final int MIN_ID = 1;
	public static final int MAX_LENGTH_NAME = 45;
	public static final int MAX_LENGTH_DESCRIPTION = 100;
	public static final int MAX_SCALE_PRICE = 2;
	public static final BigDecimal MIN_PRICE = new BigDecimal("0.01");
	public static final BigDecimal MAX_PRICE = new BigDecimal("9999.99");
	public static final int MIN_DURATION = 1;
	public static final int MAX_DURATION = 365;

	/**
	 * Checks for invalid gift ñertificate data
	 * 
	 * @param giftCertificate {@link GiftCertificate} gift certificate
	 */
	public static void validateGiftCertificate(GiftCertificate giftCertificate) {
		List<String> errorMessageKeys = new ArrayList<>();
		validateName(giftCertificate.getName(), errorMessageKeys);
		validateDescription(giftCertificate.getDescription(), errorMessageKeys);
		validatePrice(giftCertificate.getPrice(), errorMessageKeys);
		validateDuration(giftCertificate.getDuration(), errorMessageKeys);
		List<Tag> tagList = giftCertificate.getTags();
		if (tagList != null && !tagList.isEmpty()) {
			tagList.forEach(tag -> validateTagName(tag.getName(), errorMessageKeys));
		}
		if (!errorMessageKeys.isEmpty()) {
			throw new InvalidDataException(errorMessageKeys, String.valueOf(giftCertificate.getId()),
					GIFT_CERTIFICATE_INCORRECT_DATA.getErrorCode());
		}
	}

    /**
  	 * Checks if id is valid
  	 * 
  	 * @param id {@link long} gift certificate id
  	 */
	public static void validateId(long id) {
		if (id < MIN_ID) {
			throw new InvalidDataException(Arrays.asList(INCORRECT_ID.getErrorMessageKey()), 
					String.valueOf(id),
					GIFT_CERTIFICATE_INCORRECT_ID.getErrorCode());
		}
	}

    /**
  	 * Checks if name is valid
  	 * 
  	 * @param name {@link String} name
  	 * @param errorMessageKeys {@link List} of {@link String} list keys messages for warning 
  	 */
	private static void validateName(String name, List<String> errorMessageKeys) {
		if (StringUtils.isBlank(name) || name.length() > MAX_LENGTH_NAME) {
			errorMessageKeys.add(INCORRECT_NAME.getErrorMessageKey());
		}
	}

    /**
  	 * Checks if description is valid
  	 * 
  	 * @param description {@link String} description
  	 * @param errorMessageKeys {@link List} of {@link String} list keys messages for warning 
  	 */
	private static void validateDescription(String description, List<String> errorMessageKeys) {
		if (StringUtils.isBlank(description) || description.length() > MAX_LENGTH_DESCRIPTION) {
			errorMessageKeys.add(INCORRECT_DESCRIPTION.getErrorMessageKey());
		}
	}

    /**
  	 * Checks if price is valid
  	 * 
  	 * @param price {@link BigDecimal} price
  	 * @param errorMessageKeys {@link List} of {@link String} list keys messages for warning 
  	 */
	private static void validatePrice(BigDecimal price, List<String> errorMessageKeys) {
		if (price == null || price.scale() > MAX_SCALE_PRICE || price.compareTo(MIN_PRICE) < 0
				|| price.compareTo(MAX_PRICE) > 0) {
			errorMessageKeys.add(INCORRECT_PRICE.getErrorMessageKey());
		}
	}

    /**
  	 * Checks if duration is valid
  	 * 
  	 * @param duration {@link int} duration
  	 * @param errorMessageKeys {@link List} of {@link String} list keys messages for warning 
  	 */
	private static void validateDuration(int duration, List<String> errorMessageKeys) {
		if (duration < MIN_DURATION || duration > MAX_DURATION) {
			errorMessageKeys.add(INCORRECT_DURATION.getErrorMessageKey());
		}
	}

    /**
  	 * Checks if tag name is valid
  	 * 
  	 * @param name {@link String} tag name
  	 * @param errorMessageKeys {@link List} of {@link String} list keys messages for warning 
  	 */
	private static void validateTagName(String name, List<String> errorMessageKeys) {
		if (StringUtils.isBlank(name) || name.length() > MAX_LENGTH_NAME) {
			errorMessageKeys.add(INCORRECT_TAG_NAME.getErrorMessageKey());
		}
	}
}
