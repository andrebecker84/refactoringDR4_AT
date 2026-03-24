package com.andrebecker.assessment.q5;

/*
 * Q5 — Refatorando condicional complexa com polimorfismo
 *
 * Problema original: notifyUser() usava if/else if sobre uma String "channel"
 * para despachar o comportamento correto. Adicionar "WHATSAPP" exigia modificar
 * esta classe — violação de OCP.
 *
 * Solução: Replace Conditional with Polymorphism + Extract Interface.
 *   - NotificationChannel define o contrato com send(String message)
 *   - EmailChannel, SmsChannel e PushChannel implementam o comportamento específico
 *   - NotificationService recebe um NotificationChannel via construtor (injeção)
 *     e delega sem nenhuma condicional
 *
 * OCP aplicado: a classe está fechada para modificação. Um novo canal é uma nova
 * implementação de NotificationChannel — sem tocar nesta classe.
 *
 * LSP verificado: qualquer implementação de NotificationChannel pode substituir
 * outra sem alterar o comportamento observável de notifyUser().
 *
 * SRP: NotificationService coordena o envio; cada canal sabe apenas como enviar.
 */
public final class NotificationService {

    private final NotificationChannel channel;

    public NotificationService(NotificationChannel channel) {
        if (channel == null) {
            throw new IllegalArgumentException("Canal de notificação obrigatório.");
        }
        this.channel = channel;
    }

    public void notifyUser(String message) {
        channel.send(message);
    }
}
