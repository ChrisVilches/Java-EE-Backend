# Java EE Backend

## Tabla de contenidos

<!-- toc -->

- [Configuracion de la base de datos](#configuracion-de-la-base-de-datos)
- [Configuracion de Glassfish](#configuracion-de-glassfish)
- [Usuario](#usuario)
  * [Listar todos los usuarios](#listar-todos-los-usuarios)
  * [Listar usuarios paginado](#listar-usuarios-paginado)
  * [Login](#login)
  * [Registrar usuario](#registrar-usuario)
  * [Eliminar usuario](#eliminar-usuario)
  * [Editar usuario](#editar-usuario)
- [Actividad](#actividad)
  * [Listar todas las actividades](#listar-todas-las-actividades)
  * [Agregar actividad](#agregar-actividad)

<!-- tocstop -->

## Configuracion de la base de datos

1. En la carpeta ```/DB``` se encuentra el modelo (se abre con MySQL Workbench). Este modelo puede compilar codigo.
2. Tambien se encuentra en la carpeta la ultima version del codigo de la estructura de la base de datos (```schema.sql```).
3. Tambien hay un archivo con una poblacion inicial (```poblacion.sql```).
4. La base de datos debe llamarse ```recreu```.


## Configuracion de Glassfish

Crear la siguiente Pool en Glassfish

```
JDBC/recreu_pool
```

## Usuario

### Listar todos los usuarios

```GET /usuarios```

### Listar usuarios paginado


```GET /usuarios/?ultima_id={ultima_id}&mostrar={tamano_pagina}```

Esto sirve para hacer algo similar a lo que hace Twitter, es decir, mostrar una porcion de la lista, y luego cuando se necesitan mas, se coloca en **ultima_id** la ultima ID de la pagina actual, y el numero de nuevos elementos que se quieren obtener (**tamano_pagina**). Por ejemplo si la ultima id fue 10, y se quieren obtener 3 nuevos elementos, los elementos que se retornan tienen las ids 9, 8 y 7 (asumiendo que estan ordenadas y no hay huecos, aunque funciona bien incluso si los hubiera).


1. **ultima_id** es la ultima ID de la pagina anterior. Para mostrar la primera pagina, dejarla con valor 0.
2. **tamano_pagina** cantidad de resultados que se quieren obtener.


### Login

```POST /usuarios/login```

Retorna codigo de estado ```200 (OK)``` en caso de login correcto, y en caso de login incorrecto, retorna otro codigo. El correo puede tener mayusculas y minusculas (no afecta), pero la password debe ser igual que como se registro.

Alternativa #1: Sin incluir ```@usach.cl```

```json
{
    "correo":"correo",
    "password":"pass"
}
```

Alternativa #2: Con ```@usach.cl```
```json
{
    "correo":"correo@usach.cl",
    "password":"pass"
}
```


### Registrar usuario

```POST /usuarios```

El formato del JSON debe ser:

```json
{
	"apellidoMaterno":"cespedes",
	"apellidoPaterno":"vilches",
	"primerNombre":"felipe",
	"segundoNombre":"chris",
	"correo":"correo.sin.arroba.usach",
	"password":"pass.minimo.6.caracteres",
	"fechaNacimiento":"1991-10-24",	
	"sexo": true
}
```

Al registrar usuario, los siguientes atributos son opcionales (ya que se pueden agregar despues, cuando el usuario quiera configurar su perfil):

1. ```numeroTelefono```
2. ```intereses```
3. ```urlFacebook```
4. ```urlInstagram```
5. ```urlTwitter```
6. ```carrera``` (asume que la BD no tiene todas las carreras, por lo cual algunos usuarios pueden no indicar su carrera, ademas pueden ser profesores, por eso no lo puse como mandatorio)

En caso de querer incorporar la carrera, se pone de esta forma

```json
{
	"correo":"correo.sin.arroba.usach",
	"carrera":{
		"carreraId": 4
	},
	"sexo": true
}
```

Los demas atributos (```disponibilidad```, ```last_update```, etc) no se insertan al registrar usuario (se ignoran).

### Eliminar usuario

```DELETE /usuarios/{id}```

Si lo elimina exitosamente, retorna codigo de estado ```200 (OK)```, y retorna codigo de error en caso que el usuario no existe.

### Editar usuario

```PUT /usuarios```

**Resumen**: Enviar como JSON el mismo objeto, pero con atributos extra (para agregar atributos que antes eran ```null```), o eliminando atributos (para que ahora sean ```null```), y los atributos pueden tener valores distintos para modificarlos en la BD.

Se puede modificar un usuario existente, por ejemplo, para agregarle intereses, o URLs de Instagram, Facebook, Twitter, o para modificar cualquier cosa.

Por ejemplo, inicialmente un usuario podria no tener el atributo ```"urlInstagram": "..."``` en su JSON (eso es porque en la BD seria ```null```). Si se quiere agregar una URL de instagram, lo que se debe hacer en ese caso, es agregar el atributo a su JSON, y enviarlo a la URL usando ```PUT```. Notese que el JSON a enviar, debe contener todos los atributos, incluso si solo se quiere modificar uno.

Tambien se puede eliminar el atributo de la URL de instagram, y nuevamente enviar el JSON, lo cual eliminaria su URL de instagram (la pondria en ```null```).

Para editar la carrera, basta con modificar la ID de la carrera, y no los otros atributos (ya que son ignorados).

Los atributos ```created_at``` y ```last_update``` son ignorados.


## Actividad

### Listar todas las actividades

```GET /actividades```

### Agregar actividad

```POST /actividades```

Ejemplo de json:

```json
{
	"cuerpoActividad": "Cuerpo actividad 7",
	"duracionEstimada": "02:01:15",
	"fechaInicio": "2016-05-02T08:15:03-03:00",
	"requerimientosActividad": "Requerimientos actividad 7",
	"tipo": {
		"tipoId": 34
	},
	"tituloActividad": "Titulo actividad 7",
	"ubicacionActividadX": 8,
	"ubicacionActividadY": 552
}
```

**Nota**: En fecha de inicio hay un ```-03:00```. Esto se debe a que Chile se encuentra -3 horas desde Londres. Esto se puede omitir, y colocar solamente ```2016-05-02T08:15:03```. (Preferiblemente utilizar alguna funcion prefabricada que genere fechas y/o horas)

