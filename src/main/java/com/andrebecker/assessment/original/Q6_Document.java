package com.andrebecker.assessment.original;

// Código legado original da Questão 6 — preservado intacto, não modificar.
public class Q6_Document {
    public String type;

    public void print() {
        if (type.equals("PDF")) {
            System.out.println("Printing PDF");
        } else if (type.equals("HTML")) {
            System.out.println("Printing HTML");
        } else {
            System.out.println("Unknown format");
        }
    }
}
