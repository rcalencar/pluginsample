package com.rodrigo.pluginsample;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class HttpBin implements ServiceHandlerInterface {
    private static final Logger logger = Logger.getLogger(HttpBin.class);
    
    public ServiceResponse parse(String data) {

	if (data == null || data.trim().isEmpty()) {
	    return new ServiceResponse(null, Status.ERROR, "null");
	}

	String val = null;

	JSONParser parser = new JSONParser();

	Object obj;
	try {
	    obj = parser.parse(data);

	    JSONObject o1 = (JSONObject) obj;
	    JSONObject o2 = (JSONObject) o1.get("args");
	    val = (String) o2.get("value");

	    if (val != null) {
		return new ServiceResponse(val, Status.SUCCESS, null);
	    } else {
		return new ServiceResponse(null, Status.ERROR, data);
	    }

	} catch (ParseException e) {
	    logger.error("Error: ", e);
	    return new ServiceResponse(null, Status.ERROR, e.getMessage());
	}
    }
}
