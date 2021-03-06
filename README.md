# retoTecnico
Manual de Usuario

Las tecnologías que se usaron para el desarrollo de la presente prueba técnica son:

# Prerequisitos
-Java 11 o superior
-Mysql 8.0
-Spring Boot: 2.7.1

# Ejecución del aplicativo:

-Clonar el siguiente repositorio: https://github.com/jsonArmando/retoTecnico.git
-Use su IDE de preferencia
-Descargar las librerías del maven:  mvn clean install
-Crear base de datos ejemplo:
    create database medicalDB;
    use medicalDB;
-Modificar properties: se realizo con base de datos Mysql local
    Adicione su usuario de base de datos local al properties más la url
    spring.datasource.url=jdbc:mysql://localhost:3306/medicalDB
    spring.datasource.url=${DB_MYSQL_URL}
    spring.datasource.username=${DB_USER_URL}
    spring.datasource.password=${DB_PASSWORD_URL}

-Ejecute el proyecto con el comando: mvn spring-boot:run o por medio del IDE

# EndPoint
Se Microservicio con tres ENDPOINT 
-Registre un paciente con los diferentes porcentajes de azucar, grasa, oxígeno, ejemplo:
    POST: localhost:8080/patients/patient-create

    {
        "numberId":"4321",
        "names":"Marcos Aurelio",
        "surnames":"Villamil Suarez",
        "sugar":71,
        "fat":86,
        "oxygen":"45"
    }
-Consulte el paciente por número de identificació con el siguiente ENDPOINT EJEMPLO:
    GET: localhost:8080/patient-id/{patientStatusId}
    Obtiene los siguientes resultados

    [
        {
            "code": 2,
            "description": "\"infectious pathology of HIGH risk of seriously ill.\"",
            "numberId": 4321,
            "names": "Marcos Aurelio",
            "surnames": "Villamil Suarez",
            "sugar": 71,
            "fat": 86,
            "oxygen": 45,
            "status": "HIGH risk disease.",
            "active": true
        }
    ]

    description: comentario médico del estado del paciente
    status: nivel del paciente, alto,medio,bajo
    active: true si el paciente se encuentra hospitalizado false, se encuentra en la casa

-Consulta todos los pacientes ejemplo:
    GET:localhost:8080/patients/patient-status/

Puede realizar las diferentes pruebas, como valore superiores al 100%, usuarios, no encontrados.
En el aplicativo puede detallar cada uno los patrones y arquitectura que se uso, más las pruebas unitarias y funcionales.
