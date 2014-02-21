package com.celerity.service.rest.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class BooleanAdapter   extends XmlAdapter<String, Boolean> {

    @Override
    public String marshal(Boolean v) {
        return v ? "true" : "false";
    }

    @Override
    public Boolean unmarshal(String v) {
        return "true".equals(v);
    }

}
