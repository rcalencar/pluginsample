package com.rodrigo.pluginsample;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;


public class SampleService {
    private static final Logger logger = Logger.getLogger(SampleServiceFactory.class);
    
    public final String name;
    public final String url;
    public final ServiceHandlerInterface handler;

    public SampleService(String name, String url,
	    ServiceHandlerInterface handler) {
	super();
	this.name = name;
	this.url = url;
	this.handler = handler;
    }

    private String getUrl(String account) {
	return url + account;
    }
    
    public ServiceResponse query(String id) {
        CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpGet httpget = new HttpGet(getUrl(id));

        logger.info("querying=" + httpget.getURI());

        // Create a custom response handler
        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
            public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            }
        };

        String responseBody = null;
        ServiceResponse response = null;
        try {
            responseBody = httpclient.execute(httpget, responseHandler);
            logger.info("Http result: " + id + "=" + responseBody);

            response = handler.parse(responseBody);
            logger.info("Service result: " + id + "=" + response);

        } catch (Exception e) {
            logger.error("Error: " + toString(), e);

        } finally {

            try {
                httpclient.close();

            } catch (IOException e) {
                logger.error("Error: " + toString(), e);
            }
        }

        return response;
    }
    
    @Override
    public String toString() {
	return "CurrencyService [name=" + name + ", url=" + url + ", handler="
		+ handler + "]";
    }
}
