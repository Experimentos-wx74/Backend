Feature: US11 Gestión de publicaciones de asesores
  Como asesor
  quiero hacer publicaciones
  para tener un mayor alcance hacia los criadores dentro de la aplicación

  Scenario Outline: Crear una nueva publicación

    Given el <asesor> desea crear una publicación.
    And está en el apartado de Mis Publicaciones.
    When hace clic en el botón Crear Publicación.
    Then se le redirige a un formulario donde puede ingresar el contenido de su nueva <publicacion>.
    And después de completar el contenido, hace clic en el botón Publicar.
    Then el sistema le mostrará un <mensaje> de confirmación.

    Examples:
      | asesor       | publicacion                                                                                                                         | mensaje                        |
      | Josue Garcia | Título: ¡Impulsa tu granja!, Descripción: Estoy brindando consejos muy útiles para..., Imagen: http://google.com/imagen-ejemplo.png | ¡Publicación creada con éxito! |

  Scenario Outline: Eliminar una publicación existente

    Given el <asesor> desea eliminar una <publicacion> existente.
    And está en el apartado de Mis Publicaciones de la Sidebar.
    And tiene una publicación previamente creada.
    When selecciona la opción de eliminar en la publicación que desea borrar.
    Then el sistema le mostrará un <mensaje> de confirmación solicitando la confirmación de la eliminación.
    And después de confirmar, la publicación se elimina de su perfil y ya no está disponible para los criadores.

    Examples:
      | asesor       | publicacion                                                                                                                         | mensaje                                     | mensaje de confirmación           |
      | Josue Garcia | Título: ¡Impulsa tu granja!, Descripción: Estoy brindando consejos muy útiles para..., Imagen: http://google.com/imagen-ejemplo.png | ¿Realmente desea eliminar esta publicación? | ¡Publicación eliminada con éxito! |