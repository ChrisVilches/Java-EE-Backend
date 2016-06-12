# Java EE Backend

## Tabla de contenidos

<!-- toc -->

- [Configuracion de la base de datos](#configuracion-de-la-base-de-datos)
- [Configuracion de Glassfish](#configuracion-de-glassfish)
- [Usuario](#usuario)
  * [Listar todos los usuarios](#listar-todos-los-usuarios)
  * [Obtener un usuario por su ID](#obtener-un-usuario-por-su-id)
  * [Obtener usuario por su correo](#obtener-usuario-por-su-correo)
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
  * [Obtener actividad por su ID](#obtener-actividad-por-su-id)
  * [Listar todas las actividades organizadas por un usuario (ID)](#listar-todas-las-actividades-organizadas-por-un-usuario-id)
  * [Listar actividades que pertenecen a una categoria](#listar-actividades-que-pertenecen-a-una-categoria)
  * [Agregar actividad](#agregar-actividad)
  * [Listar usuarios que participan en una actividad](#listar-usuarios-que-participan-en-una-actividad)
  * [Editar actividad](#editar-actividad)
- [Categoria](#categoria)
  * [Listar todas las categorias](#listar-todas-las-categorias)
  * [Listar los tipos pertenecientes a una categoria](#listar-los-tipos-pertenecientes-a-una-categoria)
- [Reporte](#reporte)
  * [Listar reportes](#listar-reportes)
  * [Obtener reporte dado su ID](#obtener-reporte-dado-su-id)
  * [Marcar un reporte como revisado](#marcar-un-reporte-como-revisado)
- [Tipo](#tipo)
  * [Listar todos los tipos](#listar-todos-los-tipos)

<!-- tocstop -->

## Configuracion de la base de datos

1. En la carpeta ```/DB``` se encuentra el modelo (se abre con MySQL Workbench). Este modelo puede compilar codigo.
2. Tambien se encuentra en la carpeta la ultima version del codigo de la estructura de la base de datos (```schema.sql```).
3. Tambien hay un archivo con una poblacion inicial (```poblacion.sql```).
4. La base de datos debe llamarse ```recreu```.


## Configuracion de Glassfish

Crear la siguiente Pool en Glassfish

```
jdbc/recreu_pool
```

No olvidar colocar los atributos usuario, password y URL al crear esta pool. La URL es algo como ```jdbc:mysql://localhost:3306/recreu```.

## Usuario

### Listar todos los usuarios

```GET /usuarios```

Se pueden pasar parametros extra agregando al final algo como ```?mostrar=20&ultima_id=223```.

Diccionario de parametros:

1. **mostrar (numero)**: Pone un limite a cuantos elementos mostrar. Equivalente a ```LIMIT``` en SQL.
2. **ultima_id (numero)**: Sirve para paginar. Entrega los elementos cuya id es menor a ```ultima_id```. Ademas pone el orden en ```ORDER BY id DESC```. En conjunto con el parametro ```mostrar```, sirve para paginar, ya que se puede obtener un numero de elementos, por ejemplo 5, y luego obtener los siguiente 5 elementos (indicando la ultima id de la pagina anterior). Si el valor es 0, entonces se entrega la primera pagina.

Ejemplos:

1. Si existen 15 elementos ordenados numerados perfectamente con ID ascendente, y si se hace ```/usuarios?mostrar=5&ultima_id=0```, entonces se obtienen los usuarios ids 15, 14, 13, 12, 11. Luego para obtener la siguiente pagina, se ejecuta ```/usuarios?mostrar=5&ultima_id=11``` (ya que la ultima ID de la primera pagina fue 11), y se obtienen los usuarios con ids 10, 9, 8, 7, 6.
2. En el caso anterior, si se ejecuta ```/usuarios?ultima_id=0``` se obtienen todos los elementos empezando desde el 15 hasta el 1.
3. Y si se ejecuta ```/usuarios?mostrar=2``` se obtienen los usuarios 1 y 2, ya que ```mostrar``` no ordena descendientemente.


### Obtener un usuario por su ID

```GET /usuarios/{usuario_id}```


### Obtener usuario por su correo

```GET /usuarios/buscar?correo={correo}```

El correo puede tener ```@usach.cl``` o no. Estar en minusculas o mayusculas, nada de eso afecta.

Arroja codigo exitoso 200 en ambos casos (si encuentra o no un usuario), pero cuando lo encuentra, retorna el JSON, y cuando no lo encuentra, no entrega ningun JSON.


### Login

```POST /usuarios/login```

Retorna codigo de estado ```200 (OK)``` y el JSON con el usuario en caso de login correcto, y en caso de login incorrecto, retorna otro codigo de estado. El correo puede tener mayusculas y minusculas (no afecta), pero la password debe ser igual que como se registro.

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

Permite los parametros **limit_a**, **limit_b** y **nofinalizadas**, ejemplo:

```GET /usuarios/2/actividades?limit_a=1&limit_b=2```

O tambien

```GET /usuarios/2/actividades?nofinalizadas```


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

Diccionario de parametros extra:

1. **mostrar (numero)**: Limite de cuantos elementos se quieren ver. Equivalente a: [Listar todos los usuarios](#listar-todos-los-usuarios)
2. **limit_a** y **limit_b**: Sirve tambien para paginar. El parametro **limit_a** indica en que posicion comienza (si el valor es 0, entonces entrega la primera fila), y **limit_b** indica la cantidad de filas que se desea obtener.
3. **ultima_id (numero)**: Obtener los elementos a partir de la ultima_id. Sirve para paginar. Equivalente a: [Listar todos los usuarios](#listar-todos-los-usuarios)
4. **tipos**: Sirve para obtener la union de actividades que pertenecen a los tipos de la lista entregada. Ejemplos de formato es:
  * ```?tipos=2```
  * ```?tipos=2-3-4```
  * ```?tipos=2-7-8-9-3```
5. Parametros georeferenciales: Para hacer una busqueda geografica, tienen que existir estos tres parametros (simultaneamente):
  * **latitud**: Latitud donde centrar la busqueda.
  * **longitud**: Longitud donde centrar la busqueda.
  * **ladocuadrado**: Una vez centrada la busqueda, se crea un cuadrado de lado ```ladocuadrado``` alrededor del centro, y se filtran las actividades, mostrando solo las que estan dentro de este cuadrado.
6. **nofinalizadas**: Filtra las actividades para mostrar solamente las que no han finalizado (se calcula con su tiempo de inicio y duracion estimada). Este parametro no tiene valor, simplemente se pone asi ```var=1&nofinalizadas&dato=2```
7. Parametros de notificaciones: Para obtener las "notificaciones", no se debe usar el parametro ```nofinalizadas```, y se debe usar estos parametros (todos simultaneamente):
 * **usuario_no_participa**: Id del usuario. Las actividades mostradas seran solo las cuales el usuario no participa ni organiza.
 * **minutos**: La actividad comienza dentro de cuantos minutos. Si se pone 30 minutos, quiere decir que la actividad comienza en un momento que esta dentro del rango **ahora**, y **ahora+30min**.
 * Se pueden usar otros parametros de ubicacion geografica, en conjunto con estos parametros.
8. **activas**: Parametro (sin valor) que filtra para mostrar solo las que estan activas (```es_activo = true``` en la base de datos). Se utiliza de esta forma ```/actividades?activas```
9. **tiempo_inicio** y **tiempo_fin**: Sirve para entregar solo las que se encuentran dentro del rango de fechas. El formato es ```YY-MM-DD-hh:mm:ss``` (Año, mes, dia, hora, minutos, y segundos, en ese orden, cada numero separado por UN caracter, de los cuales puede ser alguno de estos ```:-_```). Ver ejemplo de uso mas abajo.


Ejemplo con varios parametros:

```GET /actividades/?latitud=7&longitud=550&ladocuadrado=100&tipos=13&mostrar=2&ultima_id=39```

Otro ejemplo con notificaciones

```GET /actividades/?latitud=7&longitud=505&ladocuadrado=1000&minutos=35&usuario_no_participa=2```


Ejemplo con fechas:

```GET /actividades/?tiempo_inicio=2016-06-01-00:00:00&tiempo_fin=2016-11-05-00:10:00```


### Obtener actividad por su ID

```GET /actividades/{actividad_id}```


### Listar todas las actividades organizadas por un usuario (ID)

```GET /actividades/organizador/{usuario_organizador_id}```

### Listar actividades que pertenecen a una categoria

```GET /actividades/categoria/{nombre_categoria}```

El nombre de la categoria debe respetar minusculas y mayusculas.

Ejemplo (**limit_a** es para que los resultados empiecen desde la fila 2 (numero empieza desde 0), y **limit_b** es para mostrar un total de 3 filas)

```GET /actividades/categoria/Deporte?limit_a=2&limit_b=3```

Tambien acepta el parametro **nofinalizadas**, ejemplo

```GET /actividades/categoria/Deporte?nofinalizadas```


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

Debe enviarse el JSON conteniendo los atributos con sus valores actuales, y los nuevos valores en los atributos que se quieren cambiar. El tipo y organizador se puede simplificar a solo su ID. Ejemplo:

```json
{
    "actividadId": 3,
    "cuerpoActividad": "Cuerpo actividad 3",
    "duracionEstimada": "02:00:10-03:00",
    "esActivo": true,
    "fechaInicio": "2016-05-02T05:10:07-03:00",
    "organizador": {
        "usuarioId": 1
    },
    "personasMaximas": 5,
    "requerimientosActividad": "Requerimientos actividad 3",
    "tipo": {
        "tipoId": 12
    },
    "tituloActividad": "Titulo actividad 3",
    "ubicacionActividadX": 85,
    "ubicacionActividadY": 50
}
```


## Categoria

### Listar todas las categorias

```GET /categorias```

### Listar los tipos pertenecientes a una categoria

```GET /categorias/{categoria_id}/tipos```

## Reporte

### Listar reportes

```GET /reportes```

Diccionario de parametros extra:

1. **mostrar (numero)**: Limite de cuantos elementos se quieren ver. Equivalente a: [Listar todos los usuarios](#listar-todos-los-usuarios)
2. **ultima_id (numero)**: Obtener los elementos a partir de la ultima_id. Sirve para paginar. Equivalente a: [Listar todos los usuarios](#listar-todos-los-usuarios)
3. **no_revisados**: Sirve para listar solamente los reportes que aun no se han revisado. Este parametro no tiene valor, por ejemplo ```?param1=hola&no_revisados&param2=100```.


### Obtener reporte dado su ID

```GET /reportes/{reporte_id}```

### Marcar un reporte como revisado

```PUT /reportes/{reporte_id}/revisar/{administrador_id}```

El parametro ```administrador_id``` es la id del usuario administrador que revisa el reporte.

## Tipo

### Listar todos los tipos

```GET /tipos```
