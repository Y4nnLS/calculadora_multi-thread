import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        String expressao = "((a + b) * (c - d) / (a + a) * (d - a)) + 4 * 2 + (e * 7)";
        Map<String, Double> variaveis = new HashMap<>();
        variaveis.put("a", 1.0);
        variaveis.put("b", 2.0);
        variaveis.put("c", 3.0);
        variaveis.put("d", 4.0);
        variaveis.put("e", 0.5);

        System.out.println("Expressão: " + expressao);
        System.out.println("Variáveis: " + variaveis);
        System.out.println("----------------------------------");

        Node raiz = ExpressionParser.parse(expressao);
        raiz.setVariaveis(variaveis);

        Thread avaliador = new Thread(raiz);
        avaliador.start();

        synchronized (raiz) {
            while (!raiz.isComputado()) {
                try {
                    raiz.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("\nResultado final: " + raiz.getResultado());
    }
}
