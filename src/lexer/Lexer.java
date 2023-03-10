package lexer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lexer {

    private Set<String> structural_elements;
    private Set<String> entities;
    private Set<String> attributes;
    private String RELATION = "relation";
    private char ASSIGNMENT = '=';
    private char START_TAG = '<';
    private char END_TAG = '<';


    public Lexer () {
        structural_elements = new HashSet<>(Arrays.asList("package", "diagram"));
        entities = new HashSet<>(Arrays.asList("actor", "usecase"));
        attributes = new HashSet<>(Arrays.asList("name", "type"));
    }


    public List<Token> analyze (String l) {
        List<Token> lexems = new ArrayList<>();

        String[] tokens = tokenize(l);
        int counter = 1;
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i].toLowerCase();
            
            if (structural_elements.contains(token)) {
                lexems.add(new Token(counter, "STRUCTURE", tokens[i]));
            } else if (entities.contains(token)) {
                lexems.add(new Token(counter, "ENTITY", tokens[i]));
            } else if (attributes.contains(token)) {
                lexems.add(new Token(counter, "ATTRIBUTE", tokens[i]));
            } else if (token.compareTo(RELATION) == 0) {
                lexems.add(new Token(counter, "RELATION", tokens[i]));
            } else if (token.compareTo(Character.toString(ASSIGNMENT)) == 0) {
                lexems.add(new Token(counter, "ASSIGNMENT", tokens[i]));
            } else if (token.compareTo(Character.toString(START_TAG)) == 0  ||  token.compareTo(Character.toString(END_TAG)) == 0) {
                lexems.add(new Token(counter, "TAG LIMIT", tokens[i]));
            }

            counter++;
        }
        return lexems;
    }


    private String[] tokenize (String l) {
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < l.length() - 1; i++) {
            char curr = l.charAt(i);
            char next = l.charAt(i + 1);

            sb.append(curr);

            if ((Character.isLetter(curr) || Character.isDigit(curr))  &&  !Character.isLetter(next)  &&  next != 39  &&  next != ' ') {
                sb.append(" ");
            } else if (curr >= 60  &&  curr <= 62) {
                if (Character.isLetter(next) || Character.isDigit(next) ||  next == 39) {
                    sb.append(" ");
                }
            } else if (curr == 39  &&  !Character.isLetter(next)) {
                sb.append(" ");
            }
        }
        sb.append(l.charAt(l.length() - 1));

        return sb.toString().split(" ");
    }
}
