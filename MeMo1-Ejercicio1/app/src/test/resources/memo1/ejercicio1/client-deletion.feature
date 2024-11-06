Feature: Client deletion
 
    Scenario: Successfully delete client
        Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
        And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
        When I delete the client
        Then The client with DNI 11222333 should not be found anymore

    Scenario: Cannot delete client with associated accounts
        Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
        And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
        And An account with CBU 123456789, alias "iAmAccount", with client of DNI 11222333 as owner and created by branch 1
        When I try to delete the client
        Then The operation should be denied due to the client having associated accounts
        Then The client with DNI 11222333 should have 1 related account/s

    Scenario: Cannot delete non-existent client
        Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
        And A non-existent client DNI like 5000000
        When I try to delete the non-existent client
        Then The operation should be denied due to the client not existing
    