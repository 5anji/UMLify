package com.lexer.keywords;

public enum Attribute {
    name, stereotype, fields, methods, source, dest,
    type, category, incoming_nodes, outcoming_nodes;

    public static boolean contains (String e) {
        for (Attribute ent : values()) {
            if (ent.toString().equals(e)) {
                return true;
            }
        }

        return false;
    }
}
