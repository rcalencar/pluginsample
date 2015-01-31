package com.rodrigo.pluginsample;


import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

//<INVOICE xmlns:xlink="http://www.w3.org/1999/xlink">
//<ID>5</ID>
//<CUSTOMERID xlink:href="http://www.thomas-bayer.com/sqlrest/CUSTOMER/34/">34</CUSTOMERID>
//<TOTAL>4182.90</TOTAL>
//</INVOICE>

@XmlRootElement(name = "INVOICE")
public class Invoice {

    private long id;
    private BigDecimal total;

    @XmlElement(name="ID")
    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    @XmlElement(name="TOTAL")
    @XmlJavaTypeAdapter(BigDecimalAdapter.class)
    public BigDecimal getTotal() {
	return total;
    }

    public void setTotal(BigDecimal total) {
	this.total = total;
    }

}
