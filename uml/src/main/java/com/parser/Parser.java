package com.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lexer.Lexer;
import com.lexer.Token;

import com.lexer.keywords.Category;

public class Parser {

    private Lexer lexer;
    private List<Element> parseList;

    public Parser () {
        lexer = new Lexer();
        parseList = new ArrayList<>();
    }

    public List<Element> getParseList () {
        return this.parseList;
    }

    public void fillParseList (String path) {
        List<Token> tokens = lexer.analyze(path);

        int index = 0;
        while (index < tokens.size() - 1) {
            byte marker = 0;
            String elementName = tokens.get(index).getLiteral().toLowerCase();

            if (tokens.get(index).getCategory().equals(Category.ENTITY.toString()) 
                ||  tokens.get(index).getCategory().equals(Category.RELATION.toString())) {
                Element e = new Element(elementName);
                Map<String, String> attributes = new HashMap<>();
                index++;
                
                while ((!tokens.get(index).getCategory().equals(Category.ENTITY.toString()) 
                        &&  !tokens.get(index).getCategory().equals(Category.RELATION.toString())) 
                        &&  index < tokens.size() - 1) {
                    if (tokens.get(index).getCategory().equals(Category.ATTRIBUTE.toString())) {
                        attributes.put(tokens.get(index).getLiteral(), tokens.get(index + 2).getLiteral().replace("'", ""));
                        index += 3;
                    } else {
                        index++;
                    }
                }

                e.setAttributes(attributes);
                this.parseList.add(e);
                marker++;
            }

            if (marker == 0) {
                index++;
            }
        }
    }
}