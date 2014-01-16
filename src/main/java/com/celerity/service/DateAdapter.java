package com.celerity.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.WebApplicationException;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateAdapter  extends XmlAdapter<String, Date> {

    public static final SimpleDateFormat outDateFormat = new SimpleDateFormat("MM/dd/yyyy");
    
    public static final SimpleDateFormat inDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public String marshal(Date v) {
        return outDateFormat.format(v);
    }

    @Override
    public Date unmarshal(String v) {
        try {
            return inDateFormat.parse(v);
        } catch (ParseException e) {
            throw new WebApplicationException();
        }
    }
}

