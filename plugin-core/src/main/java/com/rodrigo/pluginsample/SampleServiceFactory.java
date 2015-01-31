package com.rodrigo.pluginsample;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class SampleServiceFactory {

    // http://duckranger.com/2011/06/jaxb-without-a-schema/
    // https://code.google.com/p/json-simple/wiki/DecodingExamples
    // http://www.thomas-bayer.com/sqlrest/INVOICE/,com.rodrigo.plugin.SqlRest
    // http://httpbin.org/get?value=15.00&id=,com.rodrigo.plugin.HttpBin

    private static final Logger logger = Logger.getLogger(SampleServiceFactory.class);
    private static final String URL = "url";
    private static SampleServiceFactory INSTANCE;

    private Map<String, SampleService> services_ = new HashMap<String, SampleService>();

    private SampleServiceFactory() {
    }

    public static SampleService getService(String serviceName) {
        if (INSTANCE == null) {
            INSTANCE = new SampleServiceFactory();
            INSTANCE.init();
        }

        return INSTANCE.services_.get(serviceName);
    }

    private void init() {
        String url_properties_ = System.getProperty(URL);

        loadConfig(url_properties_, services_);

        logger.info("services: " + services_);
    }

    private void loadConfig(String url_properties, Map<String, SampleService> urls) {
        String[] urla = url_properties.split(",");

        for (String urlname : urla) {
            String urlpar = System.getProperty(urlname);
            String[] pars = urlpar.split(",");
            String name = pars[0];
            String url = pars[1];
            String clazzname = pars[2];

            try {
                @SuppressWarnings("rawtypes")
                Class clazz = Class.forName(clazzname);

                ServiceHandlerInterface inst = (ServiceHandlerInterface) clazz.newInstance();
                SampleService p = new SampleService(name, url, inst);
                urls.put(name, p);

            } catch (Exception e) {
                logger.error("Error: ", e);
            }
        }
    }
}