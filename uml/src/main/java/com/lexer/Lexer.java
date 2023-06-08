package com.lexer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.lexer.keywords.*;

public class Lexer {

    private String RELATION = "relation";
    private char ASSIGNMENT = '=';

    public List<Token> analyze(String path) {
        List<Token> lexems = new ArrayList<>();
        String text = new String();

        try {
            File file = new File(path);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                text += sc.nextLine();
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] tokens = tokenize(text);

        int counter = 1;
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i].toLowerCase();

            if (Entity.contains(token)) {
                lexems.add(new Token(counter, "ENTITY", tokens[i]));
            } else if (Attribute.contains(token)) {
                lexems.add(new Token(counter, Category.ATTRIBUTE.toString(), tokens[i]));
            } else if (token.compareTo(RELATION) == 0) {
                lexems.add(new Token(counter, Category.RELATION.toString(), tokens[i]));
            } else if (token.compareTo(Character.toString(ASSIGNMENT)) == 0) {
                lexems.add(new Token(counter, Category.ASSIGNMENT.toString(), tokens[i]));
            } else if (token.compareTo(">") == 0) {
                lexems.add(new Token(counter, Category.TAG_END.toString(), tokens[i]));
            } else if (token.compareTo("<") == 0) {
                lexems.add(new Token(counter, Category.TAG_START.toString(), tokens[i]));
            } else if (token.contains("'")) {
                lexems.add(new Token(counter, Category.VALUE.toString(), tokens[i]));
            } else {
                if (token.contains(" ")) {
                    System.out.println("Syntax error in the word: " + token);
                }
            }

            counter++;
        }
        return lexems;
    }

    private String[] tokenize(String l) {
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < l.length() - 1; i++) {
            char curr = l.charAt(i);
            char next = l.charAt(i + 1);

            sb.append(curr);

            if ((Character.isLetter(curr) || Character.isDigit(curr)) && !Character.isLetter(next) && next != 39
                    && next != ' ') {
                sb.append(" ");
            } else if (curr >= 60 && curr < 62) {
                if (Character.isLetter(next) || Character.isDigit(next) || next == 39) {
                    sb.append(" ");
                }
            } else if (curr == 39 && !Character.isLetter(next)) {
                sb.append(" ");
            } else if (curr == 62) {
                sb.append(" ");
            }
        }
        sb.append(l.charAt(l.length() - 1));

        return sb.toString().split(" ");
    }
}
