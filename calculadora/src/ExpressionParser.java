import java.util.*;

public class ExpressionParser {

    private static final Set<String> OPERADORES = Set.of("+", "-", "*", "/");
    private static final Map<String, Integer> PRECEDENCIA = Map.of(
            "+", 1,
            "-", 1,
            "*", 2,
            "/", 2);

    public static Node parse(String expressao) {
        List<String> tokens = tokenizar(expressao);
        return construirArvore(tokens);
    }

    private static List<String> tokenizar(String expressao) {
        List<String> tokens = new ArrayList<>();
        StringBuilder buffer = new StringBuilder();

        for (int i = 0; i < expressao.length(); i++) {
            char c = expressao.charAt(i);

            if (Character.isWhitespace(c))
                continue;

            if (Character.isLetterOrDigit(c) || c == '.') {
                buffer.append(c);
            } else {
                if (!buffer.isEmpty()) {
                    tokens.add(buffer.toString());
                    buffer.setLength(0);
                }
                tokens.add(String.valueOf(c));
            }
        }

        if (!buffer.isEmpty()) {
            tokens.add(buffer.toString());
        }

        return tokens;
    }

    private static Node construirArvore(List<String> tokens) {
        Stack<Node> operandos = new Stack<>();
        Stack<String> operadores = new Stack<>();

        for (String token : tokens) {
            if (OPERADORES.contains(token)) {
                while (!operadores.isEmpty() && !operadores.peek().equals("(") &&
                        PRECEDENCIA.get(operadores.peek()) >= PRECEDENCIA.get(token)) {
                    processaOperador(operadores.pop(), operandos);
                }
                operadores.push(token);
            } else if (token.equals("(")) {
                operadores.push(token);
            } else if (token.equals(")")) {
                while (!operadores.isEmpty() && !operadores.peek().equals("(")) {
                    processaOperador(operadores.pop(), operandos);
                }
                operadores.pop();
            } else {
                operandos.push(new Node(token));
            }
        }

        while (!operadores.isEmpty()) {
            processaOperador(operadores.pop(), operandos);
        }

        return operandos.pop();
    }

    private static void processaOperador(String operador, Stack<Node> operandos) {
        Node direito = operandos.pop();
        Node esquerdo = operandos.pop();
        Node operadorNode = new Node(operador);
        operadorNode.setFilhos(esquerdo, direito);
        operandos.push(operadorNode);
    }
}
