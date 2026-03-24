package com.andrebecker.assessment.q2;

/*
 * Q2 — Identificação de bad smells e refatoração aplicada
 *
 * Bad smells encontrados no código legado (catálogo Fowler):
 *
 * 1. Primitive Obsession — `int type` sem semântica; -1 era "caso nunca ocorre".
 *    Solução: enum InvoiceType com valores SIMPLE e WITH_TAX.
 *
 * 2. Duplicate Code — o bloco `if (type == 1/2)` aparecia duas vezes em process():
 *    uma para imprimir o cabeçalho da nota e outra para montar a string do e-mail.
 *    Solução: Extract Method → formatNote() é a única fonte do conteúdo da nota.
 *
 * 3. Dead Code — `else if (type == -1)` comentado como "nunca ocorre".
 *    Solução: removido; o enum impossibilita valores inválidos.
 *
 * 4. Bug lógico silencioso — `clientEmail == null && !clientEmail.contains("@")`
 *    causa NullPointerException porque && não faz curto-circuito quando o lado
 *    esquerdo é verdadeiro (ao contrário de ||). A condição correta é ||.
 *    Solução: validação extraída para isEmailValid() com lógica corrigida.
 *
 * 5. Long Method / God Method (SRP) — process() acumulava validação, impressão
 *    e envio. Três responsabilidades distintas em um método só.
 *    Solução: Extract Method → validateEmail(), printNote(), sendNoteByEmail().
 *
 * 6. Encapsulate Field — todos os campos eram public sem controle de acesso.
 *    Solução: campos private, construtor Fail-Fast, getters sem setters desnecessários.
 *
 * 7. Feature Envy — process() sabia demais sobre formatação de e-mail.
 *    Solução: formatNote() centraliza a montagem; sendNoteByEmail() apenas envia.
 *
 * 8. Speculative Generality — o caso `type == -1` existia sem nenhum uso real.
 *    Solução: eliminado com a substituição por enum tipado.
 *
 * Refatoração ilustrada: Primitive Obsession → enum InvoiceType (ver InvoiceType.java)
 * e Duplicate Code → Extract Method formatNote() abaixo.
 */
public class Invoice {

    private final String clientName;
    private final String clientEmail;
    private final double amount;
    private final InvoiceType type;

    public Invoice(String clientName, String clientEmail, double amount, InvoiceType type) {
        if (clientName == null || clientName.isBlank()) {
            throw new IllegalArgumentException("Nome do cliente obrigatório.");
        }
        if (!isEmailValid(clientEmail)) {
            throw new IllegalArgumentException("E-mail inválido: " + clientEmail);
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Valor da nota não pode ser negativo.");
        }
        if (type == null) {
            throw new IllegalArgumentException("Tipo da nota fiscal obrigatório.");
        }
        this.clientName = clientName;
        this.clientEmail = clientEmail;
        this.amount = amount;
        this.type = type;
    }

    public void process() {
        printNote();
        sendNoteByEmail();
    }

    private void printNote() {
        System.out.println(formatNote());
    }

    private void sendNoteByEmail() {
        System.out.println("Enviando nota fiscal para: " + clientEmail);
        System.out.println("Enviando email para: " + clientEmail);
        System.out.println("Conteúdo:\n" + formatNote());
    }

    private String formatNote() {
        return "--- NOTA FISCAL ---\n" +
               "Cliente: " + clientName + "\n" +
               "Valor: R$ " + amount + "\n" +
               "Tipo: " + type.label() + "\n" +
               "---------------------";
    }

    private static boolean isEmailValid(String email) {
        return email != null && email.contains("@");
    }

    public String getClientName()  { return clientName; }
    public String getClientEmail() { return clientEmail; }
    public double getAmount()      { return amount; }
    public InvoiceType getType()   { return type; }
}
