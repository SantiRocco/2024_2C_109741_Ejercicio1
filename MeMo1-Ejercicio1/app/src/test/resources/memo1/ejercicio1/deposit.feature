Feature: Depositing money
 
  Scenario: Successfully deposit money into an account through CBU
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    And An account with CBU 123456789, alias "iAmAccount", balance of 1000.0, with client of DNI 11222333 as owner and created by branch 1
    When I deposit 200.0 into the account through CBU, on date "2024-01-01", at hour "09:00:00"
    Then The account balance should be 1200.0
    And A new transaction record, with id 1, should be created
    And Transaction with id 1 should have "2024-01-01" as date
    And Transaction with id 1 should have "09:00:00" as hour
    And Transaction with id 1 should have "deposit" as type of operation
    And Transaction with id 1 should have 200.0 as amount
    And Transaction with id 1 should have 123456789 as starter account CBU
    And Transaction with id 1 should have 123456789 as target account CBU

  Scenario: Successfully deposit money into an account through alias
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    And An account with CBU 123456789, alias "iAmAccount", balance of 1000.0, with client of DNI 11222333 as owner and created by branch 1
    When I deposit 200.0 into the account through alias, on date "2024-01-01", at hour "09:00:00"
    Then The account balance should be 1200.0
    And A new transaction record, with id 1, should be created
    And Transaction with id 1 should have "2024-01-01" as date
    And Transaction with id 1 should have "09:00:00" as hour
    And Transaction with id 1 should have "deposit" as type of operation
    And Transaction with id 1 should have 200.0 as amount
    And Transaction with id 1 should have 123456789 as starter account CBU
    And Transaction with id 1 should have 123456789 as target account CBU

  Scenario: Cannot deposit a negative amount
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    And An account with CBU 123456789, alias "iAmAccount", balance of 1000.0, with client of DNI 11222333 as owner and created by branch 1
    When I try to deposit -100.0 into the account
    Then The operation should be denied
    And The account balance should remain 1000.0
    And A new transaction record, with id 1, should not be created

  Scenario: Cannot deposit a zero amount
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    And An account with CBU 123456789, alias "iAmAccount", balance of 1000.0, with client of DNI 11222333 as owner and created by branch 1
    When I try to deposit 0.0 into the account
    Then The operation should be denied
    And The account balance should remain 1000.0
    And A new transaction record, with id 1, should not be created

  Scenario: Cannot deposit into a non-existent account through CBU
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    And A non-existent account CBU like 111122223
    When I try to deposit 100.0 into the non-existent account through CBU
    Then The operation should be denied
    And A new transaction record, with id 1, should not be created

  Scenario: Cannot deposit into a non-existent account through alias
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    And A non-existent account alias like "iAmNotAccount"
    When I try to deposit 100.0 into the non-existent account through alias
    Then The operation should be denied
    And A new transaction record, with id 1, should not be created