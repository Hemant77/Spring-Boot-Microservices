package com.cric.project.utils;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ResponseUtil class
 * 
 * ResponseUtil class is utility for building API response
 * 
 * @author Hemant Dhamal
 * @version 1.0
 *
 */
public class ResponseUtil {

	private final static Logger logger = LoggerFactory.getLogger(ResponseUtil.class);

	public static final String buildResponseJson(boolean error, String message, JSONObject responseData)
			throws Exception {
		JSONObject responseObject = new JSONObject();
		try {
			if (error)
				responseObject.put("error", true);
			else
				responseObject.put("error", false);
			if (message != null && !message.isEmpty())
				responseObject.put("message", message);
			if (responseData != null)
				responseObject.put("data", responseData);
		} catch (JSONException e) {
			logger.error("Error while parsing response JSON", e);
			throw new Exception(e.getMessage(), e.getCause());
		}
		return responseObject.toString();
	}

}
