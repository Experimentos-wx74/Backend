Feature: US13 Visualización y edición de información de animales
  Como criador de cuyes
  quiero acceder a la información de un animal
  para visualizar o actualizar su información registrada.

  Scenario Outline: Buscar y visualizar la información de un animal registrado

    Given el <criador> tiene varios cuyes registrados en la aplicación y desea editar la información de uno en específico.
    And se encuentra en el apartado de Mis Animales
    When haga clic en Ver de una jaula
    And seleccione el <card> de un cuy específico.
    Then el sistema le mostrará la <informacion detallada> del cuy.

    Examples:
      | criador     | card                                     | informacion detallada                                                                                                       |
      | Juan Perez  | ID: 1, Nombre: Pelusa, Estado: Saludable | ID: 1, Nombre: Pelusa, Raza: Andina, Genero: Hembra, ID de jaula: 1, Peso: 5.5kg, Estado: Saludable, Observaciones: Ninguna |
      | Maria Lopez | ID: 2, Nombre: Canela, Estado: Enfermo   | ID: 2, Nombre: Canela, Raza: Perú, Genero: Hembra, ID de jaula: 2, Peso: 4.2kg, Estado: Enfermo, Observaciones: Ninguna     |

  Scenario Outline: Actualizar información de un animal registrado

    Given el <criador> tiene varios cuyes registrados en la aplicación y desea editar la información de uno en específico.
    And se encuentra viendo la información detallada de un cuy.
    When haga clic en el botón Editar.
    And realiza <cambios> en la información del cuy.
    Then el sistema actualizará la <informacion detallada> del cuy

    Examples:
      | criador     | cambios     | informacion detallada                                                                                                       |
      | Juan Perez  | Peso: 5.6kg | ID: 1, Nombre: Pelusa, Raza: Andina, Genero: Hembra, ID de jaula: 1, Peso: 5.6kg, Estado: Saludable, Observaciones: Ninguna |
      | Maria Lopez | Peso: 4.3kg | ID: 2, Nombre: Canela, Raza: Perú, Genero: Hembra, ID de jaula: 2, Peso: 4.3kg, Estado: Enfermo, Observaciones: Ninguna     |