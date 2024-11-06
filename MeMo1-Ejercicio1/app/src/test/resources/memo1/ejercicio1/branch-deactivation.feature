Feature: Branch deactivation

  Scenario: Successfully deactivate a new branch
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    When I deactivate the branch
    Then The branch should be inactive

  Scenario: Cannot deactivate branch with associated accounts
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    And An account with CBU 123456789, alias "iAmAccount", with client of DNI 11222333 as owner and created by branch 1
    When I try to deactivate the branch
    Then The operation should be denied due to it having associated accounts