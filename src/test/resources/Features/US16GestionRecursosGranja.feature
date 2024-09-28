
Feature: US16 Gestión de recursos de la granja
  Como criador de cuyes
  quiero registrar los recursos de mi granja
  para tener un control sobre los recursos esenciales como alimentos y medicamentos.

  Scenario Outline: Registrar de nuevo ingreso de recursos en el inventario

    Given el <criador> ha recibido el pedido de recursos por parte del proveedor y desea registrar esto en su inventario.
    And se encuentra en el apartado de “Registro”.
    When haga clic en el botón “Nuevo Recurso”
    And ingresa los detalles de <nombre> y <tipo de recurso>, <fecha>, <cantidad> y <observacion>.
    Then el sistema mostrará una confirmacion.

    Examples:
      | criador | nombre  | tipo de recurso | fecha      | cantidad | observacion |
      | Pablo   | Alfalfa | Alimento        | 12/05/2024 | 15kg     | Ninguna     |

  Scenario Outline: Ver recursos
    Given el criador desea ver los recursos que posee.
    And se encuentra en el apartado de “Mi Granja” de la Sidebar.
    When haga clic en el botón <Gestión de Recursos>.
    Then el sistema le permitirá visualizar sus recursos que incluyen datos como el nombre del recurso, el tipo de recurso (alimento, medicina, cultivo u otro) y la fecha en el que fue registrado este recurso.

    Examples:
      | criador | nombre  | tipo de recurso | fecha      | cantidad | observacion |
      | Pablo   | Alfalfa | Alimento        | 12/05/2024 | 15kg     | Ninguna     |

  Scenario Outline: Filtrar recursos
    Given el creador desea ver un recurso de un tipo en específico.
    And se encuentra en la sección de "Gestión de Recursos" del apartado de <Mi Granja>.
    When seleccione el tipo de recurso que sea filtrar (todos, alimento, medicina, cultivo)
    And haga clic en el botón <Filtrar>
    Then el sistema le mostrará una lista de todos los recursos que pertenezcan a ese tipo.

    Examples:
      | criador | nombre  | tipo de recurso | fecha      | cantidad | observacion |
      | Pablo   | Alfalfa | Alimento        | 12/05/2024 | 15kg     | Ninguna     |
