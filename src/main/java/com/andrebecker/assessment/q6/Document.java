package com.andrebecker.assessment.q6;

/*
 * Q6 — Substituindo tipos por hierarquias e melhorando coesão
 *
 * Problemas causados pela abordagem original com type code (String type):
 *
 *   1. Ausência de segurança de tipos — qualquer String era aceita; "PDF" e "pdf"
 *      produziam comportamentos distintos sem aviso do compilador.
 *
 *   2. Lógica condicional espalhável — cada novo formato exigia localizar e modificar
 *      todos os if/else if que testavam `type`, potencialmente em múltiplos arquivos.
 *
 *   3. Baixa coesão — Document carregava tanto o dado (type) quanto o comportamento
 *      de todos os formatos possíveis em um único método. Adicionar "WORD" adicionava
 *      código a uma classe que não deveria conhecer o formato Word.
 *
 *   4. OCP violado — a classe precisava ser modificada a cada novo formato.
 *
 * Solução: Replace Type Code with Subclasses + polimorfismo.
 *   - Document vira classe abstrata com print() abstrato
 *   - Cada formato implementa seu próprio comportamento sem condicional
 *   - Adicionar MarkdownDocument = nova classe, zero modificação nas existentes
 *
 * Coesão melhorada: cada subclasse sabe exatamente como imprimir seu formato
 * e nada mais. Document define apenas o contrato.
 *
 * LSP garantido: PdfDocument, HtmlDocument e WordDocument substituem Document
 * em qualquer ponto sem alterar o comportamento esperado pelo chamador.
 */
public abstract class Document {

    public abstract void print();
}
