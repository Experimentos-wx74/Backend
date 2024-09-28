Feature: US12 Registro de información de animales
  Como criador de cuyes
  quiero contar con un sistema de registro de animales
  para almacenar información básica sobre cada animal, incluyendo su número de identificación, especie, raza, género y fecha de nacimiento.

  Scenario Outline: Registrar un nuevo animal

    Given el <criador> desea registrar un cuy en su jaula.
    And se encuentra en el apartado de “Registro”.
    When haga clic en el botón ”Nuevo cuy”
    And ingresa la información básica del animal, incluyendo su <numero de identificacion>, <numero de jaula> dónde se va a encontrar, <raza>, <genero>, <peso> y <fecha de nacimiento>
    And haga clic “Registrar”

    Then el sistema le mostrará una confirmación del registro.
    Examples:
      | criador | numero de identificacion | numero de jaula | raza | genero | peso kg | fecha de nacimiento |
      | Pablo   | 1                        | 1               | Peru | Macho  | 1.5     | 01/02/2024          |

  Scenario Outline: Fallar en el registro de animal

    Given el <criador> desea registrar un cuy en su jaula.
    And se encuentra en el apartado de “Registro”.
    When haga clic en el botón ”Nuevo cuy”
    And intenta ingresar la información del animal pero no completa todos los campos obligatorios para el registro.
    And haga clic en “Registrar”.
    Then el sistema le mostrará un <mensaje de error>.

    Examples:
      | criador | mensaje de error                     |
      | Pablo   | Faltan completar campos obligatorios |