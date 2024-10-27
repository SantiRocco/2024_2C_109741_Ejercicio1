Feature: Account creation

  Scenario: Successfully create an account with default balance
    Given I create an account with CBU 123456789 and alias "iAmAccount"
    Then The account balance should be 0.0

  Scenario: Successfully create an account with an initial balance
    Given I create an account with CBU 987654321, alias "iAmAccount" and a balance of 500.0
    Then The account balance should be 500.0
