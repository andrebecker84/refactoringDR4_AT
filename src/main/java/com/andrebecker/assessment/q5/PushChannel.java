package com.andrebecker.assessment.q5;

public final class PushChannel implements NotificationChannel {

    @Override
    public void send(String message) {
        System.out.println("Sending PUSH: " + message);
    }
}
