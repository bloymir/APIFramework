Feature: Ejemplo de request GET de Github
  Scenario: Prueba Get a un endpoint
    Given Envio un request tipo Get a un endpoint https://api.github.com URI
    Then Obtengo un status code 200