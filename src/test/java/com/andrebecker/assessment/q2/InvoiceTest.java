package com.andrebecker.assessment.q2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Invoice")
class InvoiceTest {

    @Test
    @DisplayName("construtor aceita dados válidos e expõe via getters")
    void validInvoiceIsCreated() {
        Invoice invoice = new Invoice("Ana Lima", "ana@example.com", 150.0, InvoiceType.SIMPLE);
        assertThat(invoice.getClientName(),  is("Ana Lima"));
        assertThat(invoice.getClientEmail(), is("ana@example.com"));
        assertThat(invoice.getAmount(),      is(150.0));
        assertThat(invoice.getType(),        is(InvoiceType.SIMPLE));
    }

    @Test
    @DisplayName("construtor rejeita nome em branco")
    void constructorRejectsBlankName() {
        assertThrows(IllegalArgumentException.class,
            () -> new Invoice("", "a@b.com", 10.0, InvoiceType.SIMPLE));
    }

    @Test
    @DisplayName("construtor rejeita e-mail sem @")
    void constructorRejectsInvalidEmail() {
        assertThrows(IllegalArgumentException.class,
            () -> new Invoice("João", "joaosememail", 10.0, InvoiceType.SIMPLE));
    }

    @Test
    @DisplayName("construtor rejeita e-mail nulo")
    void constructorRejectsNullEmail() {
        assertThrows(IllegalArgumentException.class,
            () -> new Invoice("João", null, 10.0, InvoiceType.SIMPLE));
    }

    @Test
    @DisplayName("construtor rejeita valor negativo")
    void constructorRejectsNegativeAmount() {
        assertThrows(IllegalArgumentException.class,
            () -> new Invoice("João", "j@b.com", -1.0, InvoiceType.SIMPLE));
    }

    @Test
    @DisplayName("construtor rejeita tipo nulo")
    void constructorRejectsNullType() {
        assertThrows(IllegalArgumentException.class,
            () -> new Invoice("João", "j@b.com", 10.0, null));
    }

    @Test
    @DisplayName("process imprime nome e valor do cliente no stdout")
    void processOutputsClientAndAmount() {
        Invoice invoice = new Invoice("Maria", "maria@x.com", 200.0, InvoiceType.WITH_TAX);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        invoice.process();
        System.setOut(System.out);
        String output = out.toString();
        assertThat(output, containsString("Maria"));
        assertThat(output, containsString("200.0"));
        assertThat(output, containsString("Com imposto"));
    }

    @Test
    @DisplayName("InvoiceType retorna label correto")
    void invoiceTypeLabelIsCorrect() {
        assertThat(InvoiceType.SIMPLE.label(),   is("Simples"));
        assertThat(InvoiceType.WITH_TAX.label(), is("Com imposto"));
    }
}
