Feature: Setting co-ownership of account
 
  Scenario: Successfully setting one co-owner when creating a new account
    Given A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    And An account with CBU 123456789, alias "iAmAccount", a balance of 1000.0, with the client with DNI 11222333 as owner
    And A client with DNI 20100100, surname "Garcia", name "Daisy", born on "2000-10-15" and with address "Cortina 223"
    When I set the client with DNI 20100100 as co-owner
    Then There should be 1 co-owner/s
    And the client with DNI 20100100 should be a co-owner of the account


  Scenario: Successfully setting various co-owners when creating a new account
    Given A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    And An account with CBU 123456789, alias "iAmAccount", a balance of 1000.0, with the client with DNI 11222333 as owner
    And A client with DNI 20100100, surname "Garcia", name "Daisy", born on "2000-10-15" and with address "Cortina 223"
    And A client with DNI 19700900, surname "Simpson", name "Mark", born on "1999-2-13" and with address "Av. Lope de Vega 1071"
    When I set the client of DNI 20100100 as co-owner
    And I set the client of DNI 19700900 as co-owner
    Then There should be 2 co-owner/s
    And The client with DNI 20100100 should be a co-owner of the account
    And The client with DNI 19700900 should be a co-owner of the account

  Scenario: owner cannot be co-owner
   Given A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    And An account with CBU 123456789, alias "iAmAccount", a balance of 1000.0, with the client of DNI 11222333 as owner
    When I set the client of DNI 11222333 as co-owner
    Then The operation should be denied