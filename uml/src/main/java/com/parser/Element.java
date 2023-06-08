package com.parser;

import java.util.HashMap;
import java.util.Map;

public class Element {

    private String name;
    private Map<String, String> attributes;

    public Element (String name) {
        this.name = name;
        attributes = new HashMap<>();
    }

    public String getName () {
        return this.name;
    }

    public Map<String, String> getAttributes () {
        return this.attributes;
    }

    public void setAttributes (Map<String, String> attributes) {
        this.attributes = attributes;
    }
}
