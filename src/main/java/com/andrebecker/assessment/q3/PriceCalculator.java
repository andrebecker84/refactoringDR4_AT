package com.andrebecker.assessment.q3;

/*
 * Q3 — Refatorando para legibilidade
 *
 * Problemas no código legado:
 *   - customerType como int sem semântica (Magic Number: 1, 2)
 *   - holiday como parâmetro booleano sem nome expressivo na chamada
 *   - discount calculado em sequência linear sem intenção clara
 *   - basePrice * (1 - discount) legível, mas o desconto em si era opaco
 *
 * Técnicas aplicadas:
 *   - Replace Magic Number with Constant → enum CustomerType com taxa embutida
 *   - Introduce Explaining Variable → baseDiscount, holidayDiscount, totalDiscount
 *   - Extract Method → baseDiscountFor(), holidayDiscountWhenApplicable()
 *   - Rename Parameter → basePrice mantido; customerType → CustomerType enum
 *
 * O método público tem assinatura autoexplicativa: quem lê a chamada entende
 * imediatamente que tipo de cliente e se é feriado determinam o preço final.
 */
public class PriceCalculator {

    public enum CustomerType {
        STANDARD(0.10),
        PREMIUM(0.15),
        GUEST(0.0);

        private final double discountRate;

        CustomerType(double discountRate) {
            this.discountRate = discountRate;
        }

        public double discountRate() {
            return discountRate;
        }
    }

    private static final double HOLIDAY_DISCOUNT = 0.05;

    public double calculateFinalPrice(double basePrice, CustomerType customerType, boolean isHoliday) {
        double baseDiscount    = baseDiscountFor(customerType);
        double holidayDiscount = holidayDiscountWhenApplicable(isHoliday);
        double totalDiscount   = baseDiscount + holidayDiscount;
        return basePrice * (1 - totalDiscount);
    }

    private double baseDiscountFor(CustomerType customerType) {
        return customerType.discountRate();
    }

    private double holidayDiscountWhenApplicable(boolean isHoliday) {
        return isHoliday ? HOLIDAY_DISCOUNT : 0.0;
    }
}
