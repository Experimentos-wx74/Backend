Feature: US14 Separación de fechas disponibles para asesoría
  Como asesor
  quiero poder seleccionar y separar las fechas y horas en las que estoy disponible
  para ofrecer asesorías para que los usuarios interesados puedan ver mis horarios disponibles y agendar una cita en un momento conveniente

  Scenario Outline: Registrar disponibilidad para asesorías
    Given el <asesor> desea registrar sus fechas y horas disponibles para asesorías.
    And  está visualizando la página de "Horario disponible"
    When haga clic en el botón “Registrar nueva fecha”
    And  complete los <datos del nuevo horario disponible> que tiene el asesor
    Then el sistema actualizará y guardará las fechas y horas seleccionadas como disponibles

    Examples:
      | asesor  | datos del nuevo horario disponible                         |
      | asesorA | Fecha: 24/05/2024 Hora de inicio: 12:00 Hora de fin: 13:00 |
      | asesorB | Fecha: 17/02/2024 Hora de inicio: 11:00 Hora de fin: 11:30 |

  Scenario Outline: Eliminar horario de disponibilidad para asesorías
    Given el <asesor> desea eliminar un horario de disponibilidad para asesorías
    And está visualizando la página de "Horario disponible"
    When haga clic en el botón “Eliminar” junto al <horario que desea eliminar>
    And confirme la eliminación del horario
    Then el sistema actualizará y eliminará el horario de disponibilidad seleccionado

    Examples:
      | asesor  | horario que desea eliminar              |
      | asesorA | Fecha: 24/05/2024 Hora de inicio: 12:00 |
      | asesorB | Fecha: 17/02/2024 Hora de inicio: 11:00 |

