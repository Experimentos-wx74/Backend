Feature: US20 Inicio de sesión
  Como usuario
  quiero acceder a mi cuenta registrada
  para acceder a las funciones de usuario

  Scenario Outline: Inicio de sesión exitoso
    Given el <usuario> desea acceder a su cuenta registrada.
    And se encuentra en el apartado de “Iniciar sesión”.
    When introduzca sus <credenciales> correctamente.
    Then será redireccionado a su <vista de usuario> respectiva.
    Examples:
      | usuario                  | credenciales                     | vista de usuario             |
      | usuario criador de cuyes | correo@gmail.com, contraseña123  | seccion de recursos y gastos |
      | usuario asesor           | correo2@gmail.com, contraseña456 | seccion de clientes          |

  Scenario Outline: Inicio de sesión fallido
    Given el <usuario> desea acceder a su cuenta registrada.
    And se encuentra en el apartado de “Iniciar sesión”.
    When introduzca sus <credenciales> incorrectamente.
    Then no se le permitirá acceso a su cuenta.
    And recibirá un <mensaje indicando el error>.
    Examples:
      | usuario                  | credenciales                      | mensaje indicando el error              |
      | usuario criador de cuyes | correo@gmail.com, contraseña1234  | contraseña incorrecta, intente de nuevo |
      | usuario asesor           | correo2@gmail.com, contraseña1234 | contraseña incorrecta, intente de nuevo |

  Scenario Outline: Bloqueo de sesión por exceso de intentos
    Given el <usuario> desea acceder a su cuenta registrada.
    And se encuentra en el apartado de “Iniciar sesión”.
    When introduzca sus <credenciales> incorrectamente.
    And siga errando hasta alcanzar el número máximo de intentos permitidos (tres intentos).
    Then recibirá un <mensaje> que le informe que ha excedido el número de intentos permitidos.
    And su cuenta será bloqueada temporalmente.
    Examples:
      | usuario                  | credenciales                                | mensaje                                                                           |
      | usuario criador de cuyes | intento 3, correo@gmail.com, contraseña1234 | ha excedido el número de intentos permitidos, espere antes de intentar nuevamente |
      | usuario asesor           | intento 3, correo@gmail.com, contraseina456 | ha excedido el número de intentos permitidos, espere antes de intentar nuevamente |