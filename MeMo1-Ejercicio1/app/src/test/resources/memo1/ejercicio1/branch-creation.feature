Feature: Branch creation

  Scenario: Successfully create a new branch
    Given A new branch with name "Suc. Palermo" address "Av. Lavalle 2500"
    Then The name should be "Suc. Palermo"
    And The address should be "Av. Lavalle 2500"
    And The branch should be active