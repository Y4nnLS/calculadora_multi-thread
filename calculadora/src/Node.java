import Operacoes.*;

import java.util.Map;

public class Node implements Runnable {
    private final String valor;
    private Node esquerdo;
    private Node direito;

    private double resultado;
    private boolean computado = false;

    private Map<String, Double> variaveis;

    public Node(String valor) {
        this.valor = valor;
    }

    public void setFilhos(Node esquerdo, Node direito) {
        this.esquerdo = esquerdo;
        this.direito = direito;
    }

    public synchronized boolean isComputado() {
        return computado;
    }

    public synchronized double getResultado() {
        while (!computado) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return resultado;
    }

    public void setVariaveis(Map<String, Double> variaveis) {
        this.variaveis = variaveis;
        if (esquerdo != null)
            esquerdo.setVariaveis(variaveis);
        if (direito != null)
            direito.setVariaveis(variaveis);
    }

    @Override
    public void run() {
        if (ehFolha()) {
            resultado = valorNumerico();
            synchronized (this) {
                computado = true;
                notifyAll();
            }
            return;
        }

        Thread esquerdaThread = new Thread(esquerdo);
        Thread direitaThread = new Thread(direito);

        esquerdaThread.start();
        direitaThread.start();

        synchronized (esquerdo) {
            while (!esquerdo.isComputado()) {
                try {
                    esquerdo.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        synchronized (direito) {
            while (!direito.isComputado()) {
                try {
                    direito.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        double valEsquerdo = esquerdo.getResultado();
        double valDireito = direito.getResultado();

        // Aqui usamos suas threads de operação
        resultado = executarOperacao(valor, valEsquerdo, valDireito);
        System.out.println("Calculando " + valEsquerdo + " " + valor + " " + valDireito + " = " + resultado);

        synchronized (this) {
            computado = true;
            notifyAll();
        }
    }

    private boolean ehFolha() {
        return esquerdo == null && direito == null;
    }

    private double valorNumerico() {
        if (variaveis.containsKey(valor)) {
            return variaveis.get(valor);
        }
        return Double.parseDouble(valor);
    }

    private double executarOperacao(String operador, double a, double b) {
        switch (operador) {
            case "+":
                SomaThread soma = new SomaThread(a, b);
                soma.start();
                try {
                    soma.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return soma.getResultado();
            case "-":
                SubtracaoThread subtracao = new SubtracaoThread(a, b);
                subtracao.start();
                try {
                    subtracao.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return subtracao.getResultado();
            case "*":
                MultiplicacaoThread multiplicacao = new MultiplicacaoThread(a, b);
                multiplicacao.start();
                try {
                    multiplicacao.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return multiplicacao.getResultado();
            case "/":
                DivisaoThread divisao = new DivisaoThread(a, b);
                divisao.start();
                try {
                    divisao.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return divisao.getResultado();
            default:
                throw new IllegalArgumentException("Operador inválido: " + operador);
        }
    }
}
