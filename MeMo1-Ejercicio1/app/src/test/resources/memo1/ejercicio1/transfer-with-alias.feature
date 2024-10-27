Feature: Transferring money through alias
 
  Scenario: Successfully transfer money from one account to another through alias
    Given A sender account with CBU 123456789, alias "iAmSender" and a balance of 1000.0
    And A receiver account with CBU 111222333, alias "iAmReceiver" and a balance of 1000.0
    When I transfer 100.0 into the other account through its alias
    Then The sender account balance should be 900.0
    And The receiver account balance should be 1100.0

  Scenario: Cannot transfer more money than balance available through alias
    Given A sender account with CBU 123456789, alias "iAmSender" and a balance of 1000.0
    And A receiver account with CBU 111222333, alias "iAmReceiver" and a balance of 1000.0
    When I transfer 1100.0 into the other account through its alias
    Then The operation should be denied due to insufficient funds
    And The sender account balance should remain 1000.0
    And The receiver account balance should remain 1000.0

  Scenario: Cannot transfer a negative amount through alias
    Given A sender account with CBU 123456789, alias "iAmSender" and a balance of 1000.0
    And A receiver account with CBU 111222333, alias "iAmReceiver" and a balance of 1000.0
    When I transfer -100.0 into the other account through its alias
    Then The operation should be denied
    And The sender account balance should remain 1000.0
    And The receiver account balance should remain 1000.0

  Scenario: Cannot transfer a zero amount through alias
    Given A sender account with CBU 123456789, alias "iAmSender" and a balance of 1000.0
    And A receiver account with CBU 111222333, alias "iAmReceiver" and a balance of 1000.0
    When I transfer 0.0 into the other account through its alias
    Then The operation should be denied
    And The sender account balance should remain 1000.0
    And The receiver account balance should remain 1000.0

  Scenario: Cannot transfer to an account with no alias
    Given A sender account with CBU 123456789, alias "iAmReceiver" and a balance of 1000.0
    And A receiver account with CBU 111222333 and no alias
    When I transfer 100.0 into the other account through its alias
    Then The operation should be denied
    And The sender account balance should remain 1000.0

  Scenario: Cannot transfer from one's account to the same one through alias
    Given A sender account with CBU 123456789, alias "iAmSender" and a balance of 1000.0
    And The same account as receiver
    When I transfer 100.0 into the same account through its alias
    Then The operation should be denied
    And The sender account balance should remain 1000.0
    And The receiver account balance should remain 1000.0
