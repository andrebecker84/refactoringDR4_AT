package com.andrebecker.assessment.q5;

/*
 * Q5 — Interface que representa um canal de notificação.
 *
 * Replace Conditional with Polymorphism: o if/else if original em notifyUser()
 * testava uma string para decidir o comportamento. Cada novo canal exigia
 * modificar NotificationService — violação direta de OCP.
 *
 * Com esta interface, adicionar um novo canal (ex.: WhatsApp) significa criar
 * uma nova classe que implementa send() — sem tocar em NotificationService.
 */
public interface NotificationChannel {
    void send(String message);
}
