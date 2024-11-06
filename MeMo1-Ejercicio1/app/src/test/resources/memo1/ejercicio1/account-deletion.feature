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