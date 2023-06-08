package com;

import java.util.List;
import java.util.Map;

import com.interpretation.*;
import com.parser.*;

public class App {

    private static String input_file_path = "/home/lavandaboy/PBL_projects/UMLify_2.0/uml/src/main/java/com/test.xuml";
    public static void main(String[] args) throws Exception {
        
        Parser p = new Parser();
        p.fillParseList(input_file_path);

        System.out.println(createRequest(p.getParseList()));
        String uiRequest = Translator.translate(createRequest(p.getParseList()));
        System.out.println(uiRequest);
        UMLbuilder.drawDiagram(uiRequest);
    }

    static String createRequest (List<Element> elements) {
        StringBuilder sb = new StringBuilder("Transform the following text into PlantUML code to draw a " + elements.get(0).getAttributes().get("type") + " diagram:");
        //StringBuilder sb = new StringBuilder("Draw diagram in ASCII using the following code:");

        for (Element element : elements) {
            sb.append(" { " + element.getName());
            Map<String, String> attributes = element.getAttributes();

            for (String attribute : attributes.keySet()) {
                sb.append(" " + attribute + ": " + attributes.get(attribute) + ";");
            }

            sb.append("}");
        }

        return sb.toString();
    }
}

