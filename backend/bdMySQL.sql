CREATE DATABASE escuela;
USE escuela;

-- Tabla Persona (Base)
CREATE TABLE Persona (
    id_persona INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    telefono VARCHAR(20)
);

-- Tabla Estudiante (Hereda de Persona)
CREATE TABLE Estudiante (
    id_persona INT PRIMARY KEY,
    numero_matricula VARCHAR(50) UNIQUE NOT NULL,
    grado VARCHAR(50) NOT NULL,
    FOREIGN KEY (id_persona) REFERENCES Persona(id_persona) ON DELETE CASCADE
);

-- Tabla Profesor (Hereda de Persona)
CREATE TABLE Profesor (
    id_persona INT PRIMARY KEY,
    especialidad VARCHAR(100) NOT NULL,
    fecha_contratacion DATE NOT NULL,
    FOREIGN KEY (id_persona) REFERENCES Persona(id_persona) ON DELETE CASCADE
);
CREATE TABLE Persona (
    id_persona INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100),
    apellido VARCHAR(100),
    email VARCHAR(100) UNIQUE
);

CREATE TABLE Administrativo (
    id_persona INT PRIMARY KEY,
    cargo VARCHAR(100),
    departamento VARCHAR(100),
    FOREIGN KEY (id_persona) REFERENCES Persona(id_persona) ON DELETE CASCADE
);

CREATE TABLE Curso (
    id_curso INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100),
    descripcion TEXT,
    creditos INT,
    id_profesor INT,
    FOREIGN KEY (id_profesor) REFERENCES Profesor(id_persona) ON DELETE SET NULL
);

CREATE TABLE Inscripcion (
    id_inscripcion INT PRIMARY KEY AUTO_INCREMENT,
    id_estudiante INT,
    id_curso INT,
    fecha_inscripcion DATE,
    FOREIGN KEY (id_estudiante) REFERENCES Estudiante(id_persona) ON DELETE CASCADE,
    FOREIGN KEY (id_curso) REFERENCES Curso(id_curso) ON DELETE CASCADE
);
