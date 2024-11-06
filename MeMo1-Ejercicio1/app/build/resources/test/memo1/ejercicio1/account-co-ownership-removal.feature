Feature: Removal of co-ownership of account
 
  Scenario: Successfully removing co-owner of account
      Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
      And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
      And An account with CBU 123456789, alias "iAmAccount", with client of DNI 11222333 as owner and created by branch 1
      And A client with DNI 20100100, surname "Garcia", name "Daisy", born on "2000-10-15" and with address "Cortina 223" as a co-owner of the account with CBU 123456789, created by branch 1
      And A client with DNI 19700900, surname "Simpson", name "Mark", born on "1999-02-13" and with address "Av. Lope de Vega 1071" as a co-owner of the account with CBU 123456789, created by branch 1
      When I remove the co-ownership of account with DNI 19700900
      Then The client with DNI 20100100 should have 1 related account/s
      And The client with DNI 19700900 should have 0 related account/s

  Scenario: Cannot remove co-ownership of client that is not co-owner
      Given A new branch with name "Suc. Palermo" and address "Av. Lavalle 2500"
      And A client with DNI 11222333, surname "Gregory", name "John", born on "1997-11-29" and with address "Av. Triunvitaro 557"
      And An account with CBU 123456789, alias "iAmAccount", with client of DNI 11222333 as owner and created by branch 1
      And A client with DNI 20100100, surname "Garcia", name "Daisy", born on "2000-10-15" and with address "Cortina 223" as a co-owner of the account with CBU 123456789, created by branch 1
      And A client with DNI 19700900, surname "Simpson", name "Mark", born on "1999-02-13" and with address "Av. Lope de Vega 1071"
      When I try to remove the co-ownership of account with DNI 19700900
      Then The operation should be denied