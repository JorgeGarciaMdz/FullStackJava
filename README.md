# FullStackJava

## Realizacion
Aplicacion Web realizada con NetBeans 12.

Utilizacion de base de datos Mysql Maria DB.

Conector utilizado para la conexión a la base de datos mysql-connector-java-8.0.20.jar

Nombre de BD "hotel"

Usuario y contraseña de base de datos "jorge"

Cuando el ide ejecuta la aplicacion, pide una contraseña, la misma es "jorge"

Servidor web utilizado "apache-tomcat-8.0.53"

Se utiliza la libreria gson-2.8.5.jar de google para parsear objetos a json y viceversa

En cada entidad esta la definicion de cada tabla y columna con sus respcetivas restricciones.

## Ejecución
Se ejecuta el proyecto web.

Hay dos tipos de usuarios que utilizan el sistema, el usuario "empleado" y usuario "administrador"

Usuario empleado:

  user: jor123

  password: 1234

Usuario administrador:

  user: admin

  password: admin

## API

### Employee

Todo lo relacionado a la entidad empleado

#### GET

**http://localhost:8080/Garcia_Jorge_COM1/api/v1/employee/**

Devuelve una lista de objetos de todos los empleados


**http://localhost:8080/Garcia_Jorge_COM1/api/v1/employee/?id=id_empleado**

Devuelve un objeto del tipo empleado con el id que se pasa como parametro

#### POST

**http://localhost:8080/Garcia_Jorge_COM1/api/v1/employee/**

{
    "user": "user_name",
    "password": "password",
    "admission_date": "Aug 24, 2021 5:56:52 PM",
    "type": "employee",
    "dni": 123456,
    "name": "name",
    "lastname": "lastname",
    "birthday": "Aug 24, 2021 5:56:52 PM"
}

