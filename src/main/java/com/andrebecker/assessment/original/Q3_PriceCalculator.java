package com.andrebecker.assessment.original;

// Código legado original da Questão 3 — preservado intacto, não modificar.
public class Q3_PriceCalculator {
    public double calculatePrice(double basePrice, int customerType, boolean holiday) {
        double discount = 0;
        if (customerType == 1) discount = 0.1;
        else if (customerType == 2) discount = 0.15;
        if (holiday) discount += 0.05;
        return basePrice * (1 - discount);
    }
}
