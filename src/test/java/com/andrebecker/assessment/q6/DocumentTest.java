package com.andrebecker.assessment.q6;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

@DisplayName("Hierarquia de Document")
class DocumentTest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream original = System.out;

    @BeforeEach
    void captureOutput() {
        System.setOut(new PrintStream(out));
    }

    @AfterEach
    void restoreOutput() {
        System.setOut(original);
    }

    @Test
    @DisplayName("PdfDocument imprime identificação do formato PDF")
    void pdfDocumentPrintsCorrectLabel() {
        new PdfDocument().print();
        assertThat(out.toString(), containsString("Printing PDF"));
    }

    @Test
    @DisplayName("HtmlDocument imprime identificação do formato HTML")
    void htmlDocumentPrintsCorrectLabel() {
        new HtmlDocument().print();
        assertThat(out.toString(), containsString("Printing HTML"));
    }

    @Test
    @DisplayName("WordDocument imprime identificação do formato Word")
    void wordDocumentPrintsCorrectLabel() {
        new WordDocument().print();
        assertThat(out.toString(), containsString("Printing Word"));
    }

    @Test
    @DisplayName("polimorfismo: três subclasses distintas via referência Document")
    void polymorphicDispatchProducesCorrectOutput() {
        new PdfDocument().print();
        new HtmlDocument().print();
        new WordDocument().print();
        String output = out.toString();
        assertThat(output, containsString("Printing PDF"));
        assertThat(output, containsString("Printing HTML"));
        assertThat(output, containsString("Printing Word"));
    }

    @Test
    @DisplayName("DocumentApp.main produz os três formatos em sequência")
    void documentAppMainProducesAllFormats() {
        DocumentApp.main(new String[]{});
        String output = out.toString();
        assertThat(output, containsString("Printing PDF"));
        assertThat(output, containsString("Printing HTML"));
        assertThat(output, containsString("Printing Word"));
    }
}
