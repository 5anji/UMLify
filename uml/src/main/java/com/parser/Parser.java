package com.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import com.lexer.Lexer;
import com.lexer.Token;

import com.lexer.keywords.Category;

public class Parser {

    private Lexer lexer;
    private List<Element> parseList;

    private boolean containsNonNumeric(String str) {
        String temp = str.replace("'", "");
        String pattern = "^(?![0-9']+$).*";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(temp);
        return matcher.matches();
    }

    public Parser() {
        lexer = new Lexer();
        parseList = new ArrayList<>();
    }

    public List<Element> getParseList() {
        return parseList;
    }

    public void fillParseList(String path) {
        List<Token> tokens = lexer.analyze(path);

        int index = 0;
        boolean tagStarted = false;

        while (index < tokens.size()) {
            byte marker = 0;
            String elementName = tokens.get(index).getLiteral().toLowerCase();

            if (!containsNonNumeric(elementName)) {
                System.err.println("WARNING: only digits are inside element  >>> " + elementName + " <<<");
                System.err.flush();
            }

            if (tokens.get(index).getCategory().equals(Category.TAG_START.toString())) {
                tagStarted = true;
            }

            if (tokens.get(index).getCategory().equals(Category.TAG_END.toString())) {
                if (!tagStarted) {
                    System.err.println("ERROR: cannot find tag beginning");
                    System.err.flush();
                    System.exit(1);
                } else {
                    tagStarted = false;
                }
            }

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
        if (tagStarted) {
            System.err.println("ERROR: cannot find tag ending");
            System.err.flush();
            System.exit(1);
        }
    }
}