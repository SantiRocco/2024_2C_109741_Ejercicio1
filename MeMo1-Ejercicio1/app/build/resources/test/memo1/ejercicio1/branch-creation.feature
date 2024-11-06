Feature: Branch creation

  Scenario: Successfully create a new branch
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    Then The name of the branch should be "Suc. Palermo"
    And The address of the branch should be "Av. Lavalle 2500"
    And The number of branch should be 1
