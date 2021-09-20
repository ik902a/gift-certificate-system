package com.epam.esm.validator;

import static com.epam.esm.exception.ErrorCode.GIFT_CERTIFICATE_INCORRECT_DATA;
import static com.epam.esm.exception.ErrorMessageKey.INCORRECT_ORDER_PARAM;
import static com.epam.esm.exception.ErrorMessageKey.INCORRECT_SORT_PARAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.EnumUtils;

import com.epam.esm.entity.OrderType;
import com.epam.esm.entity.ParamName;
import com.epam.esm.entity.SortType;
import com.epam.esm.exception.InvalidDataException;

public class ParamValidator {
	public static void validateSortParam(Map<String, String> params) {
		List<String> errorMessageKeys = new ArrayList<>();
		if (params.containsKey(ParamName.SORT_BY.toString().toLowerCase())) {
			validateSort(params.get(ParamName.SORT_BY.toString().toLowerCase()), errorMessageKeys);
		}
		if (params.containsKey(ParamName.ORDER_BY.toString().toLowerCase())) {
			validateOrder(params.get(ParamName.ORDER_BY.toString().toLowerCase()), errorMessageKeys);
		}
		if (!errorMessageKeys.isEmpty()) {
			throw new InvalidDataException(errorMessageKeys, 
					GIFT_CERTIFICATE_INCORRECT_DATA.getErrorCode());
		}
	}

	private static void validateSort(String sortBy, List<String> errorMessageKeys) {
		if (!EnumUtils.isValidEnumIgnoreCase(SortType.class, sortBy)) {
			errorMessageKeys.add(INCORRECT_SORT_PARAM.getErrorMessageKey());
		}
	}

	private static void validateOrder(String orderBy, List<String> errorMessageKeys) {
		if (!EnumUtils.isValidEnumIgnoreCase(OrderType.class, orderBy)) {
			errorMessageKeys.add(INCORRECT_ORDER_PARAM.getErrorMessageKey());
		}
	}
}
