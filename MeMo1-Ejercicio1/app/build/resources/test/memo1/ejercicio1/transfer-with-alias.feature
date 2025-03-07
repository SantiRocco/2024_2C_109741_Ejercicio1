Feature: Transferring money through alias
 
  Scenario: Successfully transfer money from one account to another through alias
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    And A sender account with CBU 123456789, alias "iAmSender", balance of 1000.0, with client of DNI 11222333 as owner and created by branch 1
    And A receiver account with CBU 111222333, alias "iAmReceiver", balance of 1000.0, with client of DNI 11222333 as owner and created by branch 1
    When I transfer 100.0 to the other account through alias, with DNI 11222333, on date "2024-01-01", at hour "09:00:00"
    Then The sender account balance should be 900.0
    And The receiver account balance should be 1100.0
    And A new transaction record, with id 1, should be created
    And Transaction with id 1 should have "2024-01-01" as date
    And Transaction with id 1 should have "09:00:00" as hour
    And Transaction with id 1 should have "transfer" as type of operation
    And Transaction with id 1 should have 100.0 as amount
    And Transaction with id 1 should have 123456789 as starter account CBU
    And Transaction with id 1 should have 111222333 as target account CBU

  Scenario: Cannot transfer more money than balance available through alias
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    And A sender account with CBU 123456789, alias "iAmSender", balance of 1000.0, with client of DNI 11222333 as owner and created by branch 1
    And A receiver account with CBU 111222333, alias "iAmReceiver", balance of 1000.0, with client of DNI 11222333 as owner and created by branch 1
    When I try to transfer 1100.0 to the other account through alias, with DNI 11222333
    Then The operation should be denied due to insufficient funds
    And The sender account balance should remain 1000.0
    And The receiver account balance should remain 1000.0
    And A new transaction record, with id 1, should not be created

  Scenario: Cannot transfer a negative amount through alias
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    And A sender account with CBU 123456789, alias "iAmSender", balance of 1000.0, with client of DNI 11222333 as owner and created by branch 1
    And A receiver account with CBU 111222333, alias "iAmReceiver", balance of 1000.0, with client of DNI 11222333 as owner and created by branch 1
    When I try to transfer -100.0 to the other account through alias, with DNI 11222333
    Then The operation should be denied
    And The sender account balance should remain 1000.0
    And The receiver account balance should remain 1000.0
    And A new transaction record, with id 1, should not be created

  Scenario: Cannot transfer a zero amount through alias
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    And A sender account with CBU 123456789, alias "iAmSender", balance of 1000.0, with client of DNI 11222333 as owner and created by branch 1
    And A receiver account with CBU 111222333, alias "iAmReceiver", balance of 1000.0, with client of DNI 11222333 as owner and created by branch 1
    When I try to transfer 0.0 to the other account through alias, with DNI 11222333
    Then The operation should be denied
    And The sender account balance should remain 1000.0
    And The receiver account balance should remain 1000.0
    And A new transaction record, with id 1, should not be created

  Scenario: Cannot transfer from one's account to the same one through alias
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    And A sender account with CBU 123456789, alias "iAmSender", balance of 1000.0, with client of DNI 11222333 as owner and created by branch 1
    And The same account as receiver
    When I try to transfer 100.0 to the same account through alias, with DNI 11222333
    Then The operation should be denied
    And The sender account balance should remain 1000.0
    And The receiver account balance should remain 1000.0
    And A new transaction record, with id 1, should not be created

  Scenario: Cannot transfer with not related DNI through alias
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    And A sender account with CBU 123456789, alias "iAmSender", balance of 1000.0, with client of DNI 11222333 as owner and created by branch 1
    And A receiver account with CBU 111222333, alias "iAmReceiver", balance of 1000.0, with client of DNI 11222333 as owner and created by branch 1
    When I try to transfer 100.0 to the same account through alias, with DNI 10999222
    Then The operation should be denied
    And The sender account balance should remain 1000.0
    And The receiver account balance should remain 1000.0
    And A new transaction record, with id 1, should not be created

  Scenario: Cannot transfer from non-existent account through alias
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    And A sender account with CBU 123456789, alias "iAmSender", balance of 1000.0, with client of DNI 11222333 as owner and created by branch 1
    And A non-existent account CBU like 111122223
    When I try to transfer 100.0 to the same account through alias, with DNI 11222333
    Then The operation should be denied
    And The sender account balance should remain 1000.0
    And A new transaction record, with id 1, should not be created

  Scenario: Cannot transfer to non-existent account through alias
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    And A non-existent account CBU like 111122223
    And A receiver account with CBU 111222333, alias "iAmReceiver", balance of 1000.0, with client of DNI 11222333 as owner and created by branch 1
    When I try to transfer 100.0 to the same account through alias, with DNI 11222333
    Then The operation should be denied
    And The receiver account balance should remain 1000.0
    And A new transaction record, with id 1, should not be created