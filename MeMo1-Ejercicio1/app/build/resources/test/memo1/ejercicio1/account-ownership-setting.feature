Feature: Setting of ownership of account
 
  Scenario: Successfully setting the main owner when creating a new account
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    And An account with CBU 123456789, alias "iAmAccount", with client of DNI 11222333 as owner and created by branch 1
    Then The client with DNI 11222333 should be the owner of the account
    And The client with DNI 11222333 should have 1 related account/s

  Scenario: Account cannot have non-existent owner
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    And A non-existent client DNI like 5000000
    When I try to create an account with CBU 123456789, alias "iAmAccount", with client of DNI 5000000 as owner and created by branch 1
    Then The operation should be denied due to the client not existing