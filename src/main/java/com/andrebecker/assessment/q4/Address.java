package com.andrebecker.assessment.q4;

/*
 * Q4 — Value Object que representa um endereço postal.
 *
 * Substitui a List raw (sem tipo genérico) por objetos com semântica real.
 * Imutável por design: endereço não muda após criação — se o usuário mudar
 * de endereço, um novo Address é criado e adicionado via User.addAddress().
 * Isso elimina a necessidade de setters e garante consistência sem validação dispersa.
 */
public final class Address {

    private final String street;
    private final String city;
    private final String zipCode;

    public Address(String street, String city, String zipCode) {
        if (street == null || street.isBlank()) {
            throw new IllegalArgumentException("Logradouro obrigatório.");
        }
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("Cidade obrigatória.");
        }
        if (zipCode == null || zipCode.isBlank()) {
            throw new IllegalArgumentException("CEP obrigatório.");
        }
        this.street  = street;
        this.city    = city;
        this.zipCode = zipCode;
    }

    public String street()  { return street; }
    public String city()    { return city; }
    public String zipCode() { return zipCode; }

    @Override
    public String toString() {
        return street + ", " + city + " — " + zipCode;
    }
}
