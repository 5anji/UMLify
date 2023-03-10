package lexer;

public class Lexer {
    public static void main(String[] args) {
        
        String line = "<diagram type=3>";

        String[] tokens = tokenize(line);
        for (int i = 0; i < tokens.length; i++) {
            System.out.println(tokens[i]);
        }
    }

    static String[] tokenize (String l) {
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
