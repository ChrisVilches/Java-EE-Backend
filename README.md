# Java EE Backend

## Tabla de contenidos

<!-- toc -->

- [Configuracion de la base de datos](#configuracion-de-la-base-de-datos)
- [Configuracion de Glassfish](#configuracion-de-glassfish)
- [Usuario](#usuario)
  * [Listar todos los usuarios](#listar-todos-los-usuarios)
  * [Obtener un usuario por su ID](#obtener-un-usuario-por-su-id)
  * [Listar usuarios paginado](#listar-usuarios-paginado)
  * [Login](#login)
  * [Registrar usuario](#registrar-usuario)
  * [Listar actividades en las que participa el usuario](#listar-actividades-en-las-que-participa-el-usuario)
  * [Listar actividades que organizo un usuario](#listar-actividades-que-organizo-un-usuario)
  * [Agregar participacion de usuario en actividad](#agregar-participacion-de-usuario-en-actividad)
  * [Eliminar participacion de usuario en actividad](#eliminar-participacion-de-usuario-en-actividad)
  * [Eliminar usuario](#eliminar-usuario)
  * [Editar usuario](#editar-usuario)
- [Actividad](#actividad)
  * [Listar todas las actividades](#listar-todas-las-actividades)
  * [Listar actividades de forma paginada](#listar-actividades-de-forma-paginada)
  * [Obtener actividad por su ID](#obtener-actividad-por-su-id)
  * [Obtener actividades que pertenecen a uno o mas tipos](#obtener-actividades-que-pertenecen-a-uno-o-mas-tipos)
  * [Agregar actividad](#agregar-actividad)
  * [Listar usuarios que participan en una actividad](#listar-usuarios-que-participan-en-una-actividad)
  * [Editar actividad](#editar-actividad)
- [Categoria](#categoria)
  * [Listar todas las categorias](#listar-todas-las-categorias)
  * [Listar los tipos pertenecientes a una categoria](#listar-los-tipos-pertenecientes-a-una-categoria)

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

### Obtener un usuario por su ID

```GET /usuarios/{usuario_id}```

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
	"sexo": true,
	"esAdministrador": false
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


### Listar actividades en las que participa el usuario

```GET /usuarios/{usuario_id}/actividades```


### Listar actividades que organizo un usuario

```GET /usuarios/{usuario_id}/actividades/?organizador```


### Agregar participacion de usuario en actividad

```POST /usuarios/{usuario_id}/actividades/{actividad_id}```

### Eliminar participacion de usuario en actividad

```DELETE /usuarios/{usuario_id}/actividades/{actividad_id}```




### Eliminar usuario

```DELETE /usuarios/{usuario_id}```

Si lo elimina exitosamente, retorna codigo de estado ```200 (OK)```, y retorna codigo de error en caso que el usuario no existe.

### Editar usuario

```PUT /usuarios```

**Resumen**: Enviar como JSON el mismo objeto, pero con atributos extra (para agregar atributos que antes eran ```null```), o eliminando atributos (para que ahora sean ```null```), y los atributos pueden tener valores distintos para modificarlos en la BD. En otras palabras, el JSON no "parcha" el objeto original, si no que lo sobreescribe completamente, por lo que si faltan atributos en el nuevo JSON, se escriben en la BD como nulo (si es que lo permite), en vez de dejar el valor original.

Se puede modificar un usuario existente, por ejemplo, para agregarle intereses, o URLs de Instagram, Facebook, Twitter, o para modificar cualquier cosa.

Por ejemplo, inicialmente un usuario podria no tener el atributo ```"urlInstagram": "..."``` en su JSON (eso es porque en la BD seria ```null```). Si se quiere agregar una URL de instagram, lo que se debe hacer en ese caso, es agregar el atributo a su JSON, y enviarlo a la URL usando ```PUT```. Notese que el JSON a enviar, debe contener todos los atributos, incluso si solo se quiere modificar uno.

Tambien se puede eliminar el atributo de la URL de instagram, y nuevamente enviar el JSON, lo cual eliminaria su URL de instagram (la pondria en ```null```).

Para editar la carrera, basta con modificar la ID de la carrera, y no los otros atributos (ya que son ignorados).

Los atributos ```created_at``` y ```last_update``` son ignorados.


## Actividad

### Listar todas las actividades

```GET /actividades```


### Listar actividades de forma paginada

```GET /actividades/?ultima_id={ultima_id}&mostrar={tamano_pagina}```

Para entender que significan estos parametros, ver [Listar usuarios paginado](#listar-usuarios-paginado).


### Obtener actividad por su ID

```GET /actividades/{actividad_id}```


### Obtener actividades que pertenecen a uno o mas tipos

**Lista de tipos**: Esta URL contiene el parametro ```tipos``` y su valor es una lista de IDs de tipos, separadas por un ```-```. En caso de usar esta consulta, se obtiene la **union**, y no la interseccion de los tipos (Utiliza la consulta del tipo ```where x in (1,2,3..)```). El formato de URL es:

```GET /actividades/?tipos=1-2-3-4-5```

**Solamente un tipo**: Similar al anterior, pero solo se coloca un numero.

```GET /actividades/?tipos=3```





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
		"tipoId": 13
	},
	"tituloActividad": "Titulo actividad 7",
	"organizador":{
		"usuarioId": 3
	},
	"ubicacionActividadX": 8,
	"ubicacionActividadY": 552
}
```

**Nota**: En fecha de inicio hay un ```-03:00```. Esto se debe a que Chile se encuentra -3 horas desde Londres. Esto se puede omitir, y colocar solamente ```2016-05-02T08:15:03```. (Preferiblemente utilizar alguna funcion prefabricada que genere fechas y/o horas)

### Listar usuarios que participan en una actividad

```GET /actividades/{actividad_id}/usuarios```


### Editar actividad

```PUT /actividades/```

Debe enviarse el JSON conteniendo la ID de la actividad, y los atributos que se quieran cambiar. El tipo se puede simplificar a solo su ID. El atributo organizador es ignorado, ya que no se puede modificar. Ejemplo:

```json
{
    "actividadId": 4,
    "cuerpoActividad": "Cuerpo actividad 4 Update",
    "duracionEstimada": "02:00:10-03:00",
    "esActivo": false,
    "fechaInicio": "2016-03-02T05:10:07-03:00",
    "personasMaximas": 8,
    "requerimientosActividad": "Requerimientos actividad 4 Update",
    "tipo": {
        "tipoId": 8
    },
    "tituloActividad": "Titulo actividad 4 Update",
    "ubicacionActividadX": 800,
    "ubicacionActividadY": 50
}
```


## Categoria

### Listar todas las categorias

```GET /categorias```

### Listar los tipos pertenecientes a una categoria

```GET /categorias/{categoria_id}/tipos```