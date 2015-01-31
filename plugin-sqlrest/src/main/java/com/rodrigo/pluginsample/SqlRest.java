package com.rodrigo.pluginsample;

import java.io.StringReader;
import java.math.BigDecimal;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

public class SqlRest implements ServiceHandlerInterface {
    private static final Logger logger = Logger.getLogger(SqlRest.class);

    public ServiceResponse parse(String data) {
        if (data == null || data.trim().isEmpty()) {
            return new ServiceResponse(null, Status.ERROR, "null");
        }

        BigDecimal val = null;

        try {
            JAXBContext context = JAXBContext.newInstance(Invoice.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader reader = new StringReader(data);
            Invoice i = (Invoice) unmarshaller.unmarshal(reader);
            val = i.getTotal();

            if (val != null) {
                return new ServiceResponse(val.toString(), Status.SUCCESS, null);
            } else {
                return new ServiceResponse(null, Status.ERROR, data);
            }

        } catch (Exception e) {
            logger.error("Error: ", e);
            return new ServiceResponse(null, Status.ERROR, e.getMessage());
        }
    }
}
