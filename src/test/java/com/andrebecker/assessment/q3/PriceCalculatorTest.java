package com.andrebecker.assessment.q3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;

@DisplayName("PriceCalculator")
class PriceCalculatorTest {

    private PriceCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new PriceCalculator();
    }

    @Test
    @DisplayName("cliente STANDARD sem feriado aplica 10% de desconto")
    void standardCustomerNoHolidayAppliesTenPercent() {
        double result = calculator.calculateFinalPrice(100.0, PriceCalculator.CustomerType.STANDARD, false);
        assertThat(result, closeTo(90.0, 0.001));
    }

    @Test
    @DisplayName("cliente PREMIUM sem feriado aplica 15% de desconto")
    void premiumCustomerNoHolidayAppliesFifteenPercent() {
        double result = calculator.calculateFinalPrice(100.0, PriceCalculator.CustomerType.PREMIUM, false);
        assertThat(result, closeTo(85.0, 0.001));
    }

    @Test
    @DisplayName("cliente GUEST sem feriado não recebe desconto")
    void guestCustomerNoHolidayPaysFullPrice() {
        double result = calculator.calculateFinalPrice(100.0, PriceCalculator.CustomerType.GUEST, false);
        assertThat(result, closeTo(100.0, 0.001));
    }

    @Test
    @DisplayName("feriado adiciona 5% ao desconto do cliente STANDARD")
    void standardCustomerOnHolidayAppliesFifteenPercent() {
        double result = calculator.calculateFinalPrice(100.0, PriceCalculator.CustomerType.STANDARD, true);
        assertThat(result, closeTo(85.0, 0.001));
    }

    @Test
    @DisplayName("feriado adiciona 5% ao desconto do cliente PREMIUM")
    void premiumCustomerOnHolidayAppliesTwentyPercent() {
        double result = calculator.calculateFinalPrice(100.0, PriceCalculator.CustomerType.PREMIUM, true);
        assertThat(result, closeTo(80.0, 0.001));
    }

    @Test
    @DisplayName("cliente GUEST em feriado aplica apenas 5% de desconto")
    void guestCustomerOnHolidayAppliesOnlyHolidayDiscount() {
        double result = calculator.calculateFinalPrice(100.0, PriceCalculator.CustomerType.GUEST, true);
        assertThat(result, closeTo(95.0, 0.001));
    }
}
