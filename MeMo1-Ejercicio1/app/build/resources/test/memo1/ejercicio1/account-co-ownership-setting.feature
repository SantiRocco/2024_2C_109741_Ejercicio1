Feature: Setting of co-ownership of account
 
  Scenario: Successfully setting one co-owner when creating a new account
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    And An account with CBU 123456789, alias "iAmAccount", with client of DNI 11222333 as owner and created by branch 1
    And A client with DNI 20100100, surname "Garcia", name "Daisy", born on "2000-10-15" and with address "Cortina 223"
    When I set the client with DNI 20100100 as co-owner
    Then The account should have 1 co-owner/s
    And The client with DNI 20100100 should be a co-owner of the account
    And The client with DNI 20100100 should have 1 related account/s

  Scenario: Successfully setting various co-owners when creating a new account
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    And An account with CBU 123456789, alias "iAmAccount", with client of DNI 11222333 as owner and created by branch 1
    And A client with DNI 20100100, surname "Garcia", name "Daisy", born on "2000-10-15" and with address "Cortina 223"
    And A client with DNI 19700900, surname "Simpson", name "Mark", born on "1999-02-13" and with address "Av. Lope de Vega 1071"
    When I set the client with DNI 20100100 as co-owner
    And I set the client with DNI 19700900 as co-owner
    Then The account should have 2 co-owner/s
    And The client with DNI 20100100 should be a co-owner of the account
    And The client with DNI 20100100 should have 1 related account/s
    And The client with DNI 19700900 should be a co-owner of the account
    And The client with DNI 19700900 should have 1 related account/s

  Scenario: Owner cannot be co-owner
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    And An account with CBU 123456789, alias "iAmAccount", with client of DNI 11222333 as owner and created by branch 1
    When I try to set the client with DNI 11222333 as co-owner
    Then The operation should be denied

  Scenario: Account may have no co-owners
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    And An account with CBU 123456789, alias "iAmAccount", with client of DNI 11222333 as owner and created by branch 1
    Then The account should have 0 co-owner/s

  Scenario: Account cannot have non-existent co-owners
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    And An account with CBU 123456789, alias "iAmAccount", with client of DNI 11222333 as owner and created by branch 1
    And A non-existent client DNI like 5000000
    When I try to set the client with DNI 5000000 as co-owner
    Then The operation should be denied