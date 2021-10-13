package com.epam.esm.validator;

import static com.epam.esm.exception.ErrorCode.INCORRECT_PARAM;
import static com.epam.esm.exception.ErrorMessageKey.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.epam.esm.entity.OrderType;
import com.epam.esm.entity.ParamName;
import com.epam.esm.entity.SortType;
import com.epam.esm.exception.InvalidParamException;

/**
 * The {@code ParamValidator} class validates sort parameters
 * 
 * @author Ihar Klepcha
 */
public class ParamValidator {
	
	/**
	 * Checks presence parameters and errors
	 * 
	 * @param params {@link Map} of {@link String} and {@link String} parameters
	 */
	public static void validateSortParam(Map<String, String> params) {
		List<String> errorMessageKeys = new ArrayList<>();
		if (params.containsKey(ParamName.SORT_BY.toString().toLowerCase())) {
			validateSort(params.get(ParamName.SORT_BY.toString().toLowerCase()), errorMessageKeys);
		}
		if (params.containsKey(ParamName.ORDER_BY.toString().toLowerCase())) {
			validateOrder(params.get(ParamName.ORDER_BY.toString().toLowerCase()), errorMessageKeys);
		}
		if (!errorMessageKeys.isEmpty()) {
			throw new InvalidParamException(errorMessageKeys, INCORRECT_PARAM.getErrorCode());
		}
	}

	/**
	 * Checks sort type
	 * 
	 * @param sortBy {@link String} value sort type 
	 * @param params {@link Map} of {@link String} and {@link String} parameters
	 */
	private static void validateSort(String sortBy, List<String> errorMessageKeys) {
		if (!EnumUtils.isValidEnumIgnoreCase(SortType.class, sortBy)) {
			errorMessageKeys.add(INCORRECT_SORT_PARAM.getErrorMessageKey());
		}
	}

	/**
	 * Checks order type
	 * 
	 * @param orderBy {@link String} value order type 
	 * @param params {@link Map} of {@link String} and {@link String} parameters
	 */
	private static void validateOrder(String orderBy, List<String> errorMessageKeys) {
		if (!EnumUtils.isValidEnumIgnoreCase(OrderType.class, orderBy)) {
			errorMessageKeys.add(INCORRECT_ORDER_PARAM.getErrorMessageKey());
		}
	}

	public static void validatePaginationParam(Map<String, String> params) {
		List<String> errorMessageKeys = new ArrayList<>();
		if (params.containsKey(ParamName.OFFSET.toString().toLowerCase())) {
			validateOffset(params.get(ParamName.OFFSET.toString().toLowerCase()), errorMessageKeys);
		}
		if (params.containsKey(ParamName.LIMIT.toString().toLowerCase())) {
			validateLimit(params.get(ParamName.LIMIT.toString().toLowerCase()), errorMessageKeys);
		}
		if (!errorMessageKeys.isEmpty()) {
			throw new InvalidParamException(errorMessageKeys, INCORRECT_PARAM.getErrorCode());
		}
	}

	public static void validateOffset(String offset, List<String> errorMessageKeys) {
		if (NumberUtils.isParsable(offset)) {
			if (Integer.parseInt(offset) < 0) {
			errorMessageKeys.add(INCORRECT_OFFSET_PARAM.getErrorMessageKey());
			}
		} else {
			errorMessageKeys.add(INCORRECT_OFFSET_PARAM.getErrorMessageKey());
		}
	}
	
	public static void validateLimit(String limit, List<String> errorMessageKeys) {
		if (NumberUtils.isParsable(limit)) {
			if (Integer.parseInt(limit) < 0) {
				errorMessageKeys.add(INCORRECT_OFFSET_PARAM.getErrorMessageKey());
			}
		} else {
			errorMessageKeys.add(INCORRECT_LIMIT_PARAM.getErrorMessageKey());
		}
	}
}
