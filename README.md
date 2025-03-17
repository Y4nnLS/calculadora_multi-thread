# 📐 Calculadora de Expressões Matemáticas Multi-thread  
Um projeto em **Java** que avalia expressões matemáticas genéricas, utilizando **paralelismo com múltiplas threads**, respeitando a **ordem de precedência das operações**. Implementa **sincronização com `synchronized`, `wait()` e `notifyAll()`** para garantir consistência e performance.

---

## 🚀 Funcionalidades

✅ **Parser de expressões matemáticas**  
✅ **Criação de árvore de expressão (AST)**  
✅ **Execução paralela das operações com threads dedicadas**  
✅ Respeita a **ordem de prioridade** dos operadores matemáticos  
✅ Sincronização utilizando **`synchronized`**, **`wait()`** e **`notifyAll()`**  
✅ Otimiza o número de threads, melhorando o desempenho  
✅ Suporte a variáveis e valores literais  
✅ Fácil de estender com novas operações e funções matemáticas

---

## 🛠️ Tecnologias Utilizadas
- ☕ **Java SE**  
- **Threads**  
- **Programação Concorrente** com `synchronized`, `wait()` e `notifyAll()`  
- **Estrutura de Árvores (AST)**  
- **Orientação a Objetos (POO)**  

---

## 🗂️ Estrutura de Pastas

```
br/com/calculadora/
├── App.java
├── ExpressionParser.java
├── Node.java
└── Operacoes/
    ├── SomaThread.java
    ├── SubtracaoThread.java
    └── MultiplicacaoThread.java
```

---

## ⚙️ Como Funciona

1. **Parser**: Interpreta a string da expressão matemática e gera uma árvore de nós (`Node`), que representam números, variáveis ou operações.
2. **Threads de Operações**: Cada operação (`Soma`, `Subtração`, `Multiplicação`, `Divisão`) é executada por sua própria thread.
3. **Sincronização**: Nós aguardam (`wait()`) pelos resultados de seus filhos antes de realizar o cálculo e notificam (`notifyAll()`) seus dependentes quando terminam.
4. **Execução**: A árvore é percorrida de forma paralela respeitando a prioridade das operações matemáticas.

---

## 📥 Como Rodar o Projeto

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/Y4nnLS/calculadora_multi-thread.git
   cd calculadora_multi-thread
   ```

2. **Compile os arquivos Java:**
   ```bash
   javac br/com/calculadora/*.java br/com/calculadora/Operacoes/*.java
   ```

3. **Execute o programa:**
   ```bash
   java br.com.calculadora.App
   ```

---

## ✍️ Exemplo de Uso

### Expressão Utilizada:
```
((a + b) * (c - d) / (a + a) * (d - a)) + 4 * 2 + (e * 7)
```

### Variáveis:
```
a = 1.0  
b = 2.0  
c = 3.0  
d = 4.0  
e = 0.5
```

### Resultado:
```
7.0
```

---

## 📌 Operadores Suportados
| Operador | Descrição      |
|----------|----------------|
| `+`      | Soma           |
| `-`      | Subtração      |
| `*`      | Multiplicação  |
| `/`      | Divisão        |

---

## 🙋‍♂️ Equipe de desenvolvimento

<table align='center'>
  <tr>
    <td align="center">
        <img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/101208372?v=4" width="100px;" alt=""/><br /><sub><b><a href="https://github.com/Y4nnLS">Yann Lucas</a></b></sub></a><br />🤓☝</a></td>
    </table>