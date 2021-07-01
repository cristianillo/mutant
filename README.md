```javascript
/**
 * @autor Cristian Robayo
 * @rol Backend Developer
 **/
```

# Operación Mutante
Proyecto que detecta si un humano es mutante basándose en su secuencia de ADN basado en Java 8 + Spring Boot.
Se recibe como parametro un array de String de NXN con la secuencia de ADN con los siguientes caracteres (A,T,C,G). Un humano es mutante, si se encuentra más de una secuencia de cuatro letras iguales, de forma oblicua, horizontal o vertical.
---
### Prerequisitos
Para la ejecución de la aplicación en ambientes locales se debe tener instalado los siguientes programas:

- [Git]
- [Java 8 SDK]
- [Maven]
- STS, Eclipse o IntelliJ
- Postgres

---
### Instalación
- 1) Se debe descargar el proyecto del repositorio remoto de git

```sh
$ git clone https://github.com/cristianillo/quasar-operation.git
```
- 2) Acceder a la carpeta `quasar-operation`.

```sh
$ cd quasar-operation
```
- 3) Compilar la aplicación con el siguiente comando:

```sh
$ mvn clean install
```
- 4) Correr la aplicación con Spring Boot de manera local.

```sh
$ mvn spring-boot:run
```
---
### Contenido de la aplicación

El utilizó Swagger para la documentación básica de los métodos y el endpoint es el siguiente:
- [http://quasaroperationservice-env.eba-tdqmq4ia.us-west-2.elasticbeanstalk.com/swagger-ui.html][endpoint-swagger]

La aplicación existe un archivo de configucarión YML para modificar la información de los satelites que hacen parte de la aplicación.

```yml
application:
  satellites:
    -
      name: 'kenobi'
      position: 
        x: -500
        y: -200
    -
      name: 'skywalker'
      position: 
        x: 100
        y: -100
    -
      name: 'sato'
      position:
        x: 500
        y: 100
```

El servicio soporta los siguientes métodos:
##### POST /topsecret/
Se encarga de calcular la posición de la nave a partir de las distancias teniendo en cuenta la posición de los satelites *Kenobi*, *Skywalker* y *Sato*.
- EndPoint: http://quasaroperationservice-env.eba-tdqmq4ia.us-west-2.elasticbeanstalk.com/topsecret/

Request del método:

```json
{
   "satellites":[
      {
         "distance":824.54,
         "message":[
            "SOS", "", "", "un", "mensaje", "", "auxilio"
         ],
         "name":"kenobi"
      },
      {
         "distance":382.30,
         "message":[
            "", "este", "", "un", "", "de", ""
         ],
         "name":"skywalker"
      },
      {
         "distance":275.18,
         "message":[
            "SOS", "", "es", "", "mensaje", "", "auxilio"
         ],
         "name":"sato"
      }
   ]
}
```

##### POST /topsecret_split/{satellite_name}
Agregar información de la distancia, el mensaje y el nombre del satelite `satellite_name` para que el método GET pueda calcular la pisición de la nave.

- EndPoint: http://quasaroperationservice-env.eba-tdqmq4ia.us-west-2.elasticbeanstalk.com/topsecret_split/{satellite_name}

Request del método:

```json
{
      "distance":382.30,
      "message":[
            "", "este", "", "un", "", "de", ""
         ]
}
```

Request del método con el mensaje de error cuando no encuentra el satelite creado en la aplicacion:

```json
{
  "message": "Satellite not found to add information"
}
```

##### GET /topsecret_split/
Se encarga de calcular la posición de acuerdo a la información agregada con el método POST. En el caso que no se pueda calcular la posición por falta de información se mostrara un mensaje de error.

- EndPoint: http://quasaroperationservice-env.eba-tdqmq4ia.us-west-2.elasticbeanstalk.com/topsecret_split/

```json
{
  "code": 404,
  "description": "There is not enough satellites information to calculate the ship position. 
  				Should be three diferent satellites information"
}
```

---
[Java 8 SDK]: https://www.oracle.com/co/java/technologies/javase/javase-jdk8-downloads.html
[Maven]: https://maven.apache.org/download.cgi
[Git]: https://git-scm.com/downloads
[endpoint-swagger]: http://quasaroperationservice-env.eba-tdqmq4ia.us-west-2.elasticbeanstalk.com/swagger-ui.html

_


