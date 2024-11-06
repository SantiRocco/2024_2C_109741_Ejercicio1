Feature: Withdrawing money
  
  Scenario: Successfully withdraw money through CBU when the balance is sufficient
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    And An account with CBU 123456789, alias "iAmAccount", balance of 1000, with client of DNI 11222333 as owner and created by branch 1
    When I withdraw 300.0 from the account through CBU, with DNI 11222333
    Then The account balance should be 700.0

  Scenario: Successfully withdraw money through alias when the balance is sufficient
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    And An account with CBU 123456789, alias "iAmAccount", balance of 1000.0, with client of DNI 11222333 as owner and created by branch 1
    When I withdraw 300.0 from the account through alias, with DNI 11222333
    Then The account balance should be 700.0

  Scenario: Successfully create a transaction record when carrying out a withdrawal
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    And An account with CBU 123456789, alias "iAmAccount", balance of 1000.0, with client of DNI 11222333 as owner and created by branch 1
    When I withdraw 200.0 from the account, with DNI 11222333, on date "2024-01-01", at hour "09:00:00"
    Then A new transaction record, with id 1, should be created
    And Transaction with id 1 should have "2024-01-01" as date
    And Transaction with id 1 should have "09:00:00" as hour
    And Transaction with id 1 should have "withdrawal" as type of operation
    And Transaction with id 1 should have 200.0 as amount
    And Transaction with id 1 should have 123456789 as starter account CBU
    And Transaction with id 1 should have 123456789 as target account CBU


  Scenario: Cannot withdraw more money than available balance
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    And An account with CBU 123456789, alias "iAmAccount", balance of 1000.0, with client of DNI 11222333 as owner and created by branch 1
    When I try to withdraw 1100.0 from the account through CBU, with DNI 11222333
    Then The operation should be denied due to insufficient funds
    And The account balance should remain 1000.0

  Scenario: Cannot withdraw a negative amount
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    And An account with CBU 123456789, alias "iAmAccount", balance of 1000.0, with client of DNI 11222333 as owner and created by branch 1
    When I try to withdraw -10.0 from the account through CBU, with DNI 11222333
    Then The operation should be denied due to insufficient funds
    And The account balance should remain 1000.0

  Scenario: Cannot withdraw a zero amount
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    And An account with CBU 123456789, alias "iAmAccount", balance of 1000.0, with client of DNI 11222333 as owner and created by branch 1
    When I try to withdraw 0.0 from the account through CBU, with DNI 11222333
    Then The operation should be denied due to insufficient funds
    And The account balance should remain 1000.0

  Scenario: Cannot withdraw from a non-existent account thorugh CBU
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    And A non-existent account CBU like 111122223
    When I try to withdraw 100.0 from the non-existent account through CBU, with DNI 11222333
    Then The operation should be denied

  Scenario: Cannot withdraw from a non-existent account thorugh alias
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    And A non-existent account alias like "iAmNotAccount"
    When I try to withdraw 100.0 from the non-existent account through alias, with DNI 11222333
    Then The operation should be denied

  Scenario: Transaction record is not created if withdrawal fails
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    And An account with CBU 123456789, alias "iAmAccount", balance of 1000.0, with client of DNI 11222333 as owner and created by branch 1
    When I try to withdraw 200.0 from the account, with DNI 5000000
    Then The operation should be denied
