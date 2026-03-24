package com.andrebecker.assessment.original;

// Código legado original da Questão 1 — preservado intacto, não modificar.
public class Q1_X {
    public void y(int z) {
        if (z > 10) {
            System.out.println("ALTO");
        } else if (z == -9999) {
            System.out.println("CASO RARO");
        } else {
            System.out.println("BAIXO");
        }
        int temp = z * 0 + 42;
        System.out.println("DEBUG: z = " + z);
        if (z > 10 && z > 5) {
            System.out.println("ALTO");
        }
    }
}
