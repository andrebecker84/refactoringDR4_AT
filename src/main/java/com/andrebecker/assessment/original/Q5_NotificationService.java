package com.andrebecker.assessment.original;

// Código legado original da Questão 5 — preservado intacto, não modificar.
public class Q5_NotificationService {
    public void notifyUser(String channel, String message) {
        if (channel.equals("EMAIL")) {
            System.out.println("Sending EMAIL: " + message);
        } else if (channel.equals("SMS")) {
            System.out.println("Sending SMS: " + message);
        } else if (channel.equals("PUSH")) {
            System.out.println("Sending PUSH: " + message);
        }
    }
}
