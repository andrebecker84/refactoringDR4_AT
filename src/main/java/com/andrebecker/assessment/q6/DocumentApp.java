package com.andrebecker.assessment.q6;

import java.util.List;

/*
 * Q6 — Demonstração de polimorfismo em execução.
 *
 * Saída esperada:
 *   Printing PDF
 *   Printing HTML
 *   Printing Word
 *
 * Nenhum if. O tipo correto de impressão é determinado pelo tipo real do objeto
 * em tempo de execução — despacho polimórfico puro.
 *
 * Por que esta abordagem melhora o design:
 *   - Coesão: cada classe sabe apenas como imprimir seu formato
 *   - DRY: sem duplicação da lógica de despacho por tipo
 *   - OCP: novo formato = nova classe; DocumentApp não precisa mudar
 *   - Testabilidade: cada Document pode ser testado isoladamente
 */
public class DocumentApp {

    public static void main(String[] args) {
        List<Document> documents = List.of(
            new PdfDocument(),
            new HtmlDocument(),
            new WordDocument()
        );

        documents.forEach(Document::print);
    }
}
