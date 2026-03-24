package com.andrebecker.assessment.q1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

@DisplayName("ClassificationService")
class ClassificationServiceTest {

    private ClassificationService service;

    @BeforeEach
    void setUp() {
        service = new ClassificationService();
    }

    @Test
    @DisplayName("valor acima de 10 é classificado como ALTO")
    void valueAboveThresholdIsHigh() {
        assertThat(service.classify(11),  is("ALTO"));
        assertThat(service.classify(100), is("ALTO"));
    }

    @Test
    @DisplayName("valor exatamente 10 é classificado como MÉDIO")
    void valueAtThresholdIsMedium() {
        assertThat(service.classify(10), is("MÉDIO"));
    }

    @Test
    @DisplayName("valor abaixo de 10 é classificado como BAIXO")
    void valueBelowThresholdIsLow() {
        assertThat(service.classify(9),  is("BAIXO"));
        assertThat(service.classify(0),  is("BAIXO"));
        assertThat(service.classify(-1), is("BAIXO"));
    }

    @Test
    @DisplayName("valor especial -9999 retorna CASO RARO antes de qualquer outra verificação")
    void specialValueReturnsRareCase() {
        assertThat(service.classify(-9999), is("CASO RARO"));
    }

    @Test
    @DisplayName("printClassification imprime a categoria no stdout")
    void printClassificationWritesToStdout() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        service.printClassification(11);
        System.setOut(System.out);
        assertThat(out.toString().trim(), containsString("ALTO"));
    }
}
