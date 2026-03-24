package com.andrebecker.assessment.q4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("User e Address")
class UserTest {

    // ------------------------------------------------------------------ User

    @Test
    @DisplayName("usuário válido expõe nome e e-mail via getters")
    void validUserExposesFields() {
        User user = new User("Carlos", "carlos@x.com");
        assertThat(user.getName(),  is("Carlos"));
        assertThat(user.getEmail(), is("carlos@x.com"));
    }

    @Test
    @DisplayName("construtor rejeita nome nulo")
    void constructorRejectsNullName() {
        assertThrows(IllegalArgumentException.class, () -> new User(null, "a@b.com"));
    }

    @Test
    @DisplayName("construtor rejeita nome em branco")
    void constructorRejectsBlankName() {
        assertThrows(IllegalArgumentException.class, () -> new User("", "a@b.com"));
    }

    @Test
    @DisplayName("construtor rejeita e-mail nulo")
    void constructorRejectsNullEmail() {
        assertThrows(IllegalArgumentException.class, () -> new User("Carlos", null));
    }

    @Test
    @DisplayName("construtor rejeita e-mail sem @")
    void constructorRejectsInvalidEmail() {
        assertThrows(IllegalArgumentException.class, () -> new User("Carlos", "invalido"));
    }

    @Test
    @DisplayName("addAddress acumula endereços e getAddresses reflete a lista")
    void addAddressAccumulatesEntries() {
        User user = new User("Carlos", "c@x.com");
        user.addAddress(new Address("Rua A", "SP", "01000-000"));
        user.addAddress(new Address("Rua B", "RJ", "20000-000"));
        assertThat(user.getAddresses(), hasSize(2));
    }

    @Test
    @DisplayName("addAddress rejeita endereço nulo")
    void addAddressRejectsNull() {
        User user = new User("Carlos", "c@x.com");
        assertThrows(IllegalArgumentException.class, () -> user.addAddress(null));
    }

    @Test
    @DisplayName("getAddresses retorna lista imutável")
    void getAddressesReturnsUnmodifiableList() {
        User user = new User("Carlos", "c@x.com");
        user.addAddress(new Address("Rua A", "SP", "01000-000"));
        List<Address> addresses = user.getAddresses();
        assertThrows(UnsupportedOperationException.class,
            () -> addresses.add(new Address("Rua C", "MG", "30000-000")));
    }

    // ------------------------------------------------------------------ Address

    @Test
    @DisplayName("Address válido expõe campos via acessores")
    void validAddressExposesFields() {
        Address address = new Address("Av. Paulista", "São Paulo", "01310-100");
        assertThat(address.street(),  is("Av. Paulista"));
        assertThat(address.city(),    is("São Paulo"));
        assertThat(address.zipCode(), is("01310-100"));
    }

    @Test
    @DisplayName("Address rejeita logradouro nulo")
    void addressRejectsNullStreet() {
        assertThrows(IllegalArgumentException.class, () -> new Address(null, "SP", "00000-000"));
    }

    @Test
    @DisplayName("Address rejeita logradouro em branco")
    void addressRejectsBlankStreet() {
        assertThrows(IllegalArgumentException.class, () -> new Address("", "SP", "00000-000"));
    }

    @Test
    @DisplayName("Address rejeita cidade nula")
    void addressRejectsNullCity() {
        assertThrows(IllegalArgumentException.class, () -> new Address("Rua A", null, "00000-000"));
    }

    @Test
    @DisplayName("Address rejeita cidade em branco")
    void addressRejectsBlankCity() {
        assertThrows(IllegalArgumentException.class, () -> new Address("Rua A", "", "00000-000"));
    }

    @Test
    @DisplayName("Address rejeita CEP nulo")
    void addressRejectsNullZipCode() {
        assertThrows(IllegalArgumentException.class, () -> new Address("Rua A", "SP", null));
    }

    @Test
    @DisplayName("Address rejeita CEP em branco")
    void addressRejectsBlankZipCode() {
        assertThrows(IllegalArgumentException.class, () -> new Address("Rua A", "SP", ""));
    }
}
