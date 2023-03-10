import java.util.List;

import lexer.Lexer;
import lexer.Token;

public class Main {
    public static void main(String[] args) {

        Lexer l = new Lexer();
        String line = "<Diagram type=3>";

        List<Token> ts = l.analyze(line);
        for (Token token : ts) {
            System.out.println("ID: "+token.getID()+"; Category: "+token.getCategory()+"; Literal: "+token.getLiteral());
        }
    }
}
