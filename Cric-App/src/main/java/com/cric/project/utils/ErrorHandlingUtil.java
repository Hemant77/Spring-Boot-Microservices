package com.cric.project.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * ErrorHandlingUtil class
 * 
 * ErrorHandlingUtil class is utility for error handling operation
 * 
 * @author Hemant Dhamal
 * @version 1.0
 *
 */
public class ErrorHandlingUtil {
	public static final String isAccessAllowed(BindingResult bindingResult) throws JSONException {
		JSONObject resp = new JSONObject();
		List<FieldError> errors = bindingResult.getFieldErrors();
		Map<String, String> resError = new HashMap<>();
		for (FieldError error : errors) {
			resError.put(error.getField(), error.getDefaultMessage());
		}
		return resp.put("error", resError.toString()).toString();
	}

}
