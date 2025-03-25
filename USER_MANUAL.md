# Manual de Usuario

## Introducción

La aplicación de gestión académica permite a los usuarios administrar estudiantes, profesores, cursos e inscripciones de manera sencilla. Este manual describe cómo utilizar las principales funcionalidades de la aplicación.

---

## Índice

1. [Inicio de Sesión](#inicio-de-sesión)
2. [Gestión de Estudiantes](#gestión-de-estudiantes)
3. [Gestión de Profesores](#gestión-de-profesores)
4. [Gestión de Cursos](#gestión-de-cursos)
5. [Gestión de Inscripciones](#gestión-de-inscripciones)
6. [Cerrar Sesión](#cerrar-sesión)
7. [Documentación de la API](#documentación-de-la-api)

---

## Inicio de Sesión

1. Abre la aplicación en tu navegador en [http://localhost:4200](http://localhost:4200).
2. Ingresa tu **nombre de usuario** y **contraseña** en los campos correspondientes.
3. Haz clic en el botón **Iniciar Sesión**.
4. Si las credenciales son correctas, serás redirigido al panel principal.

---

## Gestión de Estudiantes

### Ver la Lista de Estudiantes
1. En el menú lateral, haz clic en **Estudiantes**.
2. Se mostrará una tabla con todos los estudiantes registrados, incluyendo su nombre, apellido, correo electrónico, y grado.

### Agregar un Estudiante
1. Haz clic en el botón **Agregar Estudiante**.
2. Completa el formulario con los datos del estudiante:
   - Nombre
   - Apellido
   - Fecha de Nacimiento
   - Correo Electrónico
   - Teléfono
   - Número de Matrícula
   - Grado
3. Haz clic en **Guardar** para registrar al estudiante.

### Editar un Estudiante
1. En la lista de estudiantes, haz clic en el botón **Editar** junto al estudiante que deseas modificar.
2. Actualiza los datos en el formulario.
3. Haz clic en **Guardar** para aplicar los cambios.

### Eliminar un Estudiante
1. En la lista de estudiantes, haz clic en el botón **Eliminar** junto al estudiante que deseas borrar.
2. Confirma la acción en el cuadro de diálogo.

---

## Gestión de Profesores

### Ver la Lista de Profesores
1. En el menú lateral, haz clic en **Profesores**.
2. Se mostrará una tabla con todos los profesores registrados, incluyendo su nombre, apellido, especialidad y fecha de contratación.

### Agregar un Profesor
1. Haz clic en el botón **Agregar Profesor**.
2. Completa el formulario con los datos del profesor:
   - Nombre
   - Apellido
   - Fecha de Nacimiento
   - Correo Electrónico
   - Teléfono
   - Especialidad
   - Fecha de Contratación
3. Haz clic en **Guardar** para registrar al profesor.

### Editar un Profesor
1. En la lista de profesores, haz clic en el botón **Editar** junto al profesor que deseas modificar.
2. Actualiza los datos en el formulario.
3. Haz clic en **Guardar** para aplicar los cambios.

### Eliminar un Profesor
1. En la lista de profesores, haz clic en el botón **Eliminar** junto al profesor que deseas borrar.
2. Confirma la acción en el cuadro de diálogo.

---

## Gestión de Cursos

### Ver la Lista de Cursos
1. En el menú lateral, haz clic en **Cursos**.
2. Se mostrará una tabla con todos los cursos registrados, incluyendo su nombre, descripción, créditos y profesor asignado.

### Agregar un Curso
1. Haz clic en el botón **Agregar Curso**.
2. Completa el formulario con los datos del curso:
   - Nombre
   - Descripción
   - Créditos
   - Profesor (selecciona de la lista desplegable)
3. Haz clic en **Guardar** para registrar el curso.

### Editar un Curso
1. En la lista de cursos, haz clic en el botón **Editar** junto al curso que deseas modificar.
2. Actualiza los datos en el formulario.
3. Haz clic en **Guardar** para aplicar los cambios.

### Eliminar un Curso
1. En la lista de cursos, haz clic en el botón **Eliminar** junto al curso que deseas borrar.
2. Confirma la acción en el cuadro de diálogo.

---

## Gestión de Inscripciones

### Ver la Lista de Inscripciones
1. En el menú lateral, haz clic en **Inscripciones**.
2. Se mostrará una tabla con todas las inscripciones registradas, incluyendo el nombre del estudiante, el curso y la fecha de inscripción.

### Agregar una Inscripción
1. Haz clic en el botón **Agregar Inscripción**.
2. Completa el formulario con los datos de la inscripción:
   - Estudiante (selecciona de la lista desplegable)
   - Curso (selecciona de la lista desplegable)
   - Fecha de Inscripción
3. Haz clic en **Guardar** para registrar la inscripción.

### Eliminar una Inscripción
1. En la lista de inscripciones, haz clic en el botón **Eliminar** junto a la inscripción que deseas borrar.
2. Confirma la acción en el cuadro de diálogo.

---

## Cerrar Sesión

1. En el menú lateral, haz clic en el botón **Logout** (🚪).
2. Serás redirigido a la página de inicio de sesión.

---

## Documentación de la API

1. Abre tu navegador y accede a la URL [http://localhost:8080/swagger-ui/](http://localhost:8080/swagger-ui/).
2. En esta página, podrás explorar y probar los endpoints de la API REST del backend.
3. Cada endpoint incluye información detallada sobre los parámetros requeridos, respuestas posibles y ejemplos.

---

## Notas Adicionales

- Asegúrate de completar todos los campos requeridos en los formularios antes de guardar.
- Si encuentras algún error, verifica que el backend esté corriendo correctamente en [http://localhost:8080](http://localhost:8080).

---

Este manual cubre las funcionalidades principales de la aplicación. Si tienes preguntas adicionales, contacta al administrador del sistema. ¡Disfruta usando la aplicación! 🎓
