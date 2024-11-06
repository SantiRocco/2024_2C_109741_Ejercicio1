Feature: account deletion  
  
  Scenario: Successfully delete an account
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    And An account with CBU 123456789, alias "iAmAccount", with client of DNI 11222333 as owner and created by branch 1
    When I delete the account
    Then The account should not be found anymore
    Then The client with DNI 11222333 should have 0 related account/s
  
  Scenario: Cannot delete an account with positive balance
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    And An account with CBU 123456789, alias "iAmAccount", balance of 100.0, with client of DNI 11222333 as owner and created by branch 1
    When I try to delete the account
    Then The operation should be denied
    Then The client with DNI 11222333 should have 1 related account/s

  Scenario: Owner has one less related account when account is deleted
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    And An account with CBU 123456789, alias "iAmAccount", with client of DNI 11222333 as owner and created by branch 1
    When I delete the account
    Then The client with DNI 11222333 should have 0 related account/s

  Scenario: Co-owners have one less related account when account is deleted
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    And An account with CBU 123456789, alias "iAmAccount", with client of DNI 11222333 as owner and created by branch 1
    And A client with DNI 20100100, surname "Garcia", name "Daisy", born on "2000-10-15" and with address "Cortina 223" as a co-owner of the account with CBU 123456789, created by branch 1
    And A client with DNI 19700900, surname "Simpson", name "Mark", born on "1999-02-13" and with address "Av. Lope de Vega 1071" as a co-owner of the account with CBU 123456789, created by branch 1
    When I delete the account
    Then The client with DNI 20100100 should have 0 related account/s
    And The client with DNI 19700900 should have 0 related account/s