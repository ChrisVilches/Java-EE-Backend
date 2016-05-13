# Java EE Backend

Pool ```JDBC/recreu_pool```

# Manual de uso

## Usuario

### Listar todos los usuarios

```GET /usuarios```

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