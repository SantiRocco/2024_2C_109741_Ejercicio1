Feature: Transferring money
 
  Scenario: Successfully transfer money from one account to another
    Given A sender account with CBU 123456789 and a balance of 1000.0
    And A receiver account with CBU 111222333 and a balance of 1000.0
    When I transfer 100.0 into the other account
    Then The sender account balance should be 900.0
    And The receiver account balance should be 1100.0

  Scenario: Cannot transfer more money than balance available
    Given A sender account with CBU 123456789 and a balance of 1000.0
    And A receiver account with CBU 111222333 and a balance of 1000.0
    When I transfer 1100.0 into the other account
    Then The operation should be denied due to insufficient funds
    And The sender account balance should remain 1000.0
    And The receiver account balance should remain 1000.0

  Scenario: Cannot transfer a negative amount
    Given A sender account with CBU 123456789 and a balance of 1000.0
    And A receiver account with CBU 111222333 and a balance of 1000.0
    When I transfer -100.0 into the other account
    Then The operation should be denied
    And The sender account balance should remain 1000.0
    And The receiver account balance should remain 1000.0

  Scenario: Cannot transfer a zero amount
    Given A sender account with CBU 123456789 and a balance of 1000.0
    And A receiver account with CBU 111222333 and a balance of 1000.0
    When I transfer 0.0 into the other account
    Then The operation should be denied
    And The sender account balance should remain 1000.0
    And The receiver account balance should remain 1000.0

  Scenario: Cannot transfer to an account with no cbu
    Given A sender account with CBU 123456789 and a balance of 1000.0
    And A receiver account with no CBU
    When I transfer 100.0 into the other account
    Then The operation should be denied
    And The sender account balance should remain 1000.0

  Scenario: Cannot transfer from one's account to the same one
    Given A sender account with CBU 123456789 and a balance of 1000.0
    And The same account as receiver
    When I transfer 100.0 into the same account
    Then The operation should be denied
    And The sender account balance should remain 1000.0
    And The receiver account balance should remain 1000.0
