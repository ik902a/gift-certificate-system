package com.epam.esm.validator;

import static com.epam.esm.exception.ErrorCode.INCORRECT_PARAM;
import static com.epam.esm.exception.ErrorMessageKey.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.epam.esm.dao.util.OrderType;
import com.epam.esm.dao.util.ParamName;
import com.epam.esm.dao.util.SortType;
import com.epam.esm.exception.InvalidParamException;

/**
 * The {@code ParamValidator} class validates parameters
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
		if (params.containsKey(ParamName.SORT_BY)) {
			validateSort(params.get(ParamName.SORT_BY), errorMessageKeys);
		}
		if (params.containsKey(ParamName.ORDER_BY)) {
			validateOrder(params.get(ParamName.ORDER_BY), errorMessageKeys);
		}
		if (!errorMessageKeys.isEmpty()) {
			throw new InvalidParamException(errorMessageKeys, INCORRECT_PARAM.getErrorCode());
		}
	}

	/**
	 * Checks sort type
	 * 
	 * @param sortBy {@link String} value sort type 
	 * @param errorMessageKeys {@link List} of {@link String} list of error message keys
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
	 * @param errorMessageKeys {@link List} of {@link String} list of error message keys
	 */
	private static void validateOrder(String orderBy, List<String> errorMessageKeys) {
		if (!EnumUtils.isValidEnumIgnoreCase(OrderType.class, orderBy)) {
			errorMessageKeys.add(INCORRECT_ORDER_PARAM.getErrorMessageKey());
		}
	}

	/**
	 * Checks pagination parameters
	 * 
	 * @param params {@link Map} of {@link String} and {@link String} parameters
	 */
	public static void validatePaginationParam(Map<String, String> params) {
		List<String> errorMessageKeys = new ArrayList<>();
		if (params.containsKey(ParamName.OFFSET)) {
			validateOffset(params.get(ParamName.OFFSET), errorMessageKeys);
		}
		if (params.containsKey(ParamName.LIMIT)) {
			validateLimit(params.get(ParamName.LIMIT), errorMessageKeys);
		}
		if (!errorMessageKeys.isEmpty()) {
			throw new InvalidParamException(errorMessageKeys, INCORRECT_PARAM.getErrorCode());
		}
	}

	/**
	 * Checks offset
	 * 
	 * @param offset {@link String} offset value
	 * @param errorMessageKeys {@link List} of {@link String} list of error message keys
	 */
	public static void validateOffset(String offset, List<String> errorMessageKeys) {
		if (NumberUtils.isParsable(offset)) {
			if (Integer.parseInt(offset) < 0) {
			errorMessageKeys.add(INCORRECT_OFFSET_PARAM.getErrorMessageKey());
			}
		} else {
			errorMessageKeys.add(INCORRECT_OFFSET_PARAM.getErrorMessageKey());
		}
	}
	
	/**
	 * Checks limit
	 * 
	 * @param limit {@link String} limit value 
	 * @param errorMessageKeys {@link List} of {@link String} list of error message keys
	 */
	public static void validateLimit(String limit, List<String> errorMessageKeys) {
		if (NumberUtils.isParsable(limit)) {
			if (Integer.parseInt(limit) < 0) {
				errorMessageKeys.add(INCORRECT_LIMIT_PARAM.getErrorMessageKey());
			}
		} else {
			errorMessageKeys.add(INCORRECT_LIMIT_PARAM.getErrorMessageKey());
		}
	}
}
