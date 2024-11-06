Feature: Client creation

  Scenario: Successfully create a client with all the right data
    Given A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    Then The surname of the client should be "Gregory"
    And The name of the client should be "John"
    And The birth date of the client should be "1997-11-29"
    And The address of the client should be "Av. Triunvitaro 557"

  Scenario: Cannot create a client who is already registered in the system
    Given A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    When I try to create a new client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
    Then The operation should be denied due to the client already being registered in the system