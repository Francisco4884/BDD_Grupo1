# 🏦 Sistema Bancario BAD - Banco Andino de Desarrollo

Sistema bancario completo con arquitectura de 3 capas: Frontend (HTML/JS) + Backend (Java) + Base de Datos (SQL Server).

## 📋 Descripción

Este sistema permite la gestión completa de operaciones bancarias incluyendo:
- ✅ Autenticación de usuarios
- ✅ Registro y gestión de clientes
- ✅ Apertura de cuentas bancarias
- ✅ Registro de transacciones
- ✅ Consultas y reportes en tiempo real

## 🚀 Inicio Rápido

### 1️⃣ Descargar Dependencia Gson

**Windows:**
```cmd
descargar_gson.bat
```

**Linux/Mac:**
```bash
chmod +x descargar_gson.sh
./descargar_gson.sh
```

O descarga manualmente desde: [Gson 2.10.1](https://repo1.maven.org/maven2/com/google/code/gson/gson/2.10.1/gson-2.10.1.jar) y coloca en `lib/`

### 2️⃣ Configurar Base de Datos

1. Abre SQL Server Management Studio
2. Ejecuta el script: `database_setup.sql`
3. Actualiza credenciales en: [src/DataAccess/SQLDataHelper.java](src/DataAccess/SQLDataHelper.java)

```java
private static final String DB_URL = 
    "jdbc:sqlserver://localhost:1433;databaseName=BancoDB;encrypt=true;trustServerCertificate=true";
private static final String USER = "tu_usuario";
private static final String PASSWORD = "tu_password";
```

### 3️⃣ Compilar

**Windows:**
```cmd
compile.bat
```

**Linux/Mac:**
```bash
chmod +x compile.sh
./compile.sh
```

### 4️⃣ Ejecutar

**Windows:**
```cmd
run.bat
```

**Linux/Mac:**
```bash
chmod +x run.sh
./run.sh
```

### 5️⃣ Acceder

Abre tu navegador en: **http://localhost:8080**

**Credenciales de prueba:**
- Usuario: `admin`
- Contraseña: `admin123`
- Sucursal: `Quito - Centro`

## 📚 Documentación

| Documento | Descripción |
|-----------|-------------|
| [INICIO_RAPIDO.md](INICIO_RAPIDO.md) | Guía de inicio rápido paso a paso |
| [README_SISTEMA.md](README_SISTEMA.md) | Documentación técnica completa |
| [API_EJEMPLOS.md](API_EJEMPLOS.md) | Ejemplos de uso de la API REST |
| [RESUMEN_IMPLEMENTACION.md](RESUMEN_IMPLEMENTACION.md) | Resumen de todo lo implementado |
| [DESCARGAR_GSON.md](DESCARGAR_GSON.md) | Instrucciones para descargar Gson |

## 🏗️ Arquitectura

```
Frontend (HTML/JS)
    ↓ HTTP REST (JSON)
Controladores REST
    ↓
Lógica de Negocio (BL)
    ↓
Acceso a Datos (DAO)
    ↓
SQL Server Database
```

## 📡 API REST Endpoints

### Autenticación
- `POST /api/login` - Iniciar sesión

### Clientes
- `GET /api/clientes` - Listar todos
- `GET /api/clientes?cedula={cedula}` - Buscar por cédula
- `POST /api/clientes` - Crear cliente
- `PUT /api/clientes` - Actualizar cliente
- `DELETE /api/clientes?id={id}` - Eliminar cliente

### Cuentas
- `GET /api/cuentas` - Listar todas
- `POST /api/cuentas` - Abrir cuenta

### Transacciones
- `GET /api/transacciones` - Listar todas
- `POST /api/transacciones` - Registrar transacción

Ver [API_EJEMPLOS.md](API_EJEMPLOS.md) para ejemplos completos.

## 🛠️ Tecnologías

- **Backend**: Java (JDK 11+)
- **Base de Datos**: SQL Server
- **Frontend**: HTML5, CSS3, JavaScript (ES6+)
- **Servidor**: HTTP embebido (sin Tomcat)
- **API**: REST con JSON
- **Librerías**: Gson, JDBC

## 📁 Estructura del Proyecto

```
BDD/
├── src/
│   ├── Server/              # Servidor HTTP y Controladores REST
│   ├── BusinessLogic/       # Lógica de negocio
│   ├── DataAccess/          # Acceso a datos y DTOs
│   └── UserInterface/       # Archivos HTML
├── lib/                     # Dependencias JAR
├── bin/                     # Clases compiladas
├── compile.bat/sh           # Scripts de compilación
├── run.bat/sh               # Scripts de ejecución
└── database_setup.sql       # Script de base de datos
```

## 🔧 Solución de Problemas

Ver [README_SISTEMA.md](README_SISTEMA.md#solución-de-problemas) para guía completa.

---

**Sistema Bancario BAD - Banco Andino de Desarrollo**

*Versión 1.0 - Enero 2026*
