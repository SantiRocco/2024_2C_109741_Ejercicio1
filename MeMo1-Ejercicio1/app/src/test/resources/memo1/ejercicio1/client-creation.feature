Feature: Client creation

  Scenario: Successfully create a client with all the right data
    Given A new client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    Then The client with DNI 11222333 surname should be "Gregory"
    And The client with DNI 11222333 name should be "John"
    And The client with DNI 11222333 birth date should be "1997-11-29"
    And The client with DNI 11222333 address should be "Av. Triunvitaro 557"