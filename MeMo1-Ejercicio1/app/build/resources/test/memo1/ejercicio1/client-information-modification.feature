Feature: Modifying client information
 
    Scenario: Successfully modify client name
        Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
        And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
        When I change the name of the client to "Humberto"
        Then The name of the client should be "Humberto"
    
    Scenario: Successfully modify client surname
        Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
        And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
        When I change the surname of the client to "Donogan"
        Then The surname of the client should be "Donogan"

    Scenario: Successfully modify client address
        Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
        And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
        When I change the address of the client to "Av. Lavalle 2650"
        Then The address of the client should be "Av. Lavalle 2650"

    Scenario: Successfully register marriage between two clients and both of them are married
        Given A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
        And A client with DNI 20100100, surname "Garcia", name "Daisy", born on "2000-10-15" and with address "Cortina 223"
        When I register a new marriage on date "2024-01-01" between DNI 11222333 and DNI 20100100
        Then The date of the marriage should be "2024-01-01"
        And The client with DNI 11222333 should be married to the client with DNI 20100100
        And The client with DNI 20100100 should be married to the client with DNI 11222333

    Scenario: Two clients are not married
        Given A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
        And A client with DNI 20100100, surname "Garcia", name "Daisy", born on "2000-10-15" and with address "Cortina 223"
        Then The client with DNI 11222333 should not be married to the client with DNI 20100100
        Then The client with DNI 20100100 should not be married to the client with DNI 11222333

    Scenario: Marriage record is deleted when one of the clients involved is deleted
        Given A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
        And A client with DNI 20100100, surname "Garcia", name "Daisy", born on "2000-10-15" and with address "Cortina 223"
        And A new marriage on date "2024-01-01" between DNI 11222333 and DNI 20100100
        When I delete the marriage of DNI 11222333
        Then The client with DNI 11222333 should not be married
        And The client with DNI 20100100 should not be married

    Scenario: Cannot register marriage with one non-existent client
        Given A non-existent client DNI like 5000000
        And A client with DNI 20100100, surname "Garcia", name "Daisy", born on "2000-10-15" and with address "Cortina 223"
        When I try to register a new marriage on date "2024-01-01" between DNI 5000000 and DNI 20100100
        Then The operation should be denied due to non-existent spouse
        And The client with DNI 20100100 should not be married

    Scenario: Cannot register marriage when one client is already married
        Given A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
        And A client with DNI 20100100, surname "Garcia", name "Daisy", born on "2000-10-15" and with address "Cortina 223"
        And A client with DNI 21000000, surname "Ferreira", name "Maria", born on "2002-06-10" and with address "Av. Acoyte 245"
        And A new marriage on date "2024-01-01" between DNI 11222333 and DNI 20100100
        When I try to register a new marriage on date "2024-01-02" between DNI 20100100 and DNI 21000000
        Then The operation should be denied due to a client involved already being married
        And The client with DNI 11222333 should be married
        And The client with DNI 20100100 should be married
        And The client with DNI 21000000 should not be married