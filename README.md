User Login
============

Microservicio para la creación y consulta de usuarios.

## Comando para limpiar y compilar:

```
/gradlew clean build
```

## Endpoints:

**/v1/sign-up** : Endpoint de creación de un usuario

- Recibe un nombre, email, password y lista de telefonos:

```
{
    "name": "Example User",
    "email": "user@example.com",
    "password": "U01password",
    "phones": [
        {
            "number": 43219876,
            "cityCode": 11,
            "countryCode": "+54"
        }
    ]
}
```

- Devuelve el usuario creado:

```
{
    "id": "11dbf014-2501-422e-acf2-bea342919bbb",
    "created": "2023-04-09T20:37:24.500Z",
    "lastLogin": "2023-04-09T20:37:24.500Z",
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2ODEwNzYyNDQsImlhdCI6MTY4MTA3MjY0NCwianRpIjoiNTFlNmM0MGEtY2RlNy00NTdjLWI1NDctZWY2ZTgxYTQzMjBiIiwiZW1haWwiOiJ1c2VyQGV4YW1wbGUuY29tIn0.mgujwxJH4YaEXqoLSsc7JCYqE34IASceY4CsLpGe2cw",
    "isActive": true
}
```

**/v1/login** : Endpoint de consulta de usuario

- Recibe un token de usuario en el Authorization header

- Devuelve el usuario guardado:

```
{
    "id": "11dbf014-2501-422e-acf2-bea342919bbb",
    "created": "2023-04-09T20:37:24.500Z",
    "lastLogin": "2023-04-09T20:37:24.500Z",
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2ODEwNzYyNDQsImlhdCI6MTY4MTA3MjY0NCwianRpIjoiNTFlNmM0MGEtY2RlNy00NTdjLWI1NDctZWY2ZTgxYTQzMjBiIiwiZW1haWwiOiJ1c2VyQGV4YW1wbGUuY29tIn0.mgujwxJH4YaEXqoLSsc7JCYqE34IASceY4CsLpGe2cw",
    "isActive": true,
    "name": "Example User",
    "email": "user@example.com",
    "password": "U01password",
    "phones": [
        {
            "number": 43219876,
            "cityCode": 11,
            "countryCode": "+54"
        }
    ]
}
```

## Diagramas ilustrativos

Diagrama de componentes
------------------------

![\[fig:class01\]Diagrama de componentes](diagrams/componentDiagram.png)

Diagramas de secuencia
------------------------
![\[fig:class01\]Diagrama de secuencia login](diagrams/loginSequenceDiagram.png)
