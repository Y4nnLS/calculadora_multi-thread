package Operacoes;

public class SomaThread extends Thread {
    private final double valor1;
    private final double valor2;
    private double resultado;
    private boolean concluido = false;

    public SomaThread(double valor1, double valor2) {
        this.valor1 = valor1;
        this.valor2 = valor2;
    }

    public void run() {
        synchronized (this) {
            resultado = valor1 + valor2;
            System.out.println("Soma: " + valor1 + " + " + valor2 + " = " + resultado);
            concluido = true;
            notifyAll();
        }
    }

    public synchronized double getResultado() {
        while (!concluido) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return resultado;
    }
}
