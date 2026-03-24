package com.andrebecker.assessment.q5;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("NotificationService e canais")
class NotificationServiceTest {

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
    @DisplayName("EmailChannel envia via e-mail")
    void emailChannelSendsMessage() {
        new NotificationService(new EmailChannel()).notifyUser("olá");
        assertThat(out.toString(), containsString("Sending EMAIL: olá"));
    }

    @Test
    @DisplayName("SmsChannel envia via SMS")
    void smsChannelSendsMessage() {
        new NotificationService(new SmsChannel()).notifyUser("olá");
        assertThat(out.toString(), containsString("Sending SMS: olá"));
    }

    @Test
    @DisplayName("PushChannel envia via push")
    void pushChannelSendsMessage() {
        new NotificationService(new PushChannel()).notifyUser("olá");
        assertThat(out.toString(), containsString("Sending PUSH: olá"));
    }

    @Test
    @DisplayName("construtor rejeita canal nulo")
    void constructorRejectsNullChannel() {
        assertThrows(IllegalArgumentException.class, () -> new NotificationService(null));
    }
}
