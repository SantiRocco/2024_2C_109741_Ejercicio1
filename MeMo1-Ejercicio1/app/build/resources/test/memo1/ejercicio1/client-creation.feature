Feature: Client creation

  Scenario: Successfully create a client with all the right data
    Given I create a client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    Then The surname of client with DNI 11222333 should be "Gregory"
    And The name client of with DNI 11222333 should be "John"
    And The birth date of client with DNI 11222333 should be "1997-11-29"
    And The addres of client with DNI 11222333 should be "Av. Triunvitaro 557"