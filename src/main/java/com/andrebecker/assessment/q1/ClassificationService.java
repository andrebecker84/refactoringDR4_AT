package com.andrebecker.assessment.q1;

/*
 * Q1 — Refatoração prática de código simples
 *
 * Encapsulamento: não aplicável — esta classe não expõe estado.
 *
 * SRP: cada método tem responsabilidade única. classify() decide a categoria;
 * printClassification() é o único ponto de saída. Nenhum dos dois mistura
 * decisão de negócio com formatação ou I/O adicional.
 *
 * OCP: adicionar nova faixa (ex.: "MUITO ALTO") exige apenas um novo bloco
 * em classify() sem tocar em printClassification() nem nos testes existentes.
 *
 * Técnicas aplicadas: Rename Method, Rename Variable, Extract Method,
 * Remove Dead Code, Decompose Conditional, Introduce Explaining Variable.
 *
 * Bad smells eliminados:
 *   1. Nomes obscuros (X, y, z) → ClassificationService, printClassification, value
 *   2. Código morto → temp = z * 0 + 42, bloco DEBUG e condicional redundante removidos
 *   3. Condicional duplicada → "ALTO" era impresso duas vezes para z > 10
 *   4. Múltiplas responsabilidades → separação entre classify() e printClassification()
 */
public class ClassificationService {

    private static final int SPECIAL_CASE = -9999;
    private static final int HIGH_THRESHOLD = 10;

    public void printClassification(int value) {
        String category = classify(value);
        System.out.println(category);
    }

    String classify(int value) {
        if (value == SPECIAL_CASE) {
            return "CASO RARO";
        }
        if (value > HIGH_THRESHOLD) {
            return "ALTO";
        }
        if (value == HIGH_THRESHOLD) {
            return "MÉDIO";
        }
        return "BAIXO";
    }
}
