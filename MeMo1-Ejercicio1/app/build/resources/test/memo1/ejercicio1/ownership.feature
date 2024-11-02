Feature: Setting ownership of account
 
  Scenario: Successfully setting the main owner when creating a new account
    Given A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    And An account with CBU 123456789, alias "iAmAccount", a balance of 1000.0, with the client of DNI 11222333 as owner
    Then The client with DNI 11222333 should be the owner of the account

  Scenario: Account cannot have non-existent owner
    Given A non-existent client
    When I create an account with CBU 123456789, alias "iAmAccount", a balance of 1000.0, with the non-existent client as owner
    Then The operation should be denied