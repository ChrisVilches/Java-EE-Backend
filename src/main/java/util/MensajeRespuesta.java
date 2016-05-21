package util;

import javax.json.Json;
import javax.json.JsonObject;

public class MensajeRespuesta {
	
	public static JsonObject crear(String mensaje){
		
		return Json.createObjectBuilder().add("mensaje", mensaje).build();
		
	}

}
