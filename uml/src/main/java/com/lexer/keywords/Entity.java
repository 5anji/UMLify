package com.lexer.keywords;

public enum Entity {
    diagram,
    actor, usecase, 
    message, control, boundary, entity,
    clas, 
    state, 
    activity, object, initial_node, final_node, fork,
    component, assembly_connector,
    node;

    public static boolean contains (String e) {
        for (Entity ent : values()) {
            if (ent.toString().equals(e)) {
                return true;
            }
        }

        return false;
    }
}
