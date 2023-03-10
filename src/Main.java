import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lexer.Lexer;
import lexer.Token;

public class Main {
    public static void main(String[] args) {
        // filter by extension
        if (args.length > 0) {
            Pattern pattern = Pattern.compile("([^\\s]+(\\.(?i)(xuml))$)");
            Matcher matcher = pattern.matcher(args[0]);
            if (matcher.find()) {
                Lexer l = new Lexer();
                List<Token> ts = l.analyze(args[0]);
                for (Token token : ts) {
                    System.out.println(
                            "ID: " + token.getID() + "; Category: " + token.getCategory() + "; Literal: "
                                    + token.getLiteral());
                }
            } else {
                System.out.println("No file provided");
            }
        } else {
            System.out.println("Requires at least one more argument");
        }

    }
}
