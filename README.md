# MisTareas1_RodrigoRojasRedondo
MisTareas1:
https://github.com/Rodrivdlc/MisTareas1_RodrigoRojasRedondo.git
## Esta es la primera aplicación de tres que incluye el ejercicio Prueba de Programación Android 1:



otros ejercicio: 
ListaCompra:
https://github.com/Rodrivdlc/ListaCompra_RodrigoRojasRedondo.git

## Descripción

MisTareas1_RodrigoRojasRedondo es una aplicación de Android diseñada para ayudar a los usuarios a gestionar sus tareas. La aplicación permite a los usuarios agregar, eliminar y actualizar el estado de sus tareas. Soporta tanto el idioma inglés como el español, los cuales se pueden alternar usando un interruptor en la interfaz de usuario.

## Características

- **Agregar Tareas**: Los usuarios pueden agregar nuevas tareas usando el campo de entrada y el botón de agregar.
- **Eliminar Tareas**: Los usuarios pueden eliminar tareas haciendo clic en el botón de eliminar junto a cada tarea.
- **Actualizar Estado de Tareas**: Los usuarios pueden marcar las tareas como realizadas o pendientes haciendo clic en el botón correspondiente.
- **Soporte de Idiomas**: La aplicación soporta los idiomas inglés y español, los cuales se pueden alternar usando un interruptor.
- **Base de Datos SQLite**: La aplicación utiliza SQLite para almacenar las tareas localmente en el dispositivo.

## Detalles Técnicos

### Base de Datos SQLite

La aplicación utiliza SQLite para almacenar las tareas. La base de datos se usa para persistir las tareas entre sesiones de la aplicación. Las siguientes operaciones son soportadas:

- **Agregar Tarea**: Inserta una nueva tarea en la base de datos.
- **Eliminar Tarea**: Elimina una tarea de la base de datos.
- **Actualizar Estado de Tarea**: Actualiza el estado de una tarea (pendiente o realizada) en la base de datos.
- **Recuperar Tareas**: Obtiene las tareas de la base de datos según su estado (pendiente o realizada).

### Soporte de Idiomas

La aplicación soporta el cambio dinámico de idioma entre inglés y español. Esto se logra mediante:

1. Uso de un componente `Switch` para alternar el idioma.
2. Actualización de la configuración de la localización según el idioma seleccionado.
3. Recomposición de la interfaz de usuario para reflejar el cambio de idioma.

### Componentes de la Interfaz de Usuario

- **TaskApp**: La función composable principal que configura la interfaz de usuario y maneja la gestión de estado.
- **TaskItem**: Una función composable que representa un solo ítem de tarea en la lista.

### Estructura del Código

- `MainActivity.kt`: La actividad principal que configura la vista de contenido.
- `TaskApp`: La función composable principal que contiene la lógica de la interfaz de usuario.
- `TaskItem`: Una función composable para mostrar tareas individuales.
- `Color.kt`: Contiene las definiciones de colores usados en el tema de la aplicación.
- `strings.xml` y `strings-es.xml`: Archivos de recursos para las cadenas en inglés y español.


## Uso

1. Inicia la aplicación.
2. Usa el campo de entrada para agregar nuevas tareas.
3. Alterna el interruptor para cambiar el idioma entre inglés y español.
4. Usa los botones junto a cada tarea para marcarla como realizada o eliminarla.
5. Usa los botones en la parte inferior para filtrar las tareas por su estado (pendiente o realizada).

