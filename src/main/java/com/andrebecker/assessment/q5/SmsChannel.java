package com.andrebecker.assessment.q5;

public final class SmsChannel implements NotificationChannel {

    @Override
    public void send(String message) {
        System.out.println("Sending SMS: " + message);
    }
}
