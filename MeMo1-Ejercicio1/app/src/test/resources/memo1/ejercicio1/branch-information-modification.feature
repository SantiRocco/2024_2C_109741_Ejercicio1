Feature: Modifying branch information
  
  Scenario: successfully modify branch name
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    When I change the name of the branch to "Suc. Palermo Soho"
    Then The new name of the branch should be "Suc. Palermo Soho"

  Scenario: successfully modify branch address
    Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
    When I change the address of the branch to "Av. Lavalle 2650"
    Then The new address of the branch should be "Av. Lavalle 2650"