Feature: US08 Calificación al asesor luego de una cita
    Como criador de cuyes
    quiero calificar al asesor luego de una consulta
    para ayudar a otros criadores a tomar una decisión informada antes de separar una cita

    Scenario Outline: Calificar al asesor

        Given el <criador> desea hacer un feedback referente al servicio del asesor.
        And se encuentra en la vista de <calificacion> del servicio.
        When haga clic en el botón “Calificar Servicio”
        Then el sistema le permitirá asignarle un <numero> de estrellas y <resenar> el servicio del asesor.

        Examples:
            | criador     | numero | reseña                                                              |
            | Mario Perez | 5      | Muy buena asesoría! He aprendido bastante de la nutrición de cuyes. |

    Scenario Outline: Omitir Calificación

        Given el <criador> no desea dar feedback al asesor referente al servicio.
        And se encuentra en la vista de <calificacion> del servicio.
        When haga clic en el botón “Omitir calificación”
        Then el sistema le permitirá omitir la reseña.


        Examples:
            | criador    | calificacion |
            | Jose Mayta | omitida      |