# Proyecto de Registro Escolar

Este proyecto es una aplicación de gestión académica que permite administrar estudiantes, profesores, cursos e inscripciones. Está dividido en dos partes principales: el **backend** (API REST) y el **frontend** (interfaz de usuario).

## Tabla de Contenidos
- [Requisitos Previos](#requisitos-previos)
- [Clonar el Proyecto](#clonar-el-proyecto)
- [Configuración del Backend](#configuración-del-backend)
- [Configuración del Frontend](#configuración-del-frontend)
- [Ejecutar el Proyecto](#ejecutar-el-proyecto)
- [Tecnologías Utilizadas](#tecnologías-utilizadas)

---

## Requisitos Previos

Antes de comenzar, asegúrate de tener instalados los siguientes programas en tu máquina:

- **Node.js** (versión 16 o superior) y **npm** (incluido con Node.js): [Descargar Node.js](https://nodejs.org/)
- **Angular CLI** (versión 15 o superior): `npm install -g @angular/cli`
- **Java JDK** (versión 11 o superior): [Descargar Java](https://www.oracle.com/java/technologies/javase-downloads.html)
- **Maven** (versión 3.6 o superior): [Descargar Maven](https://maven.apache.org/download.cgi)
- **MySQL** (versión 8 o superior): [Descargar MySQL](https://dev.mysql.com/downloads/)

---

## Clonar el Proyecto

Clona el repositorio desde GitHub:

```bash
git clone https://github.com/DavidZam09/CODE_CDSA.git
cd CODE_CDSA
```

---

## Configuración del Backend

1. **Ir al directorio del backend**:
   ```bash
   cd backend
   ```

2. **Configurar la base de datos**:
   Ya que el proyecto solo se probo con H2, no se requiere la configuracion de MySQL, sin embargo aqui dejo las instrucciones:
   - Crea una base de datos en MySQL llamada `gestion_academica` (o el nombre que prefieras).
   - Actualiza el archivo de configuración `application.properties` ubicado en `src/main/resources/` con tus credenciales de MySQL:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/gestion_academica
     spring.datasource.username=tu_usuario
     spring.datasource.password=tu_contraseña
     spring.jpa.hibernate.ddl-auto=update
     ```

4. **Compilar el proyecto**:
   Ejecuta el siguiente comando para compilar el backend:
   ```bash
   mvn clean install
   ```

5. **Ejecutar el backend**:
   Inicia el servidor backend con:
   ```bash
   mvn spring-boot:run
   ```

   El backend estará disponible en: [http://localhost:8080](http://localhost:8080)

---

## Configuración del Frontend

1. **Ir al directorio del frontend**:
   ```bash
   cd frontend
   ```

2. **Instalar dependencias**:
   Ejecuta el siguiente comando para instalar las dependencias del proyecto:
   ```bash
   npm install
   ```

3. **Configurar el archivo de entorno**:
   Asegúrate de que el archivo `src/environments/environment.ts` tenga la URL correcta del backend:
   ```typescript
   export const environment = {
     production: false,
     API_URL: 'http://localhost:8080'
   };
   ```

4. **Ejecutar el frontend**:
   Inicia el servidor de desarrollo con:
   ```bash
   ng serve
   ```

   El frontend estará disponible en: [http://localhost:4200](http://localhost:4200)

---

## Ejecutar el Proyecto

1. **Inicia el backend**:
   - Asegúrate de que el backend esté corriendo en [http://localhost:8080](http://localhost:8080).

2. **Inicia el frontend**:
   - Asegúrate de que el frontend esté corriendo en [http://localhost:4200](http://localhost:4200).

3. **Accede a la aplicación**:
   - Abre tu navegador y ve a [http://localhost:4200](http://localhost:4200) para usar la aplicación.

---

## Tecnologías Utilizadas

### Backend:
- **Java** (Spring Boot)
- **Maven** (Gestor de dependencias)
- **H2** (Base de datos relacional)

### Frontend:
- **Angular** (Framework de desarrollo frontend)
- **Angular Material** (Componentes de UI)
- **TypeScript** (Lenguaje de programación)
  
## Diagrama de Base de datos:
![database_schema_v2](https://github.com/user-attachments/assets/0adc4839-3a18-41c5-a4ef-2e224954d2db)
---

## Notas Adicionales

- Si encuentras problemas con las dependencias, asegúrate de que las versiones de las herramientas instaladas sean compatibles con el proyecto.
- Puedes personalizar el archivo `application.properties` para usar una base de datos diferente o cambiar la configuración del servidor.

---

Con esta guía, deberías poder clonar, configurar y ejecutar el proyecto sin problemas. ¡Buena suerte! 🚀

