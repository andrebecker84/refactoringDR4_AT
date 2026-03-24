package com.andrebecker.assessment.q2;

/*
 * Q2 — Substituição de Primitive Obsession: o campo `int type` não carregava
 * semântica alguma. Qualquer inteiro era aceito, incluindo -1 ("caso nunca ocorre").
 * Enum tipado elimina o problema na raiz — o compilador rejeita tipos inexistentes.
 */
public enum InvoiceType {

    SIMPLE("Simples"),
    WITH_TAX("Com imposto");

    private final String label;

    InvoiceType(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }
}
