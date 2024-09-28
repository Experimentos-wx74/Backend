Feature: US06 Notificación de citas al asesor
  Como asesor
  quiero recibir notificaciones de citas programadas por los criadores
  para mantenerme al tanto de mis ofertas laborales

  Scenario Outline: Ver notificaciones de cita programadas por criadores

    Given el <asesor> desea ver sus <notificaciones> de citas programadas.
    When se encuentre en el apartado de “Notificaciones”
    Then el sistema le mostrará un <mensaje> que describe brevemente la solicitud.

    Examples:
      | asesor | Tipo de Notificación | Mensaje                                                      | Fecha                                |
      | Luisa  | Cita                 | Tienes un asesoramiento programado a ofrecer para Luis Lopez | 16 de septiembre de 2024, 14:00:00 h |