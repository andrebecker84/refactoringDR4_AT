package com.andrebecker.assessment.q4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Q4 — Refatorando para modularidade e encapsulamento
 *
 * Problemas no código legado:
 *   1. Campos public sem controle de acesso — qualquer código externo altera o estado.
 *   2. List raw (sem <T>) — sem garantia de tipo em tempo de compilação.
 *   3. Acesso direto à coleção — sem ponto único de controle para adicionar endereços.
 *
 * Técnicas aplicadas:
 *   - Encapsulate Field → name, email, addresses são private final onde possível
 *   - Replace Data Value with Object → List<Address> substitui List raw
 *   - Hide Collection → addAddress() é o único ponto de inserção;
 *     getAddresses() retorna cópia imutável (cópia defensiva)
 *
 * O encapsulamento implementado protege a integridade dos dados porque:
 *   - name e email são validados no construtor e não podem ser alterados após criação
 *   - a lista interna nunca vaza para o chamador — Collections.unmodifiableList()
 *     impede modificações externas sem passar por addAddress()
 *
 * SRP: User gerencia identidade (name, email) e seus endereços associados.
 * Não formata, não persiste, não envia e-mail. Uma única razão para mudar.
 */
public final class User {

    private final String name;
    private final String email;
    private final List<Address> addresses = new ArrayList<>();

    public User(String name, String email) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nome do usuário obrigatório.");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("E-mail inválido: " + email);
        }
        this.name  = name;
        this.email = email;
    }

    public void addAddress(Address address) {
        if (address == null) {
            throw new IllegalArgumentException("Endereço não pode ser nulo.");
        }
        addresses.add(address);
    }

    public List<Address> getAddresses() {
        return Collections.unmodifiableList(addresses);
    }

    public String getName()  { return name; }
    public String getEmail() { return email; }
}
