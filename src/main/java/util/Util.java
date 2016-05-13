package util;

public class Util {

	public static String normalizarNombre(String str) {
		if(str == null) return "";
		StringBuffer sb = new StringBuffer(str.toLowerCase());
		sb.replace(0, 1, str.substring(0, 1).toUpperCase());
		return sb.toString().trim();
	}

}
