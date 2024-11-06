Feature: Account creation

  Scenario: Successfully create an account with default balance
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    And An account with CBU 123456789, alias "iAmAccount", with client of DNI 11222333 as owner and created by branch 1
    Then The account balance should be 0.0

  Scenario: Successfully create an account with an initial balance
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    And An account with CBU 123456789, alias "iAmAccount", balance of 500.0, with client of DNI 11222333 as owner and created by branch 1
    Then The account balance should be 500.0

  Scenario: Cannot create an account with negative CBU
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    When I try to create an account with CBU -1, alias "iAmAccount", with client of DNI 11222333 as owner and created by branch 1
    Then The operation should be denied
  
  Scenario: Cannot create an account with zero CBU
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    When I try to create an account with CBU 0, alias "iAmAccount", with client of DNI 11222333 as owner and created by branch 1
    Then The operation should be denied

  Scenario: Cannot create an account with already existent CBU
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    And An account with CBU 123456789, alias "iAmAccount", with client of DNI 11222333 as owner and created by branch 1
    When I try to create an account with CBU 123456789, alias "iAmOtherAccount", with client of DNI 11222333 as owner and created by branch 1
    Then The operation should be denied

  Scenario: Cannot create an account with already existent alias
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    And An account with CBU 123456789, alias "iAmAccount", with client of DNI 11222333 as owner and created by branch 1
    When I try to create an account with CBU 200000000, alias "iAmAccount", with client of DNI 11222333 as owner and created by branch 1
    Then The operation should be denied

  Scenario: Cannot create an account with negative balance
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    When I try to create an account with CBU 123456789, alias "iAmAccount", balance of -1000.0, with client of DNI 11222333 as owner and created by branch 1
    Then The operation should be denied

  Scenario: Cannot create an account with negative branch number
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    When I try to create an account with CBU 123456789, alias "iAmAccount", with client of DNI 11222333 as owner and created by branch -1
    Then The operation should be denied

  Scenario: Cannot create an account with branch number as zero
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    When I try to create an account with CBU 123456789, alias "iAmAccount", with client of DNI 11222333 as owner and created by branch 0
    Then The operation should be denied

  Scenario: Cannot create an account with non-existent client
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    And A non-existent client DNI like 5000000
    When I try to create an account with CBU 123456789, alias "iAmAccount", with client of DNI 5000000 as owner and created by branch 1
    Then The operation should be denied
  
  Scenario: Cannot create an account with non-existent branch
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    When I try to create an account with CBU 123456789, alias "iAmAccount", with client of DNI 11222333 as owner and created by branch 9999
    Then The operation should be denied