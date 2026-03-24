package com.andrebecker.assessment.q5;

public final class EmailChannel implements NotificationChannel {

    @Override
    public void send(String message) {
        System.out.println("Sending EMAIL: " + message);
    }
}
