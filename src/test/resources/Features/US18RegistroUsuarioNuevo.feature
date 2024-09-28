Feature: US18 Registro de un usuario nuevo
  Como usuario
  quiero registrarme
  para acceder a las funciones de usuario.

  Scenario Outline: Registro de cuenta por formulario
    Given el <usuario> desea registrarse en la aplicación.
    And se encuentra en el apartado de “Registrarse”.
    When complete el <formulario> de registro con su informacion personal.
    And seleccione su <rol> en la aplicación entre “Criador” o “Asesor”
    And los <datos> sean correctos según las validaciones establecidas.
    Then la cuenta se creará.

    Examples:
      | usuario     | rol     | email           | contraseña | fecha de nacimiento | ubicación | descripción                 |
      | Julio Casas | criador | julio@gmail.com | 156626aaas | 1990-01-01          | Huachipa  | Novato en crianza de cuyes. |


  Scenario Outline: Registro incorrecto de cuenta
    Given el usuario se encuentra en el apartado de “Registrarse”.
    When ingrese los <datos> solicitados de manera errónea.
    Then la cuenta no se creará.
    And recibirá un <mensaje> indicando el error.

    Examples:
      | usuario        | rol    | años de exp | mensaje                                           |
      | Jose Luis Mora | asesor | -1          | Error: Años de experiencia no puede ser negativo. |