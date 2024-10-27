Feature: Branch deactivation

  Scenario: Successfully deactivate a new branch
    Given A new branch with name "Suc. Palermo" address "Av. Lavalle 2500"
    When The branch is deactivated
    Then The branch should be inactive

  Scenario: inactive branch cannot allow deposits
    Given An inactive branch with name "Suc. Palermo" address "Av. Lavalle 2500"
    And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    And An account with CBU 123456789, alias "iAmAccount", a balance of 1000.0, with the client of DNI 11222333 as owner
    When I try to deposit 100.0 into the account, within the inactive branch
    Then The operation should be denied

  Scenario: inactive branch cannot allow withdrawals
    Given An inactive branch with name "Suc. Palermo" address "Av. Lavalle 2500"
    And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    And An account with CBU 123456789, alias "iAmAccount", a balance of 1000.0, with the client of DNI 11222333 as owner
    When I try to withdraw 100.0 from the account, within the inactive branch
    Then The operation should be denied

  Scenario: inactive branch cannot allow transfers
    Given An inactive branch with name "Suc. Palermo" address "Av. Lavalle 2500"
    And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    And A sender account with CBU 123456789, alias "iAmAccount1", a balance of 1000.0, with the client of DNI 11222333 as owner
    And A client with DNI 20100100, surname "Garcia", name "Daisy", born on "2000-10-15" and with address "Cortina 223"
    And A receiver account with CBU 111222333, alias "iAmAccount2", a balance of 1000.0, with the client of DNI 20100100 as owner
    When I try to transfer 100.0 into the receiver account, within the inactive branch
    Then The operation should be denied